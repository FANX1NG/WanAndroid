package com.fanxing.wanandroid.ui.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanxing.wanandroid.R;
import com.fanxing.wanandroid.base.BaseActivity;
import com.fanxing.wanandroid.base.Global;
import com.fanxing.wanandroid.model.bean.HomeProjectBean;
import com.fanxing.wanandroid.model.bean.HotKeyBean;
import com.fanxing.wanandroid.model.bean.QueryBean;
import com.fanxing.wanandroid.presenter.SearchActivityPresenter;
import com.fanxing.wanandroid.protocol.RetrofitService;
import com.fanxing.wanandroid.ui.adapter.HomeProjectAdapter;
import com.fanxing.wanandroid.ui.adapter.QueryAdapter;
import com.fanxing.wanandroid.util.LogUtil;
import com.fanxing.wanandroid.view.SearchKeyLayout;
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
 * 搜索页
 *
 * @author 繁星
 */
public class SearchActivity extends BaseActivity {

    List<Object> mDatas;
    int pageNo = -1;

    @BindView(R.id.searchkey_hot_key)
    SearchKeyLayout searchkeyHotKey;
    @BindView(R.id.searchkey_history)
    SearchKeyLayout searchkeyHistory;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.ll_search_key)
    LinearLayout llSearchKey;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_loading)
    TextView tvLoading;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.search_refresh_recycler)
    FrameLayout searchRefreshRecycler;
    private Unbinder mBind;
    private SearchActivityPresenter mPresenter;
    private QueryAdapter mAdapter;
    private boolean mOver;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        mBind = ButterKnife.bind(this);
        initSmartRefreshLayout();
        initRecyclerView();
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        //设置布局方式
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //准备适配器
        mAdapter = new QueryAdapter(this, null);
        recyclerView.setAdapter(mAdapter);
    }

    /**
     * 初始化SmartRefreshLayout
     */
    private void initSmartRefreshLayout() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getQuery(0, etSearch.getText().toString());
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (!mOver) {
                    mPresenter.getQuery(++pageNo, etSearch.getText().toString());
                } else {
                    showToast("没有更多了");
                    refreshLayout.finishLoadMore();
                }
            }
        });
    }


    private void displaySearchKey(SearchKeyLayout searchKeyLayout, List<String> mDatas) {

        for (int i = 0; i < mDatas.size(); i++) {
            final String data = mDatas.get(i);
            TextView tv = new TextView(this);
            tv.setText(data);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tv.setGravity(Gravity.CENTER);
            int paddingy = 2;
            int paddingx = 2;
            tv.setPadding(paddingx, paddingy, paddingx, paddingy);
            tv.setClickable(false);

            int shape = GradientDrawable.RECTANGLE;
            int radius = Global.dp2px(4);
            int strokeWeight = Global.dp2px(1);
            int stokeColor = getResources().getColor(R.color.text_color_gray);
            int stokeColor2 = getResources().getColor(R.color.green);

            GradientDrawable normalBg = Global.createShape(shape, strokeWeight, radius, stokeColor, Color.WHITE);
            GradientDrawable pressedBg = Global.createShape(shape, strokeWeight, radius, stokeColor2, getResources().getColor(R.color.green));
            StateListDrawable selector = Global.createStateListDrawable(normalBg, pressedBg);
            tv.setBackgroundDrawable(selector);
            ColorStateList colorStateList = Global.createColorStateList(getResources().getColor(R.color.text_color_gray), getResources().getColor(R.color.white), getResources().getColor(R.color.text_color_gray), getResources().getColor(R.color.white));
            tv.setTextColor(colorStateList);
            searchKeyLayout.setSpace(Global.dp2px(8), Global.dp2px(8));
            searchKeyLayout.setPadding(Global.dp2px(8), Global.dp2px(8),
                    Global.dp2px(8), Global.dp2px(8));
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView textView = (TextView) v;
                    String s = textView.getText().toString();
                    etSearch.setText(s);
                    etSearch.setSelection(s.length());
                }
            });
            searchKeyLayout.addView(tv);
        }
    }

    @Override
    public void initData() {
        mPresenter = new SearchActivityPresenter(this);
        mPresenter.onCreate();
        mPresenter.getHotKey();
    }

    @Override
    public void initListener() {
        btnSearch.setOnClickListener(this);
        etSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v, int id) {
        switch (id) {
            case R.id.btn_search:
                if (llSearchKey.getVisibility() == View.VISIBLE) {
                    llSearchKey.setVisibility(View.GONE);
                    searchRefreshRecycler.setVisibility(View.VISIBLE);
                    refreshLayout.autoRefresh();
                    InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    //隐藏软键盘 //
                    imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
                }
                break;
            case R.id.et_search:
                if (llSearchKey.getVisibility() == View.GONE) {
                    int height = llSearchKey.getHeight();
                    //调用ofFloat方法创建ObjectAnimator对象
                    ObjectAnimator animator = ObjectAnimator.ofFloat(llSearchKey, "translationY", -height, 0);
                    //设置插值器
                    animator.setInterpolator(new DecelerateInterpolator());
                    //为目标对象的动画状态变化设置监听器
                    animator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {
                            searchRefreshRecycler.setVisibility(View.GONE);
                            llSearchKey.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                    //设置动画的持续时间
                    animator.setDuration(500);
                    animator.start();

                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onHttpSuccess(int reqType, Message msg) {
        if (reqType == RetrofitService.HTTP_GET_HOT_KEY) {
            HotKeyBean hotKeyBean = (HotKeyBean) msg.obj;
            List<HotKeyBean.DataBean> dataBeans = hotKeyBean.getData();
            List<String> datas = mPresenter.getStringData(dataBeans);
            displaySearchKey(searchkeyHotKey, datas);
            return;
        }
        if (reqType == RetrofitService.HTTP_POST_QUERY) {
            QueryBean queryBean = (QueryBean) msg.obj;
            List<QueryBean.DataBean.DatasBean> queryData = queryBean.getData().getDatas();
            mOver = queryBean.getData().isOver();
            int total = queryBean.getData().getTotal();
            if (msg.what == 0) {
                if (total == 0) {
                    tvLoading.setText(R.string.query_null);
                } else {
                    mDatas = new ArrayList<>();
                    mDatas.clear();
                    mDatas.addAll(queryData);
                    refreshLayout.finishRefresh();
                    pageNo = 0;
                    //初次加载数据成功后，将加载页隐藏
                    if (tvLoading.getVisibility() == View.VISIBLE) {
                        tvLoading.setVisibility(View.GONE);
                    }
                }
            } else {
                mDatas.addAll(queryData);
                refreshLayout.finishLoadMore();
                pageNo++;

            }
            mAdapter.setDatas(mDatas);

        }
    }

    @Override
    public void onHttpError(int reqType, String error) {
        super.onHttpError(reqType, error);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定 防止内存溢出
        if (mBind != null) {
            mBind.unbind();
        }
        mPresenter.onStop();
    }

}
