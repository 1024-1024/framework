package com.zwl.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.findViewById(R.id.btn_1).setOnClickListener(this);
        this.findViewById(R.id.btn_2).setOnClickListener(this);
        this.findViewById(R.id.btn_3).setOnClickListener(this);
        this.findViewById(R.id.btn_4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1: {
                startActivity(new Intent(this, Demo1Activity.class));
                break;
            }
            case R.id.btn_2:
                startActivity(new Intent(this, Demo2Activity.class));
                break;
            case R.id.btn_3:
                startActivity(new Intent(this, Demo3Activity.class));
                break;
            case R.id.btn_4:
                startActivity(new Intent(this, Demo4Activity.class));
                break;

        }
    }
}
