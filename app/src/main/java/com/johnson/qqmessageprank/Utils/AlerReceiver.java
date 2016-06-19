package com.johnson.qqmessageprank.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;

import com.johnson.qqmessageprank.PlayerService;
import com.johnson.qqmessageprank.PrankGuid;

public class AlerReceiver extends BroadcastReceiver {
    public AlerReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String action=intent.getAction();
        if ("volume_max".equals(action)){
            Log.i("TAG", "你好 Action ---->");
          AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            int maxvolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

            int crrunent=audioManager.getStreamVolume( AudioManager.STREAM_MUSIC );
            if(crrunent<maxvolume) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxvolume, AudioManager.FLAG_PLAY_SOUND);

            }

        } else {
            Intent i = new Intent(context, PrankGuid.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }

    }
}
