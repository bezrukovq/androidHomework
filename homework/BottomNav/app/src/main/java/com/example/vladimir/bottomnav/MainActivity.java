package com.example.vladimir.bottomnav;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private EmptyFragment eF;
    private RecyclerFragment rF;
    private ViewPFragment vF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eF = new EmptyFragment();
        vF = new ViewPFragment();
        rF = new RecyclerFragment();
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.nav_recycler:
                        item.setChecked(true);
                        tr.replace(R.id.container, rF);
                        break;
                    case R.id.nav_empty:
                        item.setChecked(true);
                        tr.replace(R.id.container, eF);
                        break;
                    case R.id.nav_pager:
                        tr.replace(R.id.container, vF);
                        item.setChecked(true);
                        break;
                }
                tr.commit();
                return false;
            }
        });
    }
}