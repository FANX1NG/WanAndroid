package com.fanxing.wanandroid.presenter;

import android.content.Intent;


/**
 * Presenter的接口，用于实现一些同步于View生命周期的方法
 * @author 繁星
 */
public interface Presenter {
    /** 视图调用onCreate时调用 */
    void onCreate();
    /** 视图调用onStart时调用 */
    void onStart();//暂时没用到
    /** 视图调用onDestroy时调用 */
    void onStop();
    /** 视图调用pause时调用 */
    void pause();//暂时没用到
    /** 依附视图时调用 */
    void attachView();//暂时没用到
    /** 对视图依附未来的意图时调用
     * @param intent 意图
     */
    void attachIncomingIntent(Intent intent);//暂时没用到
}
