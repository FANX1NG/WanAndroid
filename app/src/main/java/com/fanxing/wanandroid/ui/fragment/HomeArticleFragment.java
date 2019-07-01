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
import com.fanxing.wanandroid.model.bean.ArticleListBean;
import com.fanxing.wanandroid.model.bean.BannerBean;
import com.fanxing.wanandroid.model.bean.TopBean;
import com.fanxing.wanandroid.presenter.HomeArticleFragmentPresenter;
import com.fanxing.wanandroid.protocol.RetrofitService;
import com.fanxing.wanandroid.ui.adapter.HomeArticleAdapter;
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
 * 首页最新文章
 *
 * @author 繁星
 */
public class HomeArticleFragment extends BaseFragment {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_loading)
    TextView tvLoading;
    Unbinder unbinder;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private HomeArticleAdapter mAdapter;
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        //准备适配器
        mAdapter = new HomeArticleAdapter(getContext(), null);
        recyclerView.setAdapter(mAdapter);
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
                mPresenter.getArticleList(pageNo + 1);
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
    public void onDestroyView() {
        super.onDestroyView();
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
            } else {
                //分页加载追加数据
                datas.addAll(data);
                refreshLayout.finishLoadMore(true);
                //分页加载成功后，页码加一
                pageNo++;
            }
            //设置网络请求到的数据
            mAdapter.setDatas(datas);
            //初次加载数据成功后，将加载页隐藏
            if (tvLoading.getVisibility() == View.VISIBLE) {
                tvLoading.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        super.onHttpError(reqType, error);
        //数据请求失败，要将刷新控件关闭
        refreshLayout.finishLoadMore();
        refreshLayout.finishRefresh();
        //初次加载数据失败，将加载页内容换成加载失败
        if (tvLoading.getVisibility() == View.VISIBLE) {
            tvLoading.setText(R.string.loading_err_text);
        }
    }

}
