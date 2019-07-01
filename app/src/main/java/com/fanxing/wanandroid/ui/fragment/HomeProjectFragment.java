package com.fanxing.wanandroid.ui.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanxing.wanandroid.R;
import com.fanxing.wanandroid.base.BaseFragment;
import com.fanxing.wanandroid.model.bean.HomeProjectBean;
import com.fanxing.wanandroid.presenter.HomeProjectFragmentPresenter;
import com.fanxing.wanandroid.protocol.RetrofitService;
import com.fanxing.wanandroid.ui.adapter.HomeArticleAdapter;
import com.fanxing.wanandroid.ui.adapter.HomeProjectAdapter;
import com.fanxing.wanandroid.util.LogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 首页最新项目
 *
 * @author 繁星
 */
public class HomeProjectFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_loading)
    TextView tvLoading;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private HomeProjectAdapter mAdapter;
    private HomeProjectFragmentPresenter mPresenter;
    private int pageNo = -1;
    private List<Object> mDatas;

    @Override
    protected void loadData() {
        refreshLayout.autoRefresh();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home_project;
    }

    @Override
    public void initView() {
        initSmartRefreshLayout();
        initRecyclerView();
    }
    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        //设置布局方式
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        //准备适配器
        mAdapter = new HomeProjectAdapter(getContext(), null);
        recyclerView.setAdapter(mAdapter);
    }

    /**
     * 初始化SmartRefreshLayout
     */
    private void initSmartRefreshLayout() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getHomeProject(0);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getHomeProject(pageNo + 1);
            }
        });
    }


    @Override
    public void initData() {
        mPresenter = new HomeProjectFragmentPresenter(this);
        mPresenter.onCreate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onStop();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v, int id) {

    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        if (reqType == RetrofitService.HTTP_GET_HOME_PROJECT){
            HomeProjectBean homeProjectBean = (HomeProjectBean) msg.obj;
            List<HomeProjectBean.DataBean.DatasBean> homeProjectData = homeProjectBean.getData().getDatas();
            if (msg.what==0){
                mDatas = new ArrayList<>();
                mDatas.clear();
                mDatas.addAll(homeProjectData);
                refreshLayout.finishRefresh();
                pageNo=0;

            }else{
                mDatas.addAll(homeProjectData);
                refreshLayout.finishLoadMore();
                pageNo++;
            }
            mAdapter. setDatas(mDatas);
            //初次加载数据成功后，将加载页隐藏
            if (tvLoading.getVisibility() == View.VISIBLE) {
                tvLoading.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        super.onHttpError(reqType, error);

    }
}
