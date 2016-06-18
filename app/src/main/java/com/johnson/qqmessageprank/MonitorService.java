package com.johnson.qqmessageprank;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.johnson.qqmessageprank.Utils.Const;

public class MonitorService extends Service {

    private  MonitorReceiver receiver;
    public MonitorService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        registerHomeKeyReceiver();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        //send wakeup  broadcast

        registerMonitorReceiver();//注册 监听 广播 唤醒 监听服务
        Intent intent=new Intent();//发送 广播 重启 play 服务
        intent.setAction(Const.WAKEUP_RECEIVER_ACTION);
        sendBroadcast(intent);
        super.onDestroy();
    }


    private  void registerMonitorReceiver(){

        receiver= new MonitorReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Const.Monitor_RECEIVER_ACTION);
        registerReceiver(receiver, filter);

    }
    private HomeWatcherReceiver mHomeKeyReceiver = null;

    private void registerHomeKeyReceiver() {

        mHomeKeyReceiver = new HomeWatcherReceiver();
        IntentFilter homeFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);

        registerReceiver(mHomeKeyReceiver, homeFilter);
    }
}