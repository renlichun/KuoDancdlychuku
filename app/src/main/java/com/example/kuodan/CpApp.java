package com.example.kuodan;

import android.app.Application;
import android.content.Context;


/**
 * Created by SAMSUNG on 2018/6/14.
 */

public class CpApp extends Application {
    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();



    }


    public static Context getContext() {
        return mContext;
    }


}