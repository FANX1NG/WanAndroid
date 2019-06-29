package com.fanxing.wanandroid.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 首页ViewPager的适配器
 * @author 繁星
 */
public class HomeFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;
    private String[] mTabTitle;

    public HomeFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] tabTitle) {
        super(fm);
        mFragmentList = fragmentList;
        mTabTitle = tabTitle;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitle[position];
    }
}
