package com.fanxing.wanandroid.protocol;


import com.google.gson.JsonObject;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author 繁星
 */
public interface RetrofitService {
    /**
     * BaseUrl
     */
    String HOST_URL = "https://www.wanandroid.com/";
    /**
     * 表示请求的类型，这是获取首页头部信息的请求类型
     */
    int HTTP_GET_ARTICLE_LIST = 1;
    /**
     * 登录数据类型
     */
    int HTTP_POST_LOGIN = 2;
    /**
     * 首页Banner类型
     */
    int HTTP_GET_BANNER = 3;
    /**
     * 顶置文章类型
     */
    int HTTP_GET_TOP = 4;
    /**
     * 首页最新项目类型
     */
    int HTTP_GET_HOME_PROJECT = 5;
    /**
     * 搜索热词类型
     */
    int HTTP_GET_HOT_KEY = 6;
    /**
     * 搜索结果类型
     */
    int HTTP_POST_QUERY = 7;



    /**
     * 请求首页文章
     *
     * @param page 页码
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<JsonObject> getArticleList(@Path("page") int page);


    /**
     * 登陆
     * http://www.wanandroid.com/user/login
     *
     * @param namePassword 用户名和密码
     * @return 登陆数据
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<JsonObject> getLoginData(@FieldMap Map<String, String> namePassword);

    /**
     * 首页Banner
     *
     * @return 首页Banner数据
     */
    @GET("banner/json")
    Observable<JsonObject> getBanneData();

    /**
     * 置顶文章
     *
     * @return 置顶文章数据
     */
    @GET("article/top/json")
    Observable<JsonObject> getTopData();

    /**
     * 最新项目
     *
     * @param page 页码
     * @return 最新项目数据
     */
    @GET("article/listproject/{page}/json")
    Observable<JsonObject> getHomeProject(@Path("page") int page);

    /**
     * 搜索热词
     *
     * @return 搜索热词数据
     */
    @GET("hotkey/json")
    Observable<JsonObject> getHotKey();

    /**
     * 搜索结果
     * @param page 页码
     * @param k 搜索内容
     * @return 搜索结果数据
     */
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    Observable<JsonObject> getQueryData(@Path("page") int page, @Field("k") String k);
}

