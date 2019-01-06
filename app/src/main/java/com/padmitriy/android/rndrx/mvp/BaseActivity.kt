package com.padmitriy.android.rndrx.mvp

import android.annotation.SuppressLint
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), BaseView {

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(@StringRes message: Int) {
        showMessage(getString(message))
    }
}