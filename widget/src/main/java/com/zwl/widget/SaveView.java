package com.zwl.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by weilongzhang on 17/4/14.
 */

public class SaveView extends View {
    public final static String TAG = "Example";
    private Paint mPaint = null;

    public SaveView(Context context) {
        super(context);
        mPaint = new Paint();
    }

    public SaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SaveView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint background = new Paint();
        Paint line = new Paint();
        line.setStrokeWidth(4);
        background.setColor(Color.GRAY);
        line.setColor(Color.RED);
        int px = 500;
        int py = 500;
        canvas.drawRect(0, 0, px, py, background);
        canvas.save();
        canvas.rotate(90, px / 2, py / 2);
        // 画一个向上的箭头
        canvas.drawLine(px / 2, 0, 0, py / 2, line);
        canvas.drawLine(px / 2, 0, px, py / 2, line);
        canvas.drawLine(px / 2, 0, px / 2, py, line);
        canvas.restore();
        canvas.drawCircle(px - 100, py - 100, 50, line);

        canvas.save();
        canvas.rotate(180, px/2, py/2);
        canvas.drawLine(0, 0, px/2, py/2, line);
        canvas.restore();
        canvas.drawCircle(px - 400, py - 400, 50, line);

    }
}
