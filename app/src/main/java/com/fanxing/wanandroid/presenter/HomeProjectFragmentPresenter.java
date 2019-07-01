package com.fanxing.wanandroid.presenter;

import com.fanxing.wanandroid.base.HttpCallback;

/**
 * HomeProjectFragment的presenter类
 * @author 繁星
 */
public class HomeProjectFragmentPresenter extends BasePresenter {
    public HomeProjectFragmentPresenter(HttpCallback callback) {
        super(callback);
    }
    /**请求首页最新项目数据*/
    public void getHomeProject(int page){
        manager.getHomeProject(mCallback,mCompositeDisposable,page);
    }
}
