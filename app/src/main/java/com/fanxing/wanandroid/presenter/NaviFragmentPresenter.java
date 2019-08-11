package com.fanxing.wanandroid.presenter;

import com.fanxing.wanandroid.base.HttpCallback;
import com.fanxing.wanandroid.model.bean.NaviBean;

import java.util.ArrayList;
import java.util.List;

public class NaviFragmentPresenter extends BasePresenter {

    private List<NaviBean.DataBean.ArticlesBean> mNavidata;
    private NaviBean mNaviBean;

    public NaviFragmentPresenter(HttpCallback callback) {
        super(callback);
    }


    public void getNaviData(){
        manager.getNaviData(mCallback,mCompositeDisposable);
    }
    /**
     * 根据商品类别的id，获取右侧列表要滚动到的位置
     *
     * @param cid
     * @return
     */
    public int getListViewPosition(int cid) {
        for (int i = 0; i < mNavidata.size(); i++) {
            // 一个商品的javabean
            NaviBean.DataBean.ArticlesBean navis = mNavidata.get(i);
            if (cid == navis.getChapterId()) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 左侧列表中从哪个位置开始商品类别id为categoryId
     */
    public int getRecyclerViewPosition(int categoryId) {
        for (int i = 0; i < mNavidata.size(); i++) {
            NaviBean.DataBean bean = mNaviBean.getData().get(i);
            if (categoryId == bean.getCid()) {
                return i;
            }
        }
        return 0;
    }
    /**
     * 转换数据，以方便列表直接显示
     */
    public List<NaviBean.DataBean.ArticlesBean> transformShopDetailData(NaviBean naviBean) {
        mNaviBean = naviBean;
        mNavidata = new ArrayList<>();
        for (int i = 0; i < naviBean.getData().size(); i++) {
            NaviBean.DataBean dataBean = naviBean.getData().get(i);

            for (int j = 0; j < dataBean.getArticles().size(); j++) {
                NaviBean.DataBean.ArticlesBean articlesBean = dataBean.getArticles().get(j);
                mNavidata.add(articlesBean);
            }
        }

        return mNavidata;
    }
}
