package com.dsu.wiproapplicationglobal

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


/**
 * Created by Shobhit Gupta on 10, July, 2020.
 * fiitjeeshobhit@gmail.com
 */


/**
 * For check internet connection active or not
 * @return true if connected or connecting else not connected
 */

fun Fragment.isNetworkConnected(isConnected: (Boolean) -> Unit) {
    val cm =
        activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (cm != null) {
        if (Build.VERSION.SDK_INT < 23) {
            val ni = cm.activeNetworkInfo
            if (ni != null) {
                return isConnected(ni.isConnected && (ni.type == ConnectivityManager.TYPE_WIFI || ni.type == ConnectivityManager.TYPE_MOBILE || ni.type == ConnectivityManager.TYPE_VPN))
            }
        } else {
            val n = cm.activeNetwork
            if (n != null) {
                val nc = cm.getNetworkCapabilities(n)
                return isConnected(
                    nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                        NetworkCapabilities.TRANSPORT_WIFI
                    ) || nc.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
                )
            }
        }
    }
    return isConnected(false)
}

/**
 * Show message using SnackBar
 */
fun View?.showSnackBar(message: String?) {
    this?.let {
        message?.let {
            val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
            snackBar.show()
        }
    }
}

/**
 * @return  Returns true if the charSequence is not empty and also null.
 */
fun CharSequence?.isNotEmptyAndNull(): Boolean {
    return this != null && this.isNotEmpty() && this.trim() != ""
}
