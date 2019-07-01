package com.fanxing.wanandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义搜索词显示流式布局
 *
 * @author 繁星
 */
public class SearchKeyLayout extends ViewGroup {
    /** 用来记录描述有多少行View */
    private List<Line> mLines = new ArrayList<Line>();
    /** 用来记录当前已经添加到了哪一行 */
    private Line mCurrrenLine;
    private int mHorizontalSpace = 10;
    private int mVerticalSpace = 6;

    public SearchKeyLayout(Context context) {
        super(context);
    }

    public SearchKeyLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
    public void setSpace(int horizontalSpace, int verticalSpace){
        mHorizontalSpace = horizontalSpace;
        mVerticalSpace = verticalSpace;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //清空
        mLines.clear();
        mCurrrenLine = null;
        int layoutWidth = MeasureSpec.getSize(widthMeasureSpec);
        // 获取行最大的宽度
        int maxLineWidth = layoutWidth - getPaddingLeft() - getPaddingRight();

        //测量孩子
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View v = getChildAt(i);
            //如果孩子不可见
            if (v.getVisibility()==GONE){
                continue;
            }
            measureChild(v,widthMeasureSpec,heightMeasureSpec);
            // 往lines添加孩子
            if (mCurrrenLine ==null){
                // 说明还没有开始添加孩子
                mCurrrenLine = new Line(maxLineWidth, mHorizontalSpace);

                // 添加到 Lines中
                mLines.add(mCurrrenLine);
                // 行中一个孩子都没有
                mCurrrenLine.addView(v);
            }else {
                // 行中有孩子了
                boolean canAdd = mCurrrenLine.canAdd(v);
                if (canAdd){
                    mCurrrenLine.addView(v);
                }else {
                    //装不下，换行
                    mCurrrenLine = new Line(maxLineWidth,mHorizontalSpace);
                    mLines.add(mCurrrenLine);
                    mCurrrenLine.addView(v);
                }
            }
        }
        // 设置自己的宽度和高度
        int measuredWidth = layoutWidth;
        float allHeight = 0;
        for (int i = 0; i < mLines.size(); i++) {
            float mHeigth = mLines.get(i).mHeigth;
            //加行高
            allHeight +=mHeigth;
            //加行距
            if (i!=0){
                allHeight+=mVerticalSpace;
            }
        }
        int measuredHeight = (int) (allHeight + getPaddingTop() + getPaddingBottom() + 0.5f);
        setMeasuredDimension(measuredWidth,measuredHeight);

    }

    /**
     *   给Child 布局---> 给Line布局
     * @param b
     * @param i
     * @param i1
     * @param i2
     * @param i3
     */
    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

        int paddingLeft = getPaddingLeft();
        int offsetTop = getPaddingTop();
        for (int j = 0; j < mLines.size(); j++) {
            Line line = mLines.get(j);
            //给行布局
            line.layout(paddingLeft,offsetTop);
            offsetTop+=line.mHeigth+mVerticalSpace;
        }

    }

    class Line {
        /** 用来记录每一行有几个View */
        List<View> mViews = new ArrayList<>();
        private float mHorizontalSpace1;
        /** 行最大的宽度 */
        private float mMaxWidht;
        /** 已经使用了多少宽度 */
        private float mUsedWidth;
        /** 行的高度 */
        private float mHeigth;
        private float mMarginLeft;
        private float mMarginRight;
        private float mMarginTop;
        private float mMarginBottom;
        /**View和view之间的水平间距*/
        private float mHorizontalSpace;

        /**
         * 构造
         *
         * @param maxWidht        最大宽度
         * @param horizontalSpace 水平方向的空格
         */
        public Line(int maxWidht, int horizontalSpace) {
            mMaxWidht = maxWidht;
            mHorizontalSpace1 = horizontalSpace;
        }

        /**
         * 添加View，记录属性的变化
         *
         * @param view View
         */
        public void addView(View view) {
            int size = mViews.size();
            int viewWidth = view.getMeasuredWidth();
            int viewHeight = view.getMeasuredHeight();
            // 计算宽和高
            if (size == 0) {
                // 说明还没有添加View
                if (viewWidth > mMaxWidht) {
                    mUsedWidth = mMaxWidht;
                } else {
                    mUsedWidth = viewWidth;
                }
                mHeigth = viewHeight;
            } else {
                //多个view的情况
                mUsedWidth += viewWidth + mHorizontalSpace1;
                mHeigth = mHeigth < viewHeight ? viewHeight : mHeigth;
            }
            // 将View记录到集合中
            mViews.add(view);
        }

        /**
         * 用来判断是否可以将View添加到该line中
         *
         * @param view view
         * @return 是否可以添加
         */
        public boolean canAdd(View view) {
            int size = mViews.size();
            if (size == 0) {
                return true;
            }
            int viewdWidth = view.getMeasuredWidth();
            // 预计使用的宽度
            float planWidth = mUsedWidth + mHorizontalSpace1 + viewdWidth;
            if (planWidth > mMaxWidht) {
                return false;
            }
            return true;
        }

        /**
         * 给孩子布局
         *
         * @param offsetLeft
         * @param offsetTop
         */
        public void layout(int offsetLeft, int offsetTop) {

            int currentLeft = offsetLeft;
            int size = mViews.size();
            // 判断已经使用的宽度是否小于最大的宽度
            float extra = 0;
            float widhtAvg = 0;

            if (mMaxWidht > mUsedWidth) {
                extra = mMaxWidht - mUsedWidth;
                widhtAvg = extra / size;
            }

            for (int i = 0; i < size; i++) {
                View view = mViews.get(i);
                int viewHeight = view.getMeasuredHeight();
                int viewWidth = view.getMeasuredWidth();

                // 判断是否有富余
                if (widhtAvg!=0){
                    // 改变宽度
                    int newWidth = (int) (viewWidth + widhtAvg + 0.5f);
                    int widthMeasureSpec = MeasureSpec.makeMeasureSpec(newWidth, MeasureSpec.EXACTLY);
                    int heightMeasureSpec = MeasureSpec.makeMeasureSpec(viewHeight, MeasureSpec.EXACTLY);
                    view.measure(widthMeasureSpec,heightMeasureSpec);

                    viewWidth = view.getMeasuredWidth();
                    viewHeight = view.getMeasuredHeight();
                }

                // 布局
                int left = currentLeft;
                int top = (int) (offsetTop + (mHeigth - viewHeight) / 2 + 0.5f);
                // int top = offsetTop;
                int right = left + viewWidth;
                int bottom = top + viewHeight;
                view.layout(left,top,right,bottom);

                currentLeft+=viewWidth+mHorizontalSpace1;
            }
        }
    }
}
