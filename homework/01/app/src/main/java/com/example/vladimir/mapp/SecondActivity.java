package com.example.vladimir.mapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    EditText etName;
    EditText etEmail;
    EditText etNumber;
    TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        etEmail = findViewById(R.id.et_email);
        etName = findViewById(R.id.et_name);
        etNumber = findViewById(R.id.et_number);
        tvError = findViewById(R.id.tv_error);
    }

    public void save(View view) {
        if (!etEmail.getText().toString().equals("") && etNumber.getText().length() != 0 && !etEmail.getText().toString().equals("")) {
            Intent intent = new Intent(SecondActivity.this, FirstActivity.class);
            intent.putExtra("number", etNumber.getText().toString());
            intent.putExtra("name", etName.getText().toString());
            intent.putExtra("email", etEmail.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        } else {
            tvError.setText("PLEASE FILL ALL THE FIELDS");
        }
    }

    public void decline(View view) {
        Toast.makeText(this, "Edition declined", Toast.LENGTH_SHORT).show();
        finish();
    }
}
