package com.example.space.test.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by space on 16/11/30.
 */

public class toolbarBehavior extends CoordinatorLayout.Behavior {

    private static final String TAG = "@#toolbarBehavior";

    /**
     * Default constructor for instantiating Behaviors.
     */
    public toolbarBehavior() {
    }

    /**
     * Default constructor for inflating Behaviors from layout. The Behavior will have
     * the opportunity to parse specially defined layout parameters. These parameters will
     * appear on the child view tag.
     *
     * @param context
     * @param attrs
     */
    public toolbarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "xxxx");
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
//        return super.layoutDependsOn(parent, child, dependency);
        Log.i(TAG, "layoutDependsOn");
        return dependency instanceof TextView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

        int offSet = dependency.getTop() - child.getTop();
        ViewCompat.offsetTopAndBottom(child, offSet);
        Log.i(TAG, "onDependentViewChanged:" + offSet);
        return true;
//        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {

        Log.i(TAG, "onStartNestedScroll");
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {

        Log.i(TAG, "onStopNestedScroll");
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }
}
