package com.fanxing.wanandroid.ui.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;

import com.fanxing.wanandroid.R;
import com.fanxing.wanandroid.base.BaseFragment;
import com.fanxing.wanandroid.model.bean.NaviBean;
import com.fanxing.wanandroid.presenter.NaviFragmentPresenter;
import com.fanxing.wanandroid.protocol.RetrofitService;
import com.fanxing.wanandroid.ui.adapter.NaviAdapter;
import com.fanxing.wanandroid.ui.adapter.NaviCategoryAdapte;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class NaviFragment extends BaseFragment {
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.rv_chapter)
    RecyclerView rvChapter;
    @BindView(R.id.slhlv)
    StickyListHeadersListView slhlv;
    Unbinder unbinder;
    private NaviCategoryAdapte mNaviCategoryAdapte;
    private NaviFragmentPresenter mPresenter;
    private NaviAdapter mNaviAdapter;

    @Override
    protected void loadData() {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_navi;
    }

    @Override
    public void initView() {
        // 左侧列表
        rvChapter.setLayoutManager(new LinearLayoutManager(
                mActivity, LinearLayoutManager.VERTICAL,false));
        mNaviCategoryAdapte = new NaviCategoryAdapte(mActivity, null,this);
        rvChapter.setAdapter(mNaviCategoryAdapte);
        mNaviAdapter = new NaviAdapter(mActivity, null);
        slhlv.setAdapter(mNaviAdapter);

    }

    @Override
    public void initData() {
        mPresenter = new NaviFragmentPresenter(this);
        mPresenter.onCreate();
        mPresenter.getNaviData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onStop();
    }

    @Override
    public void initListener() {
        slhlv.setOnScrollListener(
                new AbsListView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(AbsListView view,int scrollState) {
                    }

                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem,
                                         int visibleItemCount, int totalItemCount) {
                        if (mNaviAdapter != null && mNaviAdapter.getCount() > 0) {
                            // 右侧列表第一个列表项所属的商品分类
                            int newCategoryId = mNaviAdapter.getItem(firstVisibleItem).getChapterId();

                            // 左侧列表当前选中的分类id
                            int oldCategoryId = mNaviCategoryAdapte.getItem(
                                    mNaviCategoryAdapte.getCurrentPosition()).getCid();

                            if (newCategoryId != oldCategoryId) {
                                // 2. 计算左侧列表以categoryId作为id的商品分类的列表项位置position
                                int position = mPresenter.getRecyclerViewPosition(newCategoryId);

                                // 3. 左侧列表选中位置为position列表项
                                mNaviCategoryAdapte.checkPosition(position);
                            }
                        }
                    }
                });
    }

    @Override
    public void onClick(View v, int id) {

    }
    /**取View层的Presenter 对象*/
    public NaviFragmentPresenter getPresenter() {
        return mPresenter;
    }
    /** 返回右侧的商品列表 */
    public StickyListHeadersListView getLvRightNavis() {
        return slhlv;
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        if (reqType == RetrofitService.HTTP_GET_NAVI){
            NaviBean naviBean = (NaviBean) msg.obj;
            mNaviCategoryAdapte.setDatas(naviBean.getData());
            mNaviAdapter.setDatas(mPresenter.transformShopDetailData(naviBean));
            return;
        }
    }
}
