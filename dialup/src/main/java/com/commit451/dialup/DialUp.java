package com.commit451.dialup;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Are you online?
 */
public class DialUp {

    private DialUp() {
    }

    /**
     * Creates an observable to allow for listening to connectivity changes
     *
     * @param context context
     * @return observable
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static Observable<Boolean> listen(@NonNull Context context) {
        final Context applicationContext = context.getApplicationContext();
        final IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        return Observable.create(new OnSubscribeBroadcastRegister(context, filter, null, null))
                // To get initial connectivity status
                .startWith(new Intent())
                .map(new Function<Intent, Boolean>() {
                    @Override
                    public Boolean apply(Intent intent) throws Exception {
                        return status(applicationContext);
                    }
                })
                .distinctUntilChanged();
    }

    /**
     * Returns the connectivity state
     *
     * @param context context
     * @return true if connected
     */
    public static boolean status(@NonNull Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return null != activeNetwork && activeNetwork.isConnected();

    }
}
