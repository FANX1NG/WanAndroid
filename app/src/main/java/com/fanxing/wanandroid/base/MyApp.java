package com.fanxing.wanandroid.base;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * @author 繁星
 */
public class MyApp extends Application {

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        //全局公共类初始化
        Global.init(this);
        //Leakcanar内存溢出检测  初始化
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        } else {
            refWatcher = LeakCanary.install(this);
        }
    }

    /**
     * Leakcanar内存溢出检测 由Activity获得一个引用观察者对象来监测
     *
     * @param context
     * @return
     */
    public static RefWatcher getRefWatcher(Context context) {
        MyApp myApp = (MyApp) context.getApplicationContext();
        return myApp.refWatcher;
    }
}
