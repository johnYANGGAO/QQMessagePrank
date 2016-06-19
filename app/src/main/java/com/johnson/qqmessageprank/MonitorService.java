package com.johnson.qqmessageprank;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.IBinder;
import android.util.Log;

import com.johnson.qqmessageprank.Utils.AlerReceiver;
import com.johnson.qqmessageprank.Utils.Const;

public class MonitorService extends Service {
    public AlerReceiver receiver;
    public MonitorService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("TAG", " MONITOR START");
        registerHomeKeyReceiver();
        receiver=new AlerReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("volume_max");
        registerReceiver(receiver,filter);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

       new Thread(new Runnable() {
           @Override
           public void run() {
               setVolum();
           }
       }).start();

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

    private void setVolum(){

        while (true){
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(receiver!=null) {
                Intent intent=new Intent();
                intent.setAction("volume_max");
                MonitorService.this.sendBroadcast(intent);
            }
        }



    }
}
