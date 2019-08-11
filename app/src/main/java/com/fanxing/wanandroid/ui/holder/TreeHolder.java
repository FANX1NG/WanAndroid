package com.fanxing.wanandroid.ui.holder;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanxing.wanandroid.R;
import com.fanxing.wanandroid.base.ui.BaseAdapterRV;
import com.fanxing.wanandroid.base.ui.BaseHolderRV;
import com.fanxing.wanandroid.model.bean.TreeBean;
import com.fanxing.wanandroid.ui.activity.TreeArticleActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 繁星
 */
public class TreeHolder extends BaseHolderRV<TreeBean.DataBean> {
    @BindView(R.id.tree_name)
    TextView treeName;
    @BindView(R.id.tv_children)
    TextView tvChildren;
    @BindView(R.id.item_tree)
    ConstraintLayout itemTree;
    private final Context mContext;

    public TreeHolder(Context context, ViewGroup parent, BaseAdapterRV adapter, int itemType) {
        super(context, parent, adapter, itemType, R.layout.item_tree_list);
        mContext = context;
    }

    @Override
    public void onFindViews(View itemView) {
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void onRefreshView(TreeBean.DataBean bean, int position) {
        treeName.setText(bean.getName());
        tvChildren.setText("");
        List<TreeBean.DataBean.ChildrenBean> children = bean.getChildren();
        for (int i = 0; i < children.size(); i++) {
            tvChildren.setText(tvChildren.getText() + children.get(i).getName() + "  ");
        }
        itemTree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TreeArticleActivity.class);
                intent.putExtra(TreeArticleActivity.TREE_INTENT,bean);
                mContext.startActivity(intent);
            }
        });
    }
}
