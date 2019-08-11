package com.fanxing.wanandroid.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fanxing.wanandroid.R;
import com.fanxing.wanandroid.base.BaseFragment;
import com.fanxing.wanandroid.model.bean.TreeBean;
import com.fanxing.wanandroid.presenter.TreeFragmentPresenter;
import com.fanxing.wanandroid.protocol.RetrofitService;
import com.fanxing.wanandroid.ui.activity.SearchActivity;
import com.fanxing.wanandroid.ui.adapter.TreeAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

/**
 * @author 繁星
 */
public class TreeFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_loading)
    TextView tvLoading;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.btn_search)
    Button btnSearch;
    private TreeFragmentPresenter mPresenter;
    private TreeAdapter mAdapter;


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_tree;
    }

    @Override
    public void initView() {
        initSmartRefreshLayout();
        refreshLayout.setEnableLoadMore(false);
        initRecyclerView();
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        //设置布局方式
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        //准备适配器
        mAdapter = new TreeAdapter(getContext(), null);
        recyclerView.setAdapter(mAdapter);
    }

    /**
     * 初始化SmartRefreshLayout
     */
    private void initSmartRefreshLayout() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getTree();
            }
        });
    }

    @Override
    public void initData() {
        mPresenter = new TreeFragmentPresenter(this);
        mPresenter.onCreate();
        refreshLayout.autoRefresh();
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onStop();
    }

    @Override
    public void initListener() {
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v, int id) {
        switch (id) {
            case R.id.btn_search:
                Intent intent = new Intent(mActivity, SearchActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        if (reqType == RetrofitService.HTTP_GET_TREE) {
            TreeBean treeBean = (TreeBean) msg.obj;
            List<TreeBean.DataBean> treeBeanData = treeBean.getData();
            mAdapter.setDatas(treeBeanData);
            refreshLayout.finishRefresh();
            //初次加载数据成功后，将加载页隐藏
            if (tvLoading.getVisibility() == View.VISIBLE) {
                tvLoading.setVisibility(View.GONE);
            }
            return;
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        super.onHttpError(reqType, error);
        //数据请求失败，要将刷新控件关闭
        refreshLayout.finishRefresh();
        //初次加载数据失败，将加载页内容换成加载失败
        if (tvLoading.getVisibility() == View.VISIBLE) {
            tvLoading.setText(R.string.loading_err_text);
        }
    }


}
