package com.padmitriy.android.rndrx.mvp

import android.support.annotation.StringRes

interface BaseView {

    fun showMessage(message: String)

    fun showMessage(@StringRes message: Int)
}