package com.padmitriy.android.rndrx.view

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RndRxApplication.appComponent.inject(this)
        setContentView(R.layout.activity_rating_list)

        summitRecycler.layoutManager = LinearLayoutManager(this)
        summitRecycler.adapter = adapter

        ratingListPresenter.attachView(this)

        ratingListPresenter.getSummitsList()
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
