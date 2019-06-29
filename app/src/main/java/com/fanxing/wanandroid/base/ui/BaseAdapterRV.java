package com.fanxing.wanandroid.base.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * 适配器基类封装(RecyclerView)
 * @author 繁星
 */
public abstract class BaseAdapterRV<T> extends RecyclerView.Adapter {
    private Context context;

    /** 列表显示的数据集合 */
    public List<T> listData;

    public BaseAdapterRV(Context context, List<T> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return createViewHolder(context, viewGroup, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        BaseHolderRV<T> baseHolder = (BaseHolderRV<T>) viewHolder;
        T bean = getItem(i);
        baseHolder.refreshView(bean, i);
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }

    /**
     * 创建holder对象
     *
     * @param context
     * @param parent
     * @return
     */
    public abstract BaseHolderRV<T> createViewHolder(
            Context context, ViewGroup parent, int viewType);

    /**
     * 获取列表项对应的实体对象
     */
    public T getItem(int i) {
        return listData.get(i);
    }

    /**
     * 刷新数据显示
     *
     * @param newData 要显示的新数据
     */
    public void setDatas(List<T> newData) {
        this.listData = newData;
        notifyDataSetChanged();
    }

    /**
     * 删除一个对象, 并刷新界面
     * @param bean
     */
    public void remove(T bean) {
        listData.remove(bean);
        notifyDataSetChanged();
    }
}
