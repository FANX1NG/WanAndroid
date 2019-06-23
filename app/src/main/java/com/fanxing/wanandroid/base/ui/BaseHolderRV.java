package com.fanxing.wanandroid.base.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 封装适配器中的getView方法：填充布局，查找子控件，刷新子控件显示
 * @author 繁星
 */
public abstract class BaseHolderRV<T> extends RecyclerView.ViewHolder {

    public Context context;
    /** 指RecyclerView */
    public ViewGroup parent;

    /** RecyclerView的适配器 */
    public BaseAdapterRV<T> adapter;

    /** 列表项位置 */
    public int position;

    /** 列表项实体 */
    public T bean;

    /** 点击事件 */
    private View.OnClickListener mOnRootClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            onItemClick(itemView, position, bean);
        }
    };

    public BaseHolderRV(Context context, ViewGroup parent,
                        BaseAdapterRV<T> adapter,
                        int itemType, int layoutId) {
        super(LayoutInflater.from(context).inflate(layoutId, parent, false));
        this.context=context;
        this.parent=parent;
        this.adapter=adapter;

        onFindViews(super.itemView);

        // 设置列表项点击事件监听
        super.itemView.setOnClickListener(mOnRootClickListener);
    }
    /**
     * 列表项点击事件
     *
     * @param itemView 列表项根布局
     * @param position  列表项位置
     * @param bean 列表项对应的javabean
     */
    protected void onItemClick(View itemView, int position, T bean){
        // empty
    }
    /**
     * 查找item中的子控件
     *
     * @param itemView
     * @return
     */
    public abstract void onFindViews(View itemView);
    /**
     * 更新javabean及位置，刷新子控件的显示
     *
     * @param bean
     * @param position
     */
    protected abstract void onRefreshView(T bean, int position);

    /**
     * 刷新item子控件显示
     * @param bean
     * @param position
     */
    public void refreshView(T bean, int position) {
        this.bean = bean;
        this.position = position;

        onRefreshView(bean, position);
    }
}
