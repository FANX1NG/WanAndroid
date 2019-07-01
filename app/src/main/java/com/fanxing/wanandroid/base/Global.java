package com.fanxing.wanandroid.base;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * 全局公共类, 封装：屏幕宽高获取，单位转换，主线程运行等
 * @author 繁星
 */
public class Global {

    public static Context mContext;
    public static float mDensity;
    public static int mScreenHeight;
    public static int mScreenWidth;

    public static void init(Context context) {
        mContext = context;
        initScreenSize();
    }
    private static void initScreenSize() {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        mDensity = dm.density;
        mScreenHeight = dm.heightPixels;
        mScreenWidth = dm.widthPixels;
    }

    /**
     * dp转换px
     * @param dp dp参数
     * @return
     */
    public static int dp2px(int dp) {
        return (int) (dp * mDensity);
    }
    /**
     * dp转换px
     * @param pxValue px参数
     * @return
     */
    public static float px2dp(float pxValue) {
        return (pxValue/ mDensity);
    }
    /**
     * 创建一个shape
     *
     *
     * @param shape
     * @param strokeWidth 边框宽度(px)
     * @param roundRadius 圆角半径(px)
     * @param strokeColor 边框颜色
     * @param fillColor   内部填充颜色
     * @return GradientDrawable
     */
    public static GradientDrawable createShape(int shape, int strokeWidth, int roundRadius, int strokeColor, int fillColor) {
        GradientDrawable gd = new GradientDrawable();
        gd.setShape(shape);
        gd.setColor(fillColor);
        gd.setCornerRadius(roundRadius);
        gd.setStroke(strokeWidth, strokeColor);
        return gd;
    }

    /**
     * 创建按钮文字点击样式
     *
     * @param normal  正常样式
     * @param pressed 按下样式
     * @param focused 焦点样式
     * @param unable  不可用样式
     * @return ColorStateList
     */
    public static ColorStateList createColorStateList(int normal, int pressed, int focused, int unable) {
        int[] colors = new int[]{pressed, focused, normal, focused, unable, normal};
        int[][] states = new int[6][];
        states[0] = new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled};
        states[1] = new int[]{android.R.attr.state_enabled, android.R.attr.state_focused};
        states[2] = new int[]{android.R.attr.state_enabled};
        states[3] = new int[]{android.R.attr.state_focused};
        states[4] = new int[]{android.R.attr.state_window_focused};
        states[5] = new int[]{};
        return new ColorStateList(states, colors);
    }

    /**
     * 创建按钮点击样式
     *
     * @param unSelected 未点击样式
     * @param selected 点击样式
     * @return StateListDrawable
     */
    public static StateListDrawable createStateListDrawable(GradientDrawable unSelected, GradientDrawable selected) {
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed}, selected);
        drawable.addState(new int[]{-android.R.attr.state_pressed}, unSelected);
        return drawable;
    }

    public static View inflate(int layoutResID, ViewGroup parent) {
        return LayoutInflater.from(mContext).inflate(layoutResID, parent, false);
    }

    public static View inflate(int layoutResID) {
        return inflate(layoutResID, null);
    }
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static Handler getMainHandler() {
        return mHandler;
    }

    private static Toast mToast;
    /**
     * 判断当前线程是否是主线程
     * @return true表示当前是在主线程中运行
     */
    public static boolean isUIThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static void runOnUIThread(Runnable run) {
        if (isUIThread()) {
            run.run();
        } else {
            mHandler.post(run);
        }
    }
    /**
     * 可以在子线程中调用
     * @param msg toast内容
     */
    public static void showToast(final String msg) {
        runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
                }
                mToast.setText(msg);
                mToast.show();
            }
        });
    }
}
