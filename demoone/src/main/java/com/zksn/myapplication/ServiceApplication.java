package com.zksn.myapplication;

import android.app.Application;

import org.xutils.x;


/**
 * Created by admin on 2016/10/12.
 */

public class ServiceApplication extends Application {
    @Override
    public void onCreate() {
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }
}
