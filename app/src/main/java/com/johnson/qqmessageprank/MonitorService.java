package com.johnson.qqmessageprank;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import com.johnson.qqmessageprank.Utils.Const;

public class MonitorService extends Service {

    public MonitorService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("TAG", " MONITOR START");
        registerHomeKeyReceiver();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent== null) {
            //player call
            wakeup();
        } else {

        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        wakeup();
        Log.i("TAG", " MONITOR KILLED BY OS");
        super.onDestroy();
    }

    private void wakeup() {
        Log.i("TAG", " MONITOR WAKE PLAYER UP");
        Intent again = new Intent(this ,PlayerService.class);
        again.setAction(Const.PLAYER_SERVICE_ACTION);
        startService(again);

    }



    private HomeWatcherReceiver mHomeKeyReceiver = null;

    private void registerHomeKeyReceiver() {

        mHomeKeyReceiver = new HomeWatcherReceiver();
        IntentFilter homeFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);

        registerReceiver(mHomeKeyReceiver, homeFilter);
    }
}
