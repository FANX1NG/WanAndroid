package com.fanxing.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author 繁星
 */
public abstract class BaseFragment extends Fragment implements UIOperation {
    /** 管理Fragment的Activity */
    public BaseActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 初始化Activity对象
        mActivity = (BaseActivity)getActivity();
    }

    /** Fragment显示的布局 */
    public View mRoot;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        if (mRoot == null) {
            mRoot = inflater.inflate(getLayoutRes(), null);

            initView();
            initListener();
            initData();

        } else {
            // 解除mRoot与其父控件的关系
            unbindWidthParent(mRoot);
        }
        return mRoot;
    }

    /**
     * 设置点击事件
     * @param v 点击控件
     */
    @Override
    public void onClick(View v) {
        onClick(v,v.getId());
    }

    /**
     * 解除父子控件关系
     *
     * @param view
     */
    public void unbindWidthParent(View view) {
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
    }

}
