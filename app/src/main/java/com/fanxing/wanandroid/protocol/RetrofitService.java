package com.fanxing.wanandroid.protocol;


import com.google.gson.JsonObject;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author 繁星
 */
public interface RetrofitService {
    /** BaseUrl */
    String HOST_URL = "https://www.wanandroid.com/";
    /** 表示请求的类型，这是获取首页头部信息的请求类型 */
    int HTTP_GET_ARTICLE_LIST = 1;
    /** 登录数据类型 */
    int HTTP_LOGIN = 2;

    /**
     * 请求首页文章
     * @param page 页码
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<JsonObject> getArticleList(@Path("page") int page);


    /**
     * 登陆
     * http://www.wanandroid.com/user/login
     * @param namePassword 用户名和密码
     * @return 登陆数据
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<JsonObject> getLoginData(@FieldMap Map<String, String> namePassword);
}
