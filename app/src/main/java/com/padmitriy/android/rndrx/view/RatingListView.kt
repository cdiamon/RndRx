package com.padmitriy.android.rndrx.view

import com.padmitriy.android.rndrx.model.Summit
import com.padmitriy.android.rndrx.mvp.BaseView

interface RatingListView : BaseView {
    fun showSummits(list: List<Summit>)
}