package com.zwl.butterknife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.zwl.butterknife.annotation.BinderView;

public class MainActivity extends AppCompatActivity {

    @BinderView(R.id.tv)
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectView.bind(this);
        Toast.makeText(getApplicationContext(), ";;;" + textView, Toast.LENGTH_LONG).show();
    }
}
