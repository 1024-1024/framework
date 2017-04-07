package com.zwl.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zwl.widget.AutoBreakLayout;
import com.zwl.widget.MultiAutoBreakLayout;

public class MainActivity extends AppCompatActivity {

    private AutoBreakLayout autoBreakLayout;
    private LinearLayout lll;
    private ScrollView scrollView;
    private MultiAutoBreakLayout breakLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lll = (LinearLayout) this.findViewById(R.id.lll);
        autoBreakLayout = (AutoBreakLayout) this.findViewById(R.id.AutoBreakLayout);
//        scrollView = (ScrollView) this.findViewById(R.id.activity_main);
//        scrollView.setFillViewport(true);


    }
    public void addText(View view) {

    }

    public void add(View view) {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
        textView.setText("waimian");
        autoBreakLayout.addView(textView);
    }


}
