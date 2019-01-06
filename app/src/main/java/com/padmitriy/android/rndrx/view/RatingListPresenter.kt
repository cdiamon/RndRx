package com.padmitriy.android.rndrx.view

import com.padmitriy.android.rndrx.R
import com.padmitriy.android.rndrx.db.dao.SummitsDao
import com.padmitriy.android.rndrx.model.Summit
import com.padmitriy.android.rndrx.mvp.BasePresenter
import com.padmitriy.android.rndrx.util.generateNewSummitList
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RatingListPresenter @Inject constructor(private val summitsDao: SummitsDao) :
    BasePresenter<RatingListView>() {

    fun getSummitsList() {

        unsubscribeOnDrop(summitsDao.getAllsummits()
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
    private fun fillSummitList() {
        unsubscribeOnDrop(
            Observable.fromCallable { summitsDao.insertSummitsList(generateNewSummitList()) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }

    fun changeSummitRating(summit: Summit) {
        unsubscribeOnDrop(Observable.fromCallable { summitsDao.updateSummit(summit) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe())
    }

}
