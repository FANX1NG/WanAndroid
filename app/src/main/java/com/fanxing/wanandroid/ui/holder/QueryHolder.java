package com.fanxing.wanandroid.ui.holder;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanxing.wanandroid.R;
import com.fanxing.wanandroid.base.ui.BaseAdapterRV;
import com.fanxing.wanandroid.base.ui.BaseHolderRV;
import com.fanxing.wanandroid.model.bean.QueryBean;
import com.fanxing.wanandroid.ui.activity.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 繁星
 */
public class QueryHolder extends BaseHolderRV<QueryBean.DataBean.DatasBean> {
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
    @BindView(R.id.item_home_list)
    ConstraintLayout itemHomeList;

    public QueryHolder(Context context, ViewGroup parent, BaseAdapterRV adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_home_list);
    }

    @Override
    public void onFindViews(View itemView) {
        ButterKnife.bind(this, itemView);
        //设置item的Top标签为透明
        tvTop.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onRefreshView(QueryBean.DataBean.DatasBean bean, int position) {
//为item设置数据
        tvChapter.setText(bean.getSuperChapterName() + "/" + bean.getChapterName());
        String title = bean.getTitle();
        Spanned spanned;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            spanned = Html.fromHtml(title, Html.FROM_HTML_MODE_LEGACY);
        } else {
            spanned = Html.fromHtml(title);
        }
        title = spanned.toString();
        String finalTitle = title;
        tvTitle.setText(spanned);
        tvAuthor.setText(bean.getAuthor());
        tvNiceDate.setText(bean.getNiceDate());
        itemHomeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra(WebViewActivity.WEB_VIEW_TITLE, finalTitle);
                intent.putExtra(WebViewActivity.WEB_VIEW_URL, bean.getLink());
                context.startActivity(intent);
            }
        });
    }
}
