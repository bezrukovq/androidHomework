package com.example.vladimir.mapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvEmail;
    TextView tvNumber;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnSend = findViewById(R.id.btn_send);
        btnSend.setEnabled(false);
    }

    public void edit(View view) {
        Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
        startActivityForResult(intent, 123);
    }


    public void send(View view) {
        String text = "Name:\n" + tvName.getText().toString() + "\n Email:\n" + tvEmail.getText().toString() + "\n Number\n" + tvNumber.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text); // текст отправки
        intent.putExtra(Intent.EXTRA_SUBJECT, "userInfo");
        startActivityForResult(Intent.createChooser(intent, "Share with"), 111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 111) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this,
                        "Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,
                        "Denied", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {
                if (data.getExtras() != null) {
                    tvName = findViewById(R.id.tv_name);
                    tvName.setText(data.getExtras().getString("name"));
                    tvEmail = findViewById(R.id.tv_email);
                    tvEmail.setText(data.getStringExtra("email"));
                    tvNumber = findViewById(R.id.tv_number);
                    tvNumber.setText(data.getStringExtra("number"));
                    btnSend.setEnabled(true);
                }
            } else {
                Toast.makeText(this,
                        "Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
