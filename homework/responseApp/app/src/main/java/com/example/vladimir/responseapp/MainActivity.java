package com.example.vladimir.responseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getExtras() != null) {
            String text = getIntent().getExtras().get(Intent.EXTRA_TEXT).toString();
            tv_text = findViewById(R.id.tv_text);
            tv_text.setText(text);
            setResult(RESULT_OK);
        }
    }

    public void finish(View view) {
        finish();
    }
}
