package com.example.vladimir.mapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    EditText et_name;
    EditText et_email;
    EditText et_number;
    TextView tv_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        et_email = findViewById(R.id.et_email);
        et_name = findViewById(R.id.et_name);
        et_number = findViewById(R.id.et_number);
        tv_error = findViewById(R.id.tv_error);
    }

    public void save(View view) {
        if (!et_email.getText().toString().equals("") && et_number.getText().length() != 0 && !et_email.getText().toString().equals("")) {
            Intent intent = new Intent(SecondActivity.this, FirstActivity.class);
            intent.putExtra("number", et_number.getText().toString());
            intent.putExtra("name", et_name.getText().toString());
            intent.putExtra("email", et_email.getText().toString());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        } else {
            tv_error.setText("PLEASE FILL ALL THE FIELDS");
        }
    }

    public void decline(View view) {
        Toast.makeText(this,"Edition declined", Toast.LENGTH_SHORT).show();
        finish();
    }
}
