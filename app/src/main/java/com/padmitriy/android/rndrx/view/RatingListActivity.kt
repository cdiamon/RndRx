package com.padmitriy.android.rndrx.view

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import com.padmitriy.android.rndrx.R
import com.padmitriy.android.rndrx.RndRxApplication
import com.padmitriy.android.rndrx.model.Summit
import com.padmitriy.android.rndrx.mvp.BaseActivity
import kotlinx.android.synthetic.main.activity_rating_list.*
import javax.inject.Inject

class RatingListActivity : BaseActivity(), RatingListView, SummitListAdapter.SummitListener {

    @Inject
    lateinit var ratingListPresenter: RatingListPresenter

    private val adapter: SummitListAdapter by lazy { SummitListAdapter(this) }
    private var fabActiveState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RndRxApplication.appComponent.inject(this)
        setContentView(R.layout.activity_rating_list)

        summitRecycler.layoutManager = LinearLayoutManager(this)
        summitRecycler.adapter = adapter

        ratingListPresenter.attachView(this)

        ratingListPresenter.getSummitsList()

        initViews()
    }

    private fun initViews() {
        fab.setOnClickListener {
            if (!fabActiveState) {
                fabActiveState = true
                fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_stop_fab))
                fabText.text = getString(R.string.fab_stop)
                ratingListPresenter.startRandomizing()
            } else {
                fabActiveState = false
                fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_play_arrow_fab))
                fabText.text = getString(R.string.randomize)
                ratingListPresenter.stopRandomizing()
            }
        }
    }

    override fun showSummits(list: List<Summit>) {
        adapter.setSummits(list)
    }

    override fun onRatingChanged(summit: Summit) {
        ratingListPresenter.changeSummitRating(summit)
    }

    override fun onDestroy() {
        super.onDestroy()
        ratingListPresenter.dropView()
    }
}
