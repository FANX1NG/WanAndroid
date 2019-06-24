package com.fanxing.wanandroid.protocol;

import com.fanxing.wanandroid.base.HttpCallback;
import com.fanxing.wanandroid.model.bean.ArticleListBean;
import com.fanxing.wanandroid.model.bean.LoginBean;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;

/**
 * 数据请求管理类
 * @author 繁星
 */
public class DataManager extends BaseProtocol{
    /** Retrofit接口实例 */
    private final RetrofitService mRetrofitService;

    public DataManager(){
        mRetrofitService = RetrofitHelper.getInstance().getServer();
    }

    /**
     * 请求首页文章数据
     * @param callback  回调接口
     * @param compositeDisposable 请求管理器
     * @param page 页码
     */
    public void getArticleList(HttpCallback callback, CompositeDisposable compositeDisposable, int page){
        super.execute(mRetrofitService.getArticleList(page),RetrofitService.HTTP_GET_ARTICLE_LIST,callback, ArticleListBean.class,compositeDisposable);
    }

    /**
     * 请求登陆 并获取返回的数据
     * @param callback 回调接口
     * @param compositeDisposable 请求管理器
     * @param namePassword 用户名和密码
     */
    public void getLoginData(HttpCallback callback,CompositeDisposable compositeDisposable,Map<String,String>namePassword){
        super.execute(mRetrofitService.getLoginData(namePassword),RetrofitService.HTTP_LOGIN,callback, LoginBean.class,compositeDisposable);
    }

}
