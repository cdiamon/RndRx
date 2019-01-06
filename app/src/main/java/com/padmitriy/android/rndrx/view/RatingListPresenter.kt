package com.padmitriy.android.rndrx.view

import com.padmitriy.android.rndrx.R
import com.padmitriy.android.rndrx.db.dao.SummitsDao
import com.padmitriy.android.rndrx.model.Summit
import com.padmitriy.android.rndrx.mvp.BasePresenter
import com.padmitriy.android.rndrx.util.generateNewSummitList
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random


class RatingListPresenter @Inject constructor(private val summitsDao: SummitsDao) :
    BasePresenter<RatingListView>() {

    private lateinit var randomSummitDisposable: Disposable

    /**
     * subscribing to changes in DB and rewriting view on any update
     */
    fun getSummitsList() {
        unsubscribeOnDrop(summitsDao.getAllSummits()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { list ->
                if (list.isEmpty()) {
                    // filling db with data at first start
                    fillSummitList()
                } else {
                    view.showSummits(list)
                }
            }
            .doOnError { throwable ->
                throwable.printStackTrace()
                view.showMessage(R.string.error_message)
            }
            .subscribe())
    }

    /**
     * filling db with data at first start
     */
    fun fillSummitList() {
        unsubscribeOnDrop(
            Observable.fromCallable { summitsDao.insertSummitsList(generateNewSummitList()) }
                .subscribeOn(Schedulers.io())
                .subscribe())
    }

    fun changeSummitRating(summit: Summit) {
        unsubscribeOnDrop(
            Observable.fromCallable { summitsDao.updateSummit(summit) }
                .subscribeOn(Schedulers.io())
                .subscribe())
    }

    /**
     * in this method we take random item from database, and with random time interval changing
     * item's rating with random value
     */
    fun startRandomizing() {
        randomSummitDisposable = Observable.just(0).flatMap {
            Observable.zip(
                summitsDao.getSummitById(getRandomId()).toObservable(),
                Observable.interval(getRandomDelay(), TimeUnit.MILLISECONDS),
                BiFunction<Summit, Long, Summit> { summit, _ -> return@BiFunction summit })
        }
            .subscribeOn(Schedulers.io())
            .map { return@map it.apply { rating = getRandomRating() } }
            .doOnNext { summit ->
                summitsDao.updateSummit(summit)
            }
            .repeat()
            .subscribe({}, { throwable ->
                throwable.printStackTrace()
            })

        unsubscribeOnDrop(randomSummitDisposable)
    }

//    fun startRandomizing() {
//        randomSummitDisposable =
//                summitsDao.getSummitById(getRandomId()).toObservable()
//                        .concatMap { i -> Observable.just(i).delay(getRandomDelay(), TimeUnit.MILLISECONDS) }
//                        .map { return@map it.apply { rating = getRandomRating() } }
//                        .doOnNext { summit ->
//                            summitsDao.updateSummit(summit)
//                        }
//                        .repeat(55)
//                        .subscribeOn(Schedulers.io())
//                        .subscribe({}, { throwable ->
//                            throwable.printStackTrace()
//                        })
//
//        unsubscribeOnDrop(randomSummitDisposable)
//    }
//    fun startRandomizing() {
//        randomSummitDisposable = (
//                Observable.interval(getRandomDelay(), getRandomDelay(), TimeUnit.MILLISECONDS)
//                        .flatMap { summitsDao.getSummitById(getRandomId()).toObservable() }
////                        .concatMap { i -> Observable.just(i).delay(getRandomDelay(), TimeUnit.MILLISECONDS) }
//                        .map { return@map it.apply { rating = getRandomRating() } }
//                        .doOnNext { summit ->
//                            summitsDao.updateSummit(summit)
//                        }
//                        .repeat(55)
//                        .subscribeOn(Schedulers.io())
//                        .subscribe({}, { throwable ->
//                            throwable.printStackTrace()
//                        }))
//
//
//        unsubscribeOnDrop(randomSummitDisposable)
//    }

    fun stopRandomizing() {
        randomSummitDisposable.dispose()
    }

    fun getRandomId(): Int {
        return (Random.nextInt(10) + 1).also { Timber.i("RND id: $it") }
    }

    fun getRandomRating(): Float {
        return (Random.nextFloat() * 5).also { Timber.i("RND rating: $it") }
    }

    fun getRandomDelay(): Long {
        return Random.nextLong(100, 1000).also { Timber.i("RND delay: $it") }
    }
}
