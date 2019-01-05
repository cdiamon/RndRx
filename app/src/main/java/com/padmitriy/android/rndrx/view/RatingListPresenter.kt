package com.padmitriy.android.rndrx.view

import com.padmitriy.android.rndrx.di.DatabaseModule
import com.padmitriy.android.rndrx.mvp.BasePresenter
import javax.inject.Inject

class RatingListPresenter : BasePresenter<RatingListView>() {

    @Inject
    lateinit var databaseModule: DatabaseModule


}