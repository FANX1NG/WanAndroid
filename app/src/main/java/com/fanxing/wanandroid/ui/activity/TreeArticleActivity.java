package com.fanxing.wanandroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fanxing.wanandroid.R;
import com.fanxing.wanandroid.base.BaseActivity;
import com.fanxing.wanandroid.model.bean.TreeBean;
import com.fanxing.wanandroid.ui.adapter.TreeArticleViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 繁星
 */
public class TreeArticleActivity extends BaseActivity {
    public static final String TREE_INTENT = "tree";
    @BindView(R.id.btn_back)
    ImageButton btnBack;
    @BindView(R.id.tl_tree)
    TabLayout tlTree;
    @BindView(R.id.vp_tree)
    ViewPager vpTree;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private Unbinder mBind;
    private TreeBean.DataBean mDataBean;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_tree_article;
    }

    @Override
    public void initView() {
        //ButterKnife绑定
        mBind = ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent == null) {
            return;
        } else {
            mDataBean = (TreeBean.DataBean) intent.getSerializableExtra(TREE_INTENT);
        }
        tvTitle.setText(mDataBean.getName());
        TreeArticleViewPagerAdapter adapter = new TreeArticleViewPagerAdapter(getSupportFragmentManager(), mDataBean);
        vpTree.setAdapter(adapter);
        vpTree.setOffscreenPageLimit(4);
        tlTree.setupWithViewPager(vpTree);
    }

    @Override
    public void initData() {


    }

    @Override
    public void initListener() {
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v, int id) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定防止内存溢出
        mBind.unbind();
    }

}
