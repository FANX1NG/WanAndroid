package com.fanxing.wanandroid.ui.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanxing.wanandroid.R;
import com.fanxing.wanandroid.base.ui.BaseAdapterRV;
import com.fanxing.wanandroid.base.ui.BaseHolderRV;
import com.fanxing.wanandroid.model.bean.ArticleListBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * HomeArticleAdapter的Articleholder类
 *
 * @author 繁星
 */
public class HomeArticleHolder extends BaseHolderRV<ArticleListBean.DataBean.DatasBean> {
    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_chapter)
    TextView tvChapter;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.tv_niceDate)
    TextView tvNiceDate;

    public HomeArticleHolder(Context context, ViewGroup parent, BaseAdapterRV adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_home_list);
    }

    @Override
    public void onFindViews(View itemView) {
        ButterKnife.bind(this, itemView);
        //设置item的Top标签为透明
        tvTop.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onRefreshView(ArticleListBean.DataBean.DatasBean bean, int position) {
        //为item设置数据
        tvChapter.setText(bean.getSuperChapterName() + "/" + bean.getChapterName());
        tvTitle.setText(bean.getTitle());
        tvAuthor.setText(bean.getAuthor());
        tvNiceDate.setText(bean.getNiceDate());
    }
}
