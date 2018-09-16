package com.example.vladimir.mapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {
    TextView tv_name;
    TextView tv_email;
    TextView tv_number;
    Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btn_send = findViewById(R.id.btn_send);
        btn_send.setEnabled(false);
        if (getIntent().getExtras() != null) {
            String data = getIntent().getExtras().getString("name");
            tv_name = findViewById(R.id.tv_name);
            tv_name.setText(data);
            tv_email = findViewById(R.id.tv_email);
            tv_email.setText(getIntent().getStringExtra("email"));
            tv_number = findViewById(R.id.tv_number);
            tv_number.setText(getIntent().getStringExtra("number"));
            btn_send.setEnabled(true);
        }
    }

    public void edit(View view) {
        Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
        startActivity(intent);
    }


    public void send(View view) {
        String text = "Name:\n" + tv_name.getText().toString() + "\n Email:\n" + tv_email.getText().toString() + "\n Number\n" + tv_number.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text); // текст отправки
        intent.putExtra(Intent.EXTRA_SUBJECT, "userInfo");
        startActivityForResult(Intent.createChooser(intent, "Share with"), 111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 111) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this,
                        "Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,
                        "Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
