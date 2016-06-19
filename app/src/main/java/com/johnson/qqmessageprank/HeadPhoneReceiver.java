package com.johnson.qqmessageprank;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;
import android.view.KeyEvent;

public class HeadPhoneReceiver extends BroadcastReceiver {
    public HeadPhoneReceiver() {
    }
    private AudioManager audioManager;
    @Override
    public void onReceive(Context context, Intent intent) {
        // 获得Action
        String intentAction = intent.getAction();
        // 获得KeyEvent对象
        KeyEvent keyEvent = intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);

        Log.i("TAG", "你好 Action ---->" + intentAction + "  事件是KeyEvent----->" + keyEvent.toString());

        if (Intent.ACTION_MEDIA_BUTTON.equals(intentAction)) {

            audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            int maxvolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxvolume, AudioManager.FLAG_PLAY_SOUND);
        }
    }
}
