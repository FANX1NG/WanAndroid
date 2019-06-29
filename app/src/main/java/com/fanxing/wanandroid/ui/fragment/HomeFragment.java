package com.fanxing.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanxing.wanandroid.R;
import com.fanxing.wanandroid.base.BaseFragment;
import com.fanxing.wanandroid.ui.adapter.HomeFragmentAdapter;
import com.fanxing.wanandroid.util.LogUtil;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 首页
 *
 * @author 繁星
 */
public class HomeFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.segmentTabLayout)
    SegmentTabLayout segmentTabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private String[] mTabTitle = new String[]{"最新博文", "最新项目"};
    private List<Fragment> mFragmentList = new ArrayList<>();

    @Override
    protected void loadData() {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        initTab();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v, int id) {

    }

    private void initTab() {
        //添加两个Fragment到List中
        mFragmentList.add(new HomeArticleFragment());
        mFragmentList.add(new HomeProjectFragment());
        //新建ViewPager适配器
        HomeFragmentAdapter homeFragmentAdapter = new HomeFragmentAdapter(getChildFragmentManager(), mFragmentList, mTabTitle);
        //ViewPager绑定适配器
        viewPager.setAdapter(homeFragmentAdapter);
        //segmentTabLayout设置tab；
        segmentTabLayout.setTabData(mTabTitle);
        //监听TabLayout切换时，ViewPager也进行切换
        segmentTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
                LogUtil.e(position+"  onTabSelect");
            }

            @Override
            public void onTabReselect(int position) {
                LogUtil.e(position+"  onTabReselect");
            }
        });
        //监听ViewPager，TabLayout也进行切换
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                segmentTabLayout.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
