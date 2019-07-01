package com.fanxing.wanandroid.presenter;

import com.fanxing.wanandroid.base.HttpCallback;
import com.fanxing.wanandroid.model.bean.HotKeyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 繁星
 */
public class SearchActivityPresenter extends BasePresenter {
    public SearchActivityPresenter(HttpCallback callback) {
        super(callback);
    }

    public void getHotKey() {
        manager.getHotKey(mCallback, mCompositeDisposable);
    }

    public void getQuery(int page,String k){
        manager.getQueryData(mCallback,mCompositeDisposable,page,k);
    }
    public List<String> getStringData(List<HotKeyBean.DataBean> dataBeans) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < dataBeans.size(); i++) {
            HotKeyBean.DataBean dataBean = dataBeans.get(i);
            list.add(dataBean.getName());
        }
        return list;
    }
}
