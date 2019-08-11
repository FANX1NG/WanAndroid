package com.fanxing.wanandroid.presenter;

import com.fanxing.wanandroid.base.HttpCallback;

/**
 * @author 繁星
 */
public class TreeFragmentPresenter extends BasePresenter {
    public TreeFragmentPresenter(HttpCallback callback) {
        super(callback);
    }

    public void getTree(){
        manager.getTree(mCallback,mCompositeDisposable);
    }
}
