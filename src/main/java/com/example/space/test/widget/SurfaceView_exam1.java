package com.example.space.test.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by space on 2017/1/11.
 */

public class SurfaceView_exam1 extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private String TAG="SurfaceView_exam1";

    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private boolean mIsDrawing;
    private Paint mPaint;

    public SurfaceView_exam1(Context context) {
        super(context);
        initView();
    }

    public SurfaceView_exam1(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SurfaceView_exam1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();
    }

    /**
     * This is called immediately after the surface is first created.
     * Implementations of this should start up whatever rendering code
     * they desire.  Note that only one thread can ever draw into
     * a {@link Surface}, so you should not draw into the Surface here
     * if your normal rendering will be in another thread.
     *
     * @param holder The SurfaceHolder whose surface is being created.
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG,"surfaceCreated");
        mIsDrawing = true;
        new Thread(this).start();
    }

    /**
     * This is called immediately after any structural changes (format or
     * size) have been made to the surface.  You should at this point update
     * the imagery in the surface.  This method is always called at least
     * once, after {@link #surfaceCreated}.
     *
     * @param holder The SurfaceHolder whose surface has changed.
     * @param format The new PixelFormat of the surface.
     * @param width  The new width of the surface.
     * @param height The new height of the surface.
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * This is called immediately before a surface is being destroyed. After
     * returning from this call, you should no longer try to access this
     * surface.  If you have a rendering thread that directly accesses
     * the surface, you must ensure that thread is no longer touching the
     * Surface before returning from this function.
     *
     * @param holder The SurfaceHolder whose surface is being destroyed.
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i(TAG,"surfaceDestroyed");
        mIsDrawing = false;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (mIsDrawing) {

            draw();

            x += 1;

            y = (int) (100 * Math.sin(x * 2 * Math.PI / 180) + 400);

            mPath.lineTo(x, y);

        }
    }

    int x = 0;
    int y = 0;
    private Path mPath;


    private void draw() {
        Log.d("draw","start draw");
        try {
            canvas = surfaceHolder.lockCanvas();
            // TODO: 2017/1/11 draw somethings
            if (x>getWidth()){
                canvas.translate(-x+getWidth(),0);
            }

            canvas.drawColor(Color.WHITE);
            canvas.drawPath(mPath, mPaint);

        } catch (Exception e) {

        } finally {
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
