package com.example.space.test.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.space.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by space on 2017/1/14.
 */

public class SpaceButton_exam1 extends RelativeLayout {
    private String TAG = "SpaceButton_exam1";
    Context mContext;
    ImageView center, top, left, right, bottom;
    List<ImageView> imageViews;

    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public SpaceButton_exam1(Context context) {
        this(context, null);
        init();

    }

    /**
     * Constructor that is called when inflating a view from XML. This is called
     * when a view is being constructed from an XML file, supplying attributes
     * that were specified in the XML file. This version uses a default style of
     * 0, so the only attribute values applied are those in the Context's Theme
     * and the given AttributeSet.
     * <p>
     * <p>
     * The method onFinishInflate() will be called after all children have been
     * added.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     * @see #View(Context, AttributeSet, int)
     */
    public SpaceButton_exam1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();

    }

    /**
     * Perform inflation from XML and apply a class-specific base style from a
     * theme attribute. This constructor of View allows subclasses to use their
     * own base style when they are inflating. For example, a Button class's
     * constructor would call this version of the super class constructor and
     * supply <code>R.attr.buttonStyle</code> for <var>defStyleAttr</var>; this
     * allows the theme's button style to modify all of the base view attributes
     * (in particular its background) as well as the Button class's attributes.
     *
     * @param context      The Context the view is running in, through which it can
     *                     access the current theme, resources, etc.
     * @param attrs        The attributes of the XML tag that is inflating the view.
     * @param defStyleAttr An attribute in the current theme that contains a
     *                     reference to a style resource that supplies default values for
     *                     the view. Can be 0 to not look for defaults.
     * @see #View(Context, AttributeSet)
     */
    public SpaceButton_exam1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    public void init() {

        View view = LayoutInflater.from(mContext).inflate(R.layout.spacebuttonlayout, this);
        center = (ImageView) view.findViewById(R.id.center);
        top = (ImageView) view.findViewById(R.id.top);
        left = (ImageView) view.findViewById(R.id.left);
        right = (ImageView) view.findViewById(R.id.right);
        bottom = (ImageView) view.findViewById(R.id.bottom);

        imageViews = new ArrayList<>();
        imageViews.add(left);
        imageViews.add(top);
        imageViews.add(right);
        imageViews.add(bottom);

        int i = 0;
        while (i == imageViews.size() - 1) {
            imageViews.get(i).setOnClickListener(new click());
            i++;
        }

        center.setOnClickListener(new click());

    }

    private void startAnim() {
        ObjectAnimator animator_center = ObjectAnimator.ofFloat(center, "alpha", 0.1F, 0.1F);

        ObjectAnimator animator_left = ObjectAnimator.ofFloat(imageViews.get(0), TRANSLATION_X, -200F);

        ObjectAnimator animator_top = ObjectAnimator.ofFloat(imageViews.get(1), TRANSLATION_Y, -200F);

        ObjectAnimator animator_right = ObjectAnimator.ofFloat(imageViews.get(2), TRANSLATION_X, 200F);

        ObjectAnimator animator_bottom = ObjectAnimator.ofFloat(imageViews.get(3), TRANSLATION_Y, 200F);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.setInterpolator(new BounceInterpolator());

        set.playTogether(animator_center, animator_left, animator_top, animator_right, animator_bottom);

        set.start();
    }

    class click implements OnClickListener {

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            startAnim();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        this.setLayoutParams(new LayoutParams(w, h));

        Log.i(TAG, "size changed-old" + oldw + "xxx" + oldh);
        Log.i(TAG, "size changed-new" + w + "xxx" + h);
    }
}
