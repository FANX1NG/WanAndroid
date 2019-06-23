package com.fanxing.wanandroid.ui.activity;

import android.view.View;

import com.fanxing.wanandroid.R;
import com.fanxing.wanandroid.base.BaseActivity;
import com.fanxing.wanandroid.base.Global;
import com.fanxing.wanandroid.ui.fragment.HomeFragment;
import com.fanxing.wanandroid.ui.fragment.LoginFragment;
import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 繁星
 */
public class MainActivity extends BaseActivity {


    @BindView(R.id.navigation_bar)
    BottomTabBar navigationBar;
    private Unbinder mBind;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        //ButterKnife绑定
        mBind = ButterKnife.bind(this);
        //使用BottomTabBar绑定Fragment进行显示
        navigationBar.init(getSupportFragmentManager(), Global.mScreenWidth, Global.mScreenHeight)
//                .setImgSize(70, 70)
//                .setFontSize(14)
//                .setTabPadding(5, 0, 5)
//                .setChangeColor(Color.parseColor("#FF00F0"),Color.parseColor("#CCCCCC"))
                .addTabItem("第一项", R.mipmap.ic_launcher, LoginFragment.class)
                .addTabItem("第二项",  R.mipmap.ic_launcher, HomeFragment.class)
                .addTabItem("第三项",  R.mipmap.ic_launcher, LoginFragment.class)
                .isShowDivider(true);

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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定防止内存溢出
        mBind.unbind();
    }
}
