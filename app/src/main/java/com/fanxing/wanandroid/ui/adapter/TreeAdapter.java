package com.fanxing.wanandroid.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.fanxing.wanandroid.base.ui.BaseAdapterRV;
import com.fanxing.wanandroid.base.ui.BaseHolderRV;
import com.fanxing.wanandroid.ui.holder.TreeHolder;

import java.util.List;

/**
 * 知识体系的RecyclerView适配器
 * @author 繁星
 */
public class TreeAdapter extends BaseAdapterRV {
    public TreeAdapter(Context context, List listData) {
        super(context, listData);
    }

    @Override
    public BaseHolderRV createViewHolder(Context context, ViewGroup parent, int viewType) {
        return new TreeHolder(context,parent,this,viewType);
    }
}
