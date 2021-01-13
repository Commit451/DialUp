package com.commit451.dialup

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.annotation.RequiresPermission
import com.commit451.broadcastreceiverobservable.BroadcastReceiverObservable
import io.reactivex.rxjava3.core.Observable

/**
 * Are you online?
 *
 * Take a look at [observable] to get an [Observable] you can subscribe to
 */
object DialUp {

    /**
     * Creates an observable to allow for listening to connectivity changes. Keep in mind: this
     * is just connectivity according to the network state and does not always assure the user has internet
     * access. Other things, such as not being signed into a network or internal network errors could
     * network calls to fail.
     *
     * When you initially subscribe, you will get an immediate status of the network.
     *
     * @param context context
     * @return observable
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun observable(context: Context): Observable<Boolean> {
        val applicationContext = context.applicationContext
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        return BroadcastReceiverObservable.create(applicationContext, filter)
                // To get initial connectivity status
                .startWithItem(Intent())
                .map { isConnected(applicationContext) }
                .distinctUntilChanged()
    }

    /**
     * Returns the connectivity state at the moment
     *
     * @param context context
     * @return true if connected, false otherwise
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}
