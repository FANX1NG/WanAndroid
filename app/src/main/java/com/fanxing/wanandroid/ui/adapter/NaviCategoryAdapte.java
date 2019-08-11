package com.fanxing.wanandroid.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.fanxing.wanandroid.base.ui.BaseAdapterRV;
import com.fanxing.wanandroid.base.ui.BaseHolderRV;
import com.fanxing.wanandroid.model.bean.NaviBean;
import com.fanxing.wanandroid.ui.fragment.NaviFragment;
import com.fanxing.wanandroid.ui.holder.NaviCategoryHolder;

import java.util.List;

/**
 * @author 繁星
 */
public class NaviCategoryAdapte extends BaseAdapterRV<NaviBean.DataBean> {

    private final NaviFragment mNaviFragment;

    public NaviCategoryAdapte(Context context, List<NaviBean.DataBean> listData, NaviFragment naviFragment) {
        super(context, listData);
        mNaviFragment = naviFragment;
    }

    @Override
    public BaseHolderRV<NaviBean.DataBean> createViewHolder(Context context, ViewGroup parent, int viewType) {
        return new NaviCategoryHolder(context,mNaviFragment,parent,this,viewType);
    }
    /** 选中的列表项位置，需要高亮显示 */
    private int mCurrentPosition = 0;

    public int getCurrentPosition() {
        return mCurrentPosition;
    }

    /** 高亮显示position位置的item */
    public void checkPosition(int position) {
        this.mCurrentPosition = position;
        notifyDataSetChanged();
    }
}
