package com.fanxing.wanandroid.presenter;

import com.fanxing.wanandroid.base.HttpCallback;

/**
 * HomeArticleFragment的presenter类
 * @author 繁星
 */
public class HomeArticleFragmentPresenter extends BasePresenter {
    public HomeArticleFragmentPresenter(HttpCallback callback) {
        super(callback);
    }

    public void getBannerData(){
        manager.getBannerData(mCallback,mCompositeDisposable);
    }
    public void getTopData(){
        manager.getTopData(mCallback,mCompositeDisposable);
    }
    public void getArticleList(int page){
        manager.getArticleList(mCallback,mCompositeDisposable,page);
    }
}
