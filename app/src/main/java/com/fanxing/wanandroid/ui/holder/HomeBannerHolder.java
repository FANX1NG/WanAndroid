package com.fanxing.wanandroid.ui.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.fanxing.wanandroid.R;
import com.fanxing.wanandroid.base.GlideImageLoader;
import com.fanxing.wanandroid.base.ui.BaseAdapterRV;
import com.fanxing.wanandroid.base.ui.BaseHolderRV;
import com.fanxing.wanandroid.model.bean.BannerBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * HomeArticleAdapter的Bannerholder类
 *
 * @author 繁星
 */
public class HomeBannerHolder extends BaseHolderRV<BannerBean> {


    @BindView(R.id.banner)
    Banner banner;
    private List<String> mImages;
    private List<String> mTitles;

    public HomeBannerHolder(Context context, ViewGroup parent, BaseAdapterRV adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_home_banner);
    }

    /**
     * 绑定视图
     */
    @Override
    public void onFindViews(View itemView) {
        ButterKnife.bind(this, itemView);
    }

    /**
     * 绑定数据、刷新数据
     *
     * @param bean
     * @param position
     */
    @Override
    protected void onRefreshView(BannerBean bean, int position) {
        //1.轮播图的显示
        initLooperImage(bean.getData());

    }

    /**
     * 轮播图的显示
     */
    private void initLooperImage(List<BannerBean.DataBean> beanList) {
        if (mImages == null || mTitles == null) {
            mImages = new ArrayList<>();
            mTitles = new ArrayList<>();
        }else{
            mImages.clear();
            mTitles.clear();

        }
        for (int i = 0; i < beanList.size(); i++) {
            BannerBean.DataBean dataBean = beanList.get(i);
            mImages.add(dataBean.getImagePath());
            mTitles.add(dataBean.getTitle());
        }

        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(mImages);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(mTitles);
        banner.start();

    }
}
