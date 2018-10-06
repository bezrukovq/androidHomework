package com.example.vladimir.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView mRecyclerView = findViewById(R.id.my_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.Adapter mAdapter = new MyAdapter(getMyList());
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
}
