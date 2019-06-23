package com.fanxing.wanandroid.base;

import android.os.Message;

/**
 * 网络回调接口类
 * @author 繁星
 */
public interface HttpCallback {
    /**
     * 请求成功
     *
     * @param reqType 不同请求的标识
     * @param msg     请求返回的实体对象
     */
    void onHttpSuccess(int reqType, Message msg);

    /**
     * 请求失败的回调
     *
     * @param reqType 不同请求的标识
     * @param error   请求出错信息
     */
    void onHttpError(int reqType, String error);

}
