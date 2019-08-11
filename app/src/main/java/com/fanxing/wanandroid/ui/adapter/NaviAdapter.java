package com.fanxing.wanandroid.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanxing.wanandroid.R;
import com.fanxing.wanandroid.base.Global;
import com.fanxing.wanandroid.base.ui.BaseAdapterLV;
import com.fanxing.wanandroid.base.ui.BaseHolderLV;
import com.fanxing.wanandroid.model.bean.NaviBean;
import com.fanxing.wanandroid.ui.holder.NaviHolder;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class NaviAdapter extends BaseAdapterLV<NaviBean.DataBean.ArticlesBean> implements StickyListHeadersAdapter {


    public NaviAdapter(Context context, List<NaviBean.DataBean.ArticlesBean> listData) {
        super(context, listData);
    }

    @Override
    public BaseHolderLV<NaviBean.DataBean.ArticlesBean> createViewHolder(Context context, ViewGroup parent, NaviBean.DataBean.ArticlesBean bean, int position) {
        return new NaviHolder(context,parent,this,position,bean);
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        View headView = null;
        if (convertView == null) {
            convertView = Global.inflate(R.layout.item_navi_category_header, parent);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.tv_navi_category);
        textView.setText(listData.get(position).getChapterName());
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return listData.get(position).getChapterId();
    }
}
