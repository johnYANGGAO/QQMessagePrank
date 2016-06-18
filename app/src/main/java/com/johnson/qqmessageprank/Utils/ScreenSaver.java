package com.johnson.qqmessageprank.Utils;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

/**
 * Created by johnsmac on 6/18/16.
 */
public class ScreenSaver {

    public  static  void unlockScreen(Activity context){

        context.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }
}
