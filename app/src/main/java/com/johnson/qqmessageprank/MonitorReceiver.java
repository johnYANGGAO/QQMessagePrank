package com.johnson.qqmessageprank;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.johnson.qqmessageprank.Utils.Const;

public class MonitorReceiver extends BroadcastReceiver {
    public MonitorReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        String action = intent.getAction();

        if (Const.Monitor_RECEIVER_ACTION.equals(action)) {
            Intent a = new Intent();
            a.setAction(Const.MONITOR_SERVICE_ACTION);
            a.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(a);


        } else if (Const.WAKEUP_RECEIVER_ACTION.equals(action)) {

            Intent b = new Intent();
            b.setAction(Const.PLAYER_SERVICE_ACTION);
            b.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(b);
        }


        throw new UnsupportedOperationException("Not yet implemented");
    }
}
