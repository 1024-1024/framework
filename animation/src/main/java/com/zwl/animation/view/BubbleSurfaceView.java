package com.zwl.animation.view;

/**
 * Created by weilongzhang on 17/3/14.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.why.litesuits.android.log.Log;
import com.zwl.animation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weilongzhang on 17/3/14.
 */

public class  BubbleSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;
    private DrawThread thread;
    private List<BubbleModel> bubbles;
    private int bubbleScaleCount = 10;
    private int bubbleUpCount = 10;

    public BubbleSurfaceView(Context context) {
        this(context, null);
    }

    public BubbleSurfaceView(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public BubbleSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**设置画布  背景透明*/
        this.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        mSurfaceHolder.setFormat(PixelFormat.TRANSPARENT);
        setZOrderOnTop(true);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("zwl", "WH:" + BubbleSurfaceView.this.getMeasuredWidth() + ";" +
                BubbleSurfaceView.this.getMeasuredHeight());
        if (bubbles == null) {
            bubbles = new ArrayList<BubbleModel>();
        }
        bubbles.clear();
        for (int i = 0; i < bubbleScaleCount; i++) {
            BubbleModel model = new BubbleModel(BubbleSurfaceView.this.getMeasuredWidth(),
                    BubbleSurfaceView.this.getMeasuredHeight(), BubbleModel.BUBBLE_SCALE, i);
            bubbles.add(model);
        }
        for (int i = 0; i < bubbleUpCount; i++) {
            BubbleModel model = new BubbleModel(BubbleSurfaceView.this.getMeasuredWidth(),
                    BubbleSurfaceView.this.getMeasuredHeight(), BubbleModel.BUBBLE_UP, i);
            bubbles.add(model);
        }

        setCircleColors(R.color.color_fecf05, R.color.color_fee36c);
        start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
    }

    public void setCircleColors(int color1, int color2) {
        if (bubbles != null && !bubbles.isEmpty()) {
            for (BubbleModel model : bubbles) {
                model.setColors(color1, color2);
            }
        }
    }

    public void start() {
        thread = new DrawThread();
        thread.start();
    }


    private class DrawThread extends Thread {

        boolean isRun = true;

        @Override
        public void run() {
            super.run();
            while (true) {
                Canvas canvas = null;
                try {
                    synchronized (mSurfaceHolder) {
                        canvas = mSurfaceHolder.lockCanvas();
                        /**清除画面*/
                        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                        /**对所有bubble进行遍历绘制*/
                        for (int i = 0; i < bubbles.size(); i++) {
                            bubbles.get(i).draw(canvas, mPaint);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (canvas != null) {
                        mSurfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}

