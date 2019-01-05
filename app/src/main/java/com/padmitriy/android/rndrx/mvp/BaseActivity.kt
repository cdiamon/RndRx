package com.padmitriy.android.rndrx.mvp

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), BaseView {

    fun networkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager?.activeNetworkInfo as NetworkInfo

        return networkInfo != null && networkInfo.isConnected
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(@StringRes message: Int) {
        showMessage(getString(message))
    }


}