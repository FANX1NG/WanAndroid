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
import com.fanxing.wanandroid.model.bean.TreeArticleBean;
import com.fanxing.wanandroid.model.bean.TreeBean;
import com.fanxing.wanandroid.presenter.TreeArticleFragmentPresenter;
import com.fanxing.wanandroid.protocol.RetrofitService;
import com.fanxing.wanandroid.ui.adapter.HomeProjectAdapter;
import com.fanxing.wanandroid.ui.adapter.TreeArticleAdapter;
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
 * @author 繁星
 */
public class TreeArticleFragment extends BaseFragment {

    public static final String ARTICLE_ID = "articleId";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_loading)
    TextView tvLoading;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private int articleId;
    private TreeArticleAdapter mAdapter;
    private TreeArticleFragmentPresenter mPresenter;
    private int pageNo = -1;
    private ArrayList<Object> mDatas;
    private boolean mOver;

    public static TreeArticleFragment newInstance(int articleId) {
        Bundle args = new Bundle();
        TreeArticleFragment fragment = new TreeArticleFragment();
        args.putInt(ARTICLE_ID, articleId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_tree_article;
    }

    @Override
    public void initView() {
        initSmartRefreshLayout();
        initRecyclerView();
        refreshLayout.autoRefresh();
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        //设置布局方式
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        //准备适配器
        mAdapter = new TreeArticleAdapter(getContext(), null);
        recyclerView.setAdapter(mAdapter);
    }

    /**
     * 初始化SmartRefreshLayout
     */
    private void initSmartRefreshLayout() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getTreeArticle(0, articleId);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (!mOver) {
                    mPresenter.getTreeArticle(++pageNo, articleId);
                } else {
                    showToast("没有更多了");
                    refreshLayout.finishLoadMore();
                }
            }
        });
    }


    @Override
    public void initData() {
        Bundle arguments = getArguments();
        articleId = arguments.getInt(ARTICLE_ID);
        mPresenter = new TreeArticleFragmentPresenter(this);
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
        if (reqType == RetrofitService.HTTP_GET_TREE_ARTICLE) {
            TreeArticleBean treeArticleBean = (TreeArticleBean) msg.obj;
            List<TreeArticleBean.DataBean.DatasBean> treeArticleDatas = treeArticleBean.getData().getDatas();
            mOver = treeArticleBean.getData().isOver();
            if (msg.what == 0) {
                if (mDatas == null) {
                    mDatas = new ArrayList<>();
                } else {
                    mDatas.clear();
                }
                mDatas.addAll(treeArticleDatas);
                refreshLayout.finishRefresh();
                pageNo = 0;

            } else {
                mDatas.addAll(treeArticleDatas);
                refreshLayout.finishLoadMore();
                pageNo++;
            }
            mAdapter.setDatas(mDatas);
            //初次加载数据成功后，将加载页隐藏
            if (tvLoading.getVisibility() == View.VISIBLE) {
                tvLoading.setVisibility(View.GONE);
            }
        }


    }
}
