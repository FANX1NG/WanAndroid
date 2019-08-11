package com.fanxing.wanandroid.presenter;

import com.fanxing.wanandroid.base.HttpCallback;

/**
 * @author 繁星
 */
public class TreeArticleFragmentPresenter extends BasePresenter {
    public TreeArticleFragmentPresenter(HttpCallback callback) {
        super(callback);
    }
    public void getTreeArticle(int page,int cid){
        manager.getTreeArticle(mCallback,mCompositeDisposable,page,cid);
    }
}
