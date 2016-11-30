package com.example.space.test.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 *
 * Created by space on 16/7/13.
 */
public class FlashTextView extends TextView {

    Paint mPaint, mPaint1, mPaint2;
    LinearGradient mLinearGradient;
    Matrix mGradientMatrix;
    float mTranslate;
    int mViewWidth = 0;

    public FlashTextView(Context context) {
        super(context);
        init();
    }

    public FlashTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlashTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FlashTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        mPaint1 = new Paint();
        mPaint2 = new Paint();
        mPaint1.setColor(Color.RED);
        mPaint2.setColor(Color.BLUE);
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
                        new int[]{Color.BLACK, 0xffffffff, Color.BLACK}, null, Shader.TileMode.CLAMP);

                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {



        //外边距
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint1);
        //内边距
        canvas.drawRect(5, 5, getMeasuredWidth() - 5, getMeasuredHeight() - 5, mPaint2);

        canvas.save();

        super.onDraw(canvas);

        canvas.restore();

        if (mGradientMatrix != null) {
            mTranslate+=mViewWidth/5;
            if (mTranslate>2*mViewWidth){
                mTranslate=-mTranslate;
            }
            mGradientMatrix.setTranslate(0,0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);
        }


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
