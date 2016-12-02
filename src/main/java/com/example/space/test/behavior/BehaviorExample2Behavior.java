package com.example.space.test.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by space on 16/12/2.
 */

public class BehaviorExample2Behavior extends CoordinatorLayout.Behavior {

    /**
     * Default constructor for inflating Behaviors from layout. The Behavior will have
     * the opportunity to parse specially defined layout parameters. These parameters will
     * appear on the child view tag.
     *
     * @param context
     * @param attrs
     */
    public BehaviorExample2Behavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        /**
         * child为绑定的view,dependency为发生变化的view
         */
        return dependency instanceof TextView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        /**
         * 当dependency位置发生改变时,计算两个控件水平的距离
         * 再设置child的位移
         */
        int offSet = (int) (dependency.getX() - child.getX());
        ViewCompat.offsetLeftAndRight(child, offSet);

        return super.onDependentViewChanged(parent, child, dependency);
    }
}
