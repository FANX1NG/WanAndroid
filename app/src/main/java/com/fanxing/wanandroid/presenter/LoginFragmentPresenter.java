package com.fanxing.wanandroid.presenter;


import com.fanxing.wanandroid.base.HttpCallback;


import java.util.Map;


/**
 * LoginFragment的Presenter
 * @author 繁星
 */
public class LoginFragmentPresenter extends BasePresenter {
    public LoginFragmentPresenter(HttpCallback callback) {
        super(callback);
    }


    /**
     * 请求登录 并获得登陆数据
     * @param namePassword 用户名和密码
     */
    public void getLoginData(Map<String,String> namePassword){
        manager.getLoginData(mCallback,mCompositeDisposable,namePassword);
    }

}
