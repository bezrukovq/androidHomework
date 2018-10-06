package com.example.vladimir.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ItemInfoActivity extends AppCompatActivity {

    TextView name;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);
        name = findViewById(R.id.tv_profile_name);
        name.setText(getIntent().getStringExtra("name"));
        description = findViewById(R.id.tv_profile_description);
        description.setText(getIntent().getStringExtra("description"));
    }
}
