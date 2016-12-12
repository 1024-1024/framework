package com.zwl.animation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static android.content.ContentValues.TAG;

/**
 * Created by weilongzhang on 16/12/12.
 */

public class BezierView extends View {

    private Paint mPaint;
    private Point mStartPoint,mEndPoint, mAssistPoint;
    private Path mPath;

    public BezierView(Context context) {
        super(context);
        init();
    }

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mStartPoint = new Point(300, 600);
        mEndPoint = new Point(900, 600);
        mPath = new Path();
        mAssistPoint = new Point(600, 900);

        // 抗锯齿
        mPaint.setAntiAlias(true);
        // 防抖动
        mPaint.setDither(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mAssistPoint.x = (int) event.getX();
                mAssistPoint.y = (int) event.getY();
                Log.i(TAG, "assistPoint.x = " + mAssistPoint.x);
                Log.i(TAG, "assistPoint.Y = " + mAssistPoint.y);
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.BLACK);
        // 笔宽
        mPaint.setStrokeWidth(5);
        // 空心
        mPaint.setStyle(Paint.Style.STROKE);
        // 重置路径
        mPath.reset();
        // 起点
        mPath.moveTo(mStartPoint.x, mStartPoint.y);
        // 重要的就是这句
        mPath.quadTo(mAssistPoint.x, mAssistPoint.y, mEndPoint.x, mEndPoint.y);
        // 画路径
        canvas.drawPath(mPath, mPaint);
        // 画辅助点
        canvas.drawPoint(mAssistPoint.x, mAssistPoint.y, mPaint);
    }
}
