package com.fanxing.wanandroid.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Message;

import com.fanxing.wanandroid.base.HttpCallback;
import com.fanxing.wanandroid.protocol.DataManager;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Presenter的基类
 * @author 繁星
 */
public abstract class BasePresenter implements Presenter {

    /** 数据请求管理类 */
    public DataManager manager;
    /** 视图销毁时，将还在运行的网络请求关闭 */
    public CompositeDisposable mCompositeDisposable;
    public HttpCallback mCallback;


    public BasePresenter(HttpCallback callback) {
        mCallback = callback;
    }


    @Override
    public void onCreate() {
        manager = new DataManager();
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mCompositeDisposable != null && mCompositeDisposable.size() > 0) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView() {
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }
}
