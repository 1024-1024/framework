package com.zwl.test;

import android.app.Activity;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zwl.widget.MultiAutoBreakLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weilongzhang on 17/2/16.
 */

public class TestActivity extends Activity {

    private MultiAutoBreakLayout breakLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        breakLayout = (MultiAutoBreakLayout) findViewById(R.id.bl);
        breakLayout.setInterval(20);
        for (int i = 0; i < 20; i ++) {
            TextView textView = new TextView(this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
            textView.setText("waimian" + i);
            breakLayout.addView(textView);
        }
        LinearLayout ll_layout = (LinearLayout) findViewById(R.id.ll_layout);
        List<String> titles = new ArrayList<String>();
        titles.add("落(li)");
        titles.add("落(lia)");
        titles.add("落(lian)");
        titles.add("落(liang)");
        titles.add("落(shaung)");
        titles.add("落(lji)");
        titles.add("落(cha)");
        titles.add("落(ci)");
        titles.add("落(ljisjfe)");
        Paint paint = new Paint();
        int temp = 0;
        for (int i = 0; i < titles.size(); i++) {
            Rect rect = new Rect();
            paint.getTextBounds("落(shaung)", 0, "落(shaung)".length(), rect);
            int length = rect.width();
            if (length > temp) {
                temp = length;
            }
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                .WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.gravity = Gravity.CENTER_VERTICAL;
        for (int i = 0; i < titles.size(); i++) {
            LinearLayout layout = new LinearLayout(this);
            TextView textView = new TextView(this);
            textView.setText(titles.get(i));
            textView.setBackgroundResource(R.drawable.bottom_line);
            textView.setLayoutParams(lp);
            textView.setPadding(30, 0, 30, 0);
            textView.setGravity(Gravity.CENTER);
            ll_layout.addView(textView);
        }


    }


    public void click(View view) {
        int visible = this.findViewById(R.id.tv).getVisibility();
        this.findViewById(R.id.tv).setVisibility(visible == View.VISIBLE ? View.GONE : View.VISIBLE);

        breakLayout.requestLayout();
    }
}
