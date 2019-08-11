package com.fanxing.wanandroid.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.fanxing.wanandroid.base.ui.BaseAdapterRV;
import com.fanxing.wanandroid.base.ui.BaseHolderRV;
import com.fanxing.wanandroid.ui.holder.TreeArticleHolder;

import java.util.List;

public class TreeArticleAdapter extends BaseAdapterRV {
    public TreeArticleAdapter(Context context, List listData) {
        super(context, listData);
    }

    @Override
    public BaseHolderRV createViewHolder(Context context, ViewGroup parent, int viewType) {
        return new TreeArticleHolder(context,parent,this,viewType);
    }
}
