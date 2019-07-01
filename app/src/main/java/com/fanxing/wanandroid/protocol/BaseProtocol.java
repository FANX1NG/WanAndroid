package com.fanxing.wanandroid.protocol;

import android.os.Message;

import com.fanxing.wanandroid.base.HttpCallback;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 访问网络的基类
 *
 * @author 繁星
 */
public class BaseProtocol {


    /**
     * 执行网络请求的方法
     *
     * @param call                Observable Call对象
     * @param reqType             请求类型
     * @param callback            请求回调接口
     * @param clazz               解析数据得到的实体对象
     * @param compositeDisposable 网络请求管理
     * @param what                1表示加载更多, 0表示普通加载 ，默认是普通加载数据
     * @param <T>                 泛型表示实体类
     */
    public <T> void execute(Observable<JsonObject> call, final int reqType,
                            final HttpCallback callback, final Class<T> clazz,
                            CompositeDisposable compositeDisposable, final int what) {
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {

                    private String mJsonStr;

                    @Override
                    public void onSubscribe(Disposable d) {
                        //将网络请求放到网络请求管理里面
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        //将获取到的JsonObject装换为String类型
                        mJsonStr = jsonObject.toString();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callback != null) {
                            callback.onHttpError(reqType, e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        try {
                            //将String类型的json数据装换为JSONObject类型 来获取errorCode判断是否成功获取数据
                            JSONObject jsonObject1 = new JSONObject(mJsonStr);
                            int errorCode = jsonObject1.getInt("errorCode");
                            if (errorCode == 0) {
                                //将json数据装换为放入实体类里
                                Gson gson = new Gson();
                                T t = gson.fromJson(mJsonStr, clazz);
                                if (callback != null) {
                                    Message msg = new Message();
                                    //实体类
                                    msg.obj = t;
                                    //数据类型
                                    msg.what = what;
                                    //回到成功获取数据的接口
                                    callback.onHttpSuccess(reqType, msg);
                                }
                            } else {
                                String error = jsonObject1.getString("errorMsg");
                                if (callback != null) {
                                    //回到失败获取数据的接口
                                    callback.onHttpError(reqType, error);
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public <T> void execute(Observable<JsonObject> call, final int reqType,
                            final HttpCallback callback, final Class<T> clazz, CompositeDisposable compositeDisposable) {
        execute(call, reqType, callback, clazz, compositeDisposable, -1);
    }
}
