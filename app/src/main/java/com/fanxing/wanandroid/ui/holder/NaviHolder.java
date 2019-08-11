package com.fanxing.wanandroid.ui.holder;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanxing.wanandroid.R;
import com.fanxing.wanandroid.base.Global;
import com.fanxing.wanandroid.base.ui.BaseAdapterLV;
import com.fanxing.wanandroid.base.ui.BaseHolderLV;
import com.fanxing.wanandroid.model.bean.NaviBean;
import com.fanxing.wanandroid.ui.activity.WebViewActivity;

public class NaviHolder extends BaseHolderLV<NaviBean.DataBean.ArticlesBean> {

    private TextView mTvTitle;
    private ConstraintLayout mConstraintLayout;

    public NaviHolder(Context context, ViewGroup parent, BaseAdapterLV<NaviBean.DataBean.ArticlesBean> adapter, int position, NaviBean.DataBean.ArticlesBean bean) {
        super(context, parent, adapter, position, bean);
    }

    @Override
    protected View onCreateView(Context context, ViewGroup parent) {

        View item = Global.inflate(R.layout.item_navi, parent);
        mConstraintLayout = item.findViewById(R.id.item_navi);
        mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra(WebViewActivity.WEB_VIEW_TITLE, bean.getTitle());
                intent.putExtra(WebViewActivity.WEB_VIEW_URL, bean.getLink());
                context.startActivity(intent);
            }
        });
        mTvTitle = item.findViewById(R.id.tv_title);
        return item;
    }

    @Override
    protected void onRefreshView(NaviBean.DataBean.ArticlesBean bean, int position) {
        mTvTitle.setText(bean.getTitle());
    }
}
