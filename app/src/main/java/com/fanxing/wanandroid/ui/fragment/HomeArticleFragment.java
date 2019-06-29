package com.fanxing.wanandroid.ui.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanxing.wanandroid.R;
import com.fanxing.wanandroid.base.BaseFragment;
import com.fanxing.wanandroid.model.bean.ArticleListBean;
import com.fanxing.wanandroid.model.bean.BannerBean;
import com.fanxing.wanandroid.model.bean.TopBean;
import com.fanxing.wanandroid.presenter.HomeArticleFragmentPresenter;
import com.fanxing.wanandroid.protocol.RetrofitService;
import com.fanxing.wanandroid.ui.adapter.ArticleAdapter;
import com.fanxing.wanandroid.util.LogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 首页最新文章
 * @author 繁星
 */
public class HomeArticleFragment extends BaseFragment {
    @BindView(R.id.rv_article)
    RecyclerView rvArticle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ArticleAdapter mAdapter;
    private HomeArticleFragmentPresenter mPresenter;
    private List<Object> datas;
    private int pageNo = -1;
    private int mHeaderSize = 0;


    @Override
    protected void loadData() {
        refreshLayout.autoRefresh();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home_article;
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
        rvArticle.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        //准备适配器
        mAdapter = new ArticleAdapter(getContext(), null);
        rvArticle.setAdapter(mAdapter);
    }

    /**
     * 初始化SmartRefreshLayout
     */
    private void initSmartRefreshLayout() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getBannerData();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getArticleList(pageNo+1);
            }
        });
    }

    @Override
    public void initData() {
        mPresenter = new HomeArticleFragmentPresenter(this);
        mPresenter.onCreate();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v, int id) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onStop();
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        //表示获取Banner的请求类型
        if (reqType == RetrofitService.HTTP_GET_BANNER) {
            BannerBean bannerBean = (BannerBean) msg.obj;
            datas = new ArrayList<>();
            //添加第一个元素
            datas.add(bannerBean);
            //设置网络请求到的数据
            mAdapter.setDatas(datas);
            //当请求Banner信息成功，再获取顶置文章信息
            mPresenter.getTopData();
            return;
        }
        //表示获取顶置文章的请求类型
        if (reqType == RetrofitService.HTTP_GET_TOP) {
            TopBean topBean = (TopBean) msg.obj;
            List<TopBean.DataBean> data = topBean.getData();
            datas.addAll(data);
            mHeaderSize = datas.size();
            mAdapter.setDatas(datas);
            //设置页码为0
            pageNo = 0;
            mPresenter.getArticleList(pageNo);
            return;
        }
        //表示获取最新文章的请求类型
        if (reqType == RetrofitService.HTTP_GET_ARTICLE_LIST) {
            ArticleListBean articleListBean = (ArticleListBean) msg.obj;
            List<ArticleListBean.DataBean.DatasBean> data = articleListBean.getData().getDatas();
            ArrayList<Object> pageDatas = new ArrayList<>();
            //依据what的值，觉得是否为下拉刷新
            if (msg.what == 0) {
                for (int i = 0; i < mHeaderSize; i++) {
                    pageDatas.add(datas.get(i));
                }
                pageDatas.addAll(data);
                datas = pageDatas;
                refreshLayout.finishRefresh(true);
            }else{
                //分页加载追加数据
                datas.addAll(data);
                refreshLayout.finishLoadMore(true);
                pageNo++;
            }
            mAdapter.setDatas(datas);

        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        super.onHttpError(reqType, error);
    }
}
