package com.fanxing.wanandroid.protocol;

import com.fanxing.wanandroid.base.HttpCallback;
import com.fanxing.wanandroid.model.bean.ArticleListBean;
import com.fanxing.wanandroid.model.bean.BannerBean;
import com.fanxing.wanandroid.model.bean.HomeProjectBean;
import com.fanxing.wanandroid.model.bean.HotKeyBean;
import com.fanxing.wanandroid.model.bean.LoginBean;
import com.fanxing.wanandroid.model.bean.QueryBean;
import com.fanxing.wanandroid.model.bean.TopBean;
import com.youth.banner.Banner;

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
        super.execute(mRetrofitService.getArticleList(page),RetrofitService.HTTP_GET_ARTICLE_LIST,callback, ArticleListBean.class,compositeDisposable,page);
    }

    /**
     * 请求登陆 并获取返回的数据
     * @param callback 回调接口
     * @param compositeDisposable 请求管理器
     * @param namePassword 用户名和密码
     */
    public void getLoginData(HttpCallback callback,CompositeDisposable compositeDisposable,Map<String,String>namePassword){
        super.execute(mRetrofitService.getLoginData(namePassword),RetrofitService.HTTP_POST_LOGIN,callback, LoginBean.class,compositeDisposable);
    }

    /**
     * 请求首页Banner数据
     * @param callback 回调接口
     * @param compositeDisposable 请求管理器
     */
    public void getBannerData(HttpCallback callback,CompositeDisposable compositeDisposable){
        super.execute(mRetrofitService.getBanneData(),RetrofitService.HTTP_GET_BANNER,callback, BannerBean.class,compositeDisposable);
    }

    /**
     * 请求首页顶置文章数据
     * @param callback 回调接口
     * @param compositeDisposable 请求管理器
     */
    public void getTopData(HttpCallback callback,CompositeDisposable compositeDisposable){
        super.execute(mRetrofitService.getTopData(),RetrofitService.HTTP_GET_TOP,callback, TopBean.class,compositeDisposable);
    }
    /**
     * 请求首页项目数据
     * @param callback  回调接口
     * @param compositeDisposable 请求管理器
     * @param page 页码
     */
    public void getHomeProject(HttpCallback callback, CompositeDisposable compositeDisposable, int page){
        super.execute(mRetrofitService.getHomeProject(page),RetrofitService.HTTP_GET_HOME_PROJECT,callback, HomeProjectBean.class,compositeDisposable,page);
    }

    /**
     * 请求搜索热词数据
     * @param callback 回调接口
     * @param compositeDisposable 请求管理器
     */
    public void getHotKey(HttpCallback callback,CompositeDisposable compositeDisposable){
        super.execute(mRetrofitService.getHotKey(),RetrofitService.HTTP_GET_HOT_KEY,callback, HotKeyBean.class,compositeDisposable);
    }

    /**
     * 请求搜索结果数据
     * @param callback 回调接口
     * @param compositeDisposable 请求管理器
     * @param page 页码
     * @param k 搜索哦内容
     */
    public void getQueryData(HttpCallback callback,CompositeDisposable compositeDisposable,int page,String k){
        super.execute(mRetrofitService.getQueryData(page,k),RetrofitService.HTTP_POST_QUERY,callback, QueryBean.class,compositeDisposable,page);
    }
}
