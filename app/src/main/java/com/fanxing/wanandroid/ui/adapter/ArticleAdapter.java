package com.fanxing.wanandroid.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.fanxing.wanandroid.base.ui.BaseAdapterRV;
import com.fanxing.wanandroid.base.ui.BaseHolderRV;
import com.fanxing.wanandroid.model.bean.ArticleListBean;
import com.fanxing.wanandroid.model.bean.BannerBean;
import com.fanxing.wanandroid.model.bean.TopBean;
import com.fanxing.wanandroid.ui.holder.ArticleHolder;
import com.fanxing.wanandroid.ui.holder.BannerHolder;
import com.fanxing.wanandroid.ui.holder.TopHolder;

import java.util.List;

/**
 * 首页最新项目的RecyclerView适配器
 *
 * @author 繁星
 */
public class ArticleAdapter extends BaseAdapterRV {
    /** 列表项：banner类型 */
    private static final int ITEM_TYPE_BANNER = 0;
    /** 列表项：最新博文类型 */
    private static final int ITEM_TYPE_ARTICLE = 1;
    /** 列表项：顶置文章类型 */
    private static final int ITEM_TYPE_TOP = 2;

    public ArticleAdapter(Context context, List listData) {
        super(context, listData);
    }

    @Override
    public BaseHolderRV createViewHolder(Context context, ViewGroup parent, int viewType) {
        //判断数据类型来使用哪一种itemHolder
        switch (viewType) {
            case ITEM_TYPE_BANNER:
                return new BannerHolder(context, parent, this, viewType);
            case ITEM_TYPE_ARTICLE:
                return new ArticleHolder(context, parent, this, viewType);
            case ITEM_TYPE_TOP:
                return new TopHolder(context,parent,this,viewType);
            default:
                throw new IllegalStateException("找不到指定类型的Holder");
        }
    }

    /**
     * 取得当前要展示的列表条目的类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        Object bean = getItem(position);
        //banner
        if (bean instanceof BannerBean) {
            return ITEM_TYPE_BANNER;
        }
        //最新博文
        if (bean instanceof ArticleListBean.DataBean.DatasBean) {
            return ITEM_TYPE_ARTICLE;
        }
        //顶置文章
        if (bean instanceof TopBean.DataBean){
            return ITEM_TYPE_TOP;
        }

        throw new IllegalStateException("没有识别的类型:" + bean);
    }
}
