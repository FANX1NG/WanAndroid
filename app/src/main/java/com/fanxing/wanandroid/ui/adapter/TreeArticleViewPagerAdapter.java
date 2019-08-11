package com.fanxing.wanandroid.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fanxing.wanandroid.model.bean.TreeBean;
import com.fanxing.wanandroid.ui.fragment.TreeArticleFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 繁星
 */
public class TreeArticleViewPagerAdapter extends FragmentStatePagerAdapter {

    private static TreeBean.DataBean mDataBean;

    public TreeArticleViewPagerAdapter(FragmentManager fm, TreeBean.DataBean dataBean) {
        super(fm);
        mDataBean = dataBean;
    }

    @Override
    public Fragment getItem(int i) {
        return TreeArticleFragment.newInstance(mDataBean.getChildren().get(i).getId());
    }

    @Override
    public int getCount() {
        return mDataBean.getChildren().size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        List<String> titleList = new ArrayList<>();
        for (int i = 0; i < mDataBean.getChildren().size(); i++) {
            titleList.add(mDataBean.getChildren().get(i).getName());
        }
        return titleList.get(position);
    }
}
