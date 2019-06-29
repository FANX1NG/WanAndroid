package com.fanxing.wanandroid.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 繁星
 */
public abstract class BaseFragment extends Fragment implements UIOperation, HttpCallback {
    /** 管理Fragment的Activity */
    public BaseActivity mActivity;
    /**  是否初始化过布局 */
    protected boolean isViewInitiated;
    /** 当前界面是否可见 */
    protected boolean isVisibleToUser;
    /** 是否加载过数据 */
    protected boolean isDataInitiated;
    private Unbinder bind;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 初始化Activity对象
        mActivity = (BaseActivity) getActivity();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated=true;
        //加载数据
        prepareFetchData();
    }

    /**
     * 界面可见时调用
     * @param isVisibleToUser 用户是否可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser){
            prepareFetchData();
        }
    }
    public void prepareFetchData() {
        prepareFetchData(false);
    }

    /**
     * 判断懒加载条件
     *
     * @param forceUpdate 强制更新，好像没什么用？
     */
    public void prepareFetchData(boolean forceUpdate) {
        //如果 视图界面可见了 且 初始化过布局了 且 （如果没有加载过数据 或者 要强制更新数据）  就进行懒加载
        boolean b = !isDataInitiated || forceUpdate;
        if (isVisibleToUser && isViewInitiated && b) {
            loadData();
            isDataInitiated = true;
        }
    }
    /**
     * 懒加载
     */
    protected abstract void loadData();

    /** Fragment显示的布局 */
    public View mRoot;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRoot == null) {
            mRoot = inflater.inflate(getLayoutRes(),null);
            //ButterKnife绑定
            bind = ButterKnife.bind(this, mRoot);
            initView();
            initListener();
            initData();

        } else {
            // 解除mRoot与其父控件的关系
            unbindWidthParent(mRoot);
            bind = ButterKnife.bind(this, mRoot);
        }

        return mRoot;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //销毁时清空视图
        this.mRoot = null;
        //内存溢出检测
        RefWatcher refWatcher = MyApp.getRefWatcher(mActivity);
        refWatcher.watch(this);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除绑定 防止内存溢出
        if (bind!=null) {
            bind.unbind();
        }
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

    /**
     * Toast消息
     * @param text 显示的内容
     */
    public void showToast(String text) {
        Global.showToast(text);
    }
    /**http请求成功*/
    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        //空实现
    }
    /**http请求失败*/
    @Override
    public void onHttpError(int reqType, String error) {
        //统一处理错误信息
        showToast("请求失败:"+error);
    }

}
