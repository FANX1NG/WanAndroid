package com.fanxing.wanandroid.ui.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanxing.wanandroid.R;
import com.fanxing.wanandroid.base.Global;
import com.fanxing.wanandroid.base.ui.BaseAdapterRV;
import com.fanxing.wanandroid.base.ui.BaseHolderRV;
import com.fanxing.wanandroid.model.bean.NaviBean;
import com.fanxing.wanandroid.ui.adapter.NaviCategoryAdapte;
import com.fanxing.wanandroid.ui.fragment.NaviFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NaviCategoryHolder extends BaseHolderRV<NaviBean.DataBean> {


    private final NaviCategoryAdapte mAdapter;
    @BindView(R.id.tv_Navi_category)
    TextView tvNaviCategory;
    private final NaviFragment mNaviFragment;

    public NaviCategoryHolder(Context context, NaviFragment naviFragment, ViewGroup parent, BaseAdapterRV<NaviBean.DataBean> adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_navi_category);
        mAdapter = (NaviCategoryAdapte) super.adapter;
        mNaviFragment = naviFragment;
    }

    @Override
    public void onFindViews(View itemView) {
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void onRefreshView(NaviBean.DataBean bean, int position) {
        tvNaviCategory.setText(bean.getName());
        // 是否高亮显示
        int textColor = 0;
        int bgColor = 0;
        if (position == mAdapter.getCurrentPosition()) {
            // 选中的选项高亮显示
            textColor = R.color.item_text_selected;
            bgColor = R.color.white;
        } else { // 普通的列表项
            textColor = R.color.item_text_normal;
            bgColor = R.color.transparent;
        }
        tvNaviCategory.setTextColor(context.getResources().getColor(textColor));
        super.itemView.setBackgroundColor(context.getResources().getColor(bgColor));
    }

    @Override
    protected void onItemClick(View itemView, int position, NaviBean.DataBean bean) {
        //高亮显示某一列表项
        mAdapter.checkPosition(position);
        // 1. 获取点击的商品分类的categoryId
        int cid = bean.getCid();

        // 2. 右侧列表第几个位置开始以categoryId作为分类id
        int pos =  mNaviFragment.getPresenter().getListViewPosition(cid);

        // 3. 右侧列表滚动到pos位置
        mNaviFragment.getLvRightNavis().setSelection(pos);
    }
}
