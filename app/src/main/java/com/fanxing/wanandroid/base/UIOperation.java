package com.fanxing.wanandroid.base;

import android.view.View;

/**
 * @author 繁星
 */
public interface UIOperation extends View.OnClickListener {

    /** 返回布局文件
     * @return 返回布局文件
     */
    int getLayoutRes();

    /** 查找子控件 */
    void initView() ;

    /** 查找子控件 */
    void initData() ;
    /** 设置监听器 */
    void initListener() ;
    /**
     * 由子类处理点击事件
     * @param v 点击控件
     * @param id 点击控件的id
     */
    void onClick(View v, int id) ;
}
