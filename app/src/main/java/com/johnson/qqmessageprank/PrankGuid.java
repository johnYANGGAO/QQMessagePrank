package com.johnson.qqmessageprank;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.johnson.qqmessageprank.Utils.AlerReceiver;
import com.johnson.qqmessageprank.Utils.AlertHelper;
import com.johnson.qqmessageprank.Utils.Const;
import com.johnson.qqmessageprank.Utils.ScreenSaver;

public class PrankGuid extends AppCompatActivity {
    //volume controller
    private AudioManager audioManager;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prank_guid);
        ScreenSaver.unlockScreen(this);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxvolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxvolume, AudioManager.FLAG_PLAY_SOUND);
        registerHomeKeyReceiver(this);

        Intent intentplay = new Intent(this, PlayerService.class);
        intentplay.putExtra("play",Const.PLAYER_SERVICE_ACTION);
        startService(intentplay);

        Intent intentMoniter = new Intent(this, MonitorService.class);
        intentMoniter.putExtra("monitor", Const.MONITOR_SERVICE_ACTION);
        startService(intentMoniter);
        alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlerReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        AlertHelper.startAlert(alarmMgr,alarmIntent,this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterHomeKeyReceiver(this);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {

            case KeyEvent.KEYCODE_VOLUME_UP:

                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:

                return true;

            case KeyEvent.KEYCODE_BACK:

                return true;
            case KeyEvent.KEYCODE_MENU:

                return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private static HomeWatcherReceiver mHomeKeyReceiver = null;

    private static void registerHomeKeyReceiver(Context context) {

        mHomeKeyReceiver = new HomeWatcherReceiver();
        final IntentFilter homeFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);

        context.registerReceiver(mHomeKeyReceiver, homeFilter);
    }

    private static void unregisterHomeKeyReceiver(Context context) {

        if (null != mHomeKeyReceiver) {
            context.unregisterReceiver(mHomeKeyReceiver);
        }
    }
}
