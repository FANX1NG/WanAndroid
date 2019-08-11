package com.fanxing.wanandroid.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.fanxing.wanandroid.base.ui.BaseAdapterRV;
import com.fanxing.wanandroid.base.ui.BaseHolderRV;
import com.fanxing.wanandroid.ui.holder.QueryHolder;

import java.util.List;

/**
 * 搜索结果的RecyclerView适配器
 * @author 繁星
 */
public class QueryAdapter extends BaseAdapterRV {
    public QueryAdapter(Context context, List listData) {
        super(context, listData);
    }

    @Override
    public BaseHolderRV createViewHolder(Context context, ViewGroup parent, int viewType) {
        return new QueryHolder(context,parent,this,viewType);
    }
}
