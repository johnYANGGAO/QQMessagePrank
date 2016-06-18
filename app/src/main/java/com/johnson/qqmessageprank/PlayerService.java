package com.johnson.qqmessageprank;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.johnson.qqmessageprank.Utils.Const;

public class PlayerService extends Service {

    //play music
    private MediaPlayer mediaPlayer;
    private MonitorReceiver receiver;
    public PlayerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.wenson);
        mediaPlayer.setLooping(true);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mediaPlayer.start();

        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        //send wakeup  broadcast
        registerWakeUpReceiver();//注册传唤来的 广播 重启 此 服务
        registerHomeKeyReceiver();
        Intent intent=new Intent();//发送 监听 广播 唤醒 监听服务
        intent.setAction(Const.Monitor_RECEIVER_ACTION);
        sendBroadcast(intent);
        super.onDestroy();
    }


    private  void registerWakeUpReceiver(){

        receiver= new MonitorReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Const.WAKEUP_RECEIVER_ACTION);
        registerReceiver(receiver, filter);

    }
    private HomeWatcherReceiver mHomeKeyReceiver = null;

    private void registerHomeKeyReceiver() {

        mHomeKeyReceiver = new HomeWatcherReceiver();
        IntentFilter homeFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);

        registerReceiver(mHomeKeyReceiver, homeFilter);
    }
}
