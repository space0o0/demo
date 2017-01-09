package com.example.space.test.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 学习使用ViewDragHelper用于滑动解决方案
 * Created by space on 2017/1/9.
 */

public class DragViewGroup extends FrameLayout {

    private String TAG = "DragViewGroup";

    ViewDragHelper mViewDragHelper;
    private View mMenuView, mMainView;//菜单view,主界面view
    private int mWidth;


    public DragViewGroup(Context context) {
        super(context);
        initView();
    }

    public DragViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();

    }

    public DragViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DragViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        Log.d(TAG, "onFinishInflate");

        /**
         * 按顺序将子view分发
         */
        mMenuView = getChildAt(0);
        mMainView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Log.d(TAG, "onSizeChanged");

        /**
         * 获取view宽度
         */
        mWidth = mMenuView.getMeasuredWidth();
    }

    private void initView() {

        Log.d(TAG, "initView");

        mViewDragHelper = ViewDragHelper.create(this, callback);
    }

    // TODO: 2017/1/9 拦截事件给mViewDragHelper

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    // TODO: 2017/1/9 重写computeScroll方法
    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    // TODO: 2017/1/9 创建回调
    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            // TODO: 2017/1/9 当触摸的child是mMainView时开始检测
            return child==mMainView;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            // TODO: 2017/1/9 处理垂直滑动
            return top;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            // TODO: 2017/1/9 处理水平滑动
            return left;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            // TODO: 2017/1/9 拖动结束后
            super.onViewReleased(releasedChild, xvel, yvel);

            if (mMainView.getLeft()<300){
                mViewDragHelper.smoothSlideViewTo(mMainView,0,0);
                ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
            }else{
                mViewDragHelper.smoothSlideViewTo(mMainView,500,0);
                ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
            }
        }
    };
}
