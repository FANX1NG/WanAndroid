package com.fanxing.wanandroid.protocol;

import android.content.Context;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 繁星
 */
public class RetrofitHelper {
    /**超时时间 5s*/
    private static final int DEFAULT_TIME_OUT = 5;
    private static final int DEFAULT_READ_TIME_OUT = 10;

    private static RetrofitHelper instance;
    /** Gson解析工厂 */
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    private Retrofit mRetrofit = null;

    /**
     * 单例模式
     * @return 返回一个单例
     */
    public static RetrofitHelper getInstance() {
        if (instance == null) {
            synchronized (RetrofitHelper.class) {
                if (instance == null) {
                    instance = new RetrofitHelper();
                }
            }
        }
        return instance;
    }

    private RetrofitHelper() {
        init();
    }

    private void init() {
        resetApp();
    }

    /**
     * 创建Retrofit的Builder对象
     */
    private void resetApp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //连接 超时时间
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        //写操作 超时时间
        builder.writeTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);
        //读操作 超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);

        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(RetrofitService.HOST_URL)
                //设置解析工厂
                .addConverterFactory(factory)
                //支持RxJava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 拿到一个接口实例
     * @return
     */
    public RetrofitService getServer(){
        return mRetrofit.create(RetrofitService.class);
    }


}
