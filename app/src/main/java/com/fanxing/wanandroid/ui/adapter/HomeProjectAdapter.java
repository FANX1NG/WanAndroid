package com.fanxing.wanandroid.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.fanxing.wanandroid.base.ui.BaseAdapterRV;
import com.fanxing.wanandroid.base.ui.BaseHolderRV;
import com.fanxing.wanandroid.ui.holder.HomeProjectHolder;

import java.util.List;

/**
 * 首页最新项目的RecyclerView适配器
 * @author 繁星
 */
public class HomeProjectAdapter extends BaseAdapterRV {
    public HomeProjectAdapter(Context context, List listData) {
        super(context, listData);
    }

    @Override
    public BaseHolderRV createViewHolder(Context context, ViewGroup parent, int viewType) {
        return new HomeProjectHolder(context,parent,this,viewType);
    }
}
