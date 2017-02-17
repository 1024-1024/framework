package com.zwl.test;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zwl.widget.MultiAutoBreakLayout;

/**
 * Created by weilongzhang on 17/2/16.
 */

public class TestActivity extends Activity {

    private MultiAutoBreakLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        layout = (MultiAutoBreakLayout) this.findViewById(R.id.layout);
        layout.post(new Runnable() {
            @Override
            public void run() {
                TextView textView = new TextView(getApplication());
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                        .WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setText
                        ("jfaoiwejfpoaiwjefoajweofijawoeifjwoieijfoiwejfoawijfaoiwejfpoaiwjefoajweofijawoeifjwoieijfoiwejfoawi");
                textView.setPadding(30, 30, 30, 30);
                textView.setBackgroundColor(Color.RED);
                Log.d("zwl", "(textView.getPaint().measureText(textView.getText().toString()):" +
                        (textView.getPaint().measureText(textView.getText().toString())));
                Log.d("zwl", "textView.getPaddingLeft():" + textView.getPaddingLeft());
                int l = (int) (textView.getPaint().measureText(textView.getText().toString()) +
                        textView.getPaddingLeft() + textView.getPaddingRight());
                if (l > 1090) {
                    int count = 0;
                    for (int i = 0; i < textView.getText().toString().length(); i ++) {
                        count += textView.getPaint().measureText(textView.getText().toString(), i, i+1);
                        if (count == (l - textView.getPaddingLeft() - textView.getPaddingRight())) {

                        }
                    }

                }
                layout.addView(textView);

                TextView textView1 = new TextView(getApplication());
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                        .WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView1.setText("jfaoiwejfpoaiwjefoajwe");
                textView1.setPadding(10, 10, 10, 10);
                textView1.setBackgroundColor(Color.GREEN);
                layout.addView(textView1);
            }
        });

    }
}
