package com.example.vladimir.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CallMain {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView mRecyclerView = findViewById(R.id.my_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.Adapter mAdapter = new MyAdapter(getMyList(), this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public ArrayList<City> getMyList() {
        ArrayList<City> a = new ArrayList<>();
        a.add(new City("Chrome", "desc desc desc desc desc desc"));
        a.add(new City("Realm", "desc desc desc desc desc desc"));
        a.add(new City("Android Studio", "desc desc desc desc desc desc"));
        a.add(new City("IDEA", "desc desc desc desc desc desc"));
        a.add(new City("Chrome", "desc desc desc desc desc desc"));
        a.add(new City("Realm", "desc desc desc desc desc desc"));
        a.add(new City("Android Studio", "desc desc desc desc desc desc"));
        a.add(new City("IDEA", "desc desc desc desc desc desc"));
        a.add(new City("Chrome", "desc desc desc desc desc desc"));
        a.add(new City("Realm", "desc desc desc desc desc desc"));
        a.add(new City("Android Studio", "desc desc desc desc desc desc"));
        a.add(new City("IDEA", "desc desc desc desc desc desc"));
        return a;
    }

    @Override
    public void callback(String name, String description) {
        Intent intent = new Intent(this, ItemInfoActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("description", description);
        startActivity(intent);
    }
}
