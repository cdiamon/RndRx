package com.padmitriy.android.rndrx.view

import android.os.Bundle
import com.padmitriy.android.rndrx.R
import com.padmitriy.android.rndrx.RndRxApplication
import com.padmitriy.android.rndrx.mvp.BaseActivity
import javax.inject.Inject

class RatingListActivity : BaseActivity(), RatingListView {

    @Inject
    lateinit var ratingListPresenter: RatingListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RndRxApplication.appComponent.inject(this)
        setContentView(R.layout.activity_rating_list)

        ratingListPresenter.attachView(this)
    }


}
