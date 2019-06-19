package com.fanxing.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fanxing.wanandroid.R;


/**
 * Activity基类
 * 封装：设置监听器，初始化数据，toast方法
 * @author 繁星
 */
public abstract class BaseActivity extends AppCompatActivity implements UIOperation {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        //初始化视图
        initView();
        //初始化监听
        initListener();
        //初始化数据
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //约定界面使用回退按钮默认使用btn_back作为id
            case R.id.btn_back:
                finish();
                break;
            default:
                onClick(v,v.getId());
                break;
        }
    }
}
