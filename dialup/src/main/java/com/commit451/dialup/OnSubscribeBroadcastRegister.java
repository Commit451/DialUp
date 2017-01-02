package com.commit451.dialup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Cancellable;


/**
 * Turns a {@link BroadcastReceiver} into an {@link io.reactivex.Observable}
 */
class OnSubscribeBroadcastRegister implements ObservableOnSubscribe<Intent> {

    private final Context context;
    private final IntentFilter intentFilter;
    private final String broadcastPermission;
    private final Handler schedulerHandler;

    OnSubscribeBroadcastRegister(Context context, IntentFilter intentFilter, String broadcastPermission, Handler schedulerHandler) {
        this.context = context;
        this.intentFilter = intentFilter;
        this.broadcastPermission = broadcastPermission;
        this.schedulerHandler = schedulerHandler;
    }

    @Override
    public void subscribe(final ObservableEmitter<Intent> e) throws Exception {
        final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                e.onNext(intent);
            }
        };
        context.registerReceiver(broadcastReceiver, intentFilter, broadcastPermission, schedulerHandler);

        e.setCancellable(new Cancellable() {
            @Override
            public void cancel() throws Exception {
                context.unregisterReceiver(broadcastReceiver);
            }
        });
    }
}