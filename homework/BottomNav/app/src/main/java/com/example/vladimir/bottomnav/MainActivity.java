package com.example.vladimir.bottomnav;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private EmptyFragment emptyFragment;
    private RecyclerFragment recyclerFragment;
    private ViewPFragment viewPFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emptyFragment = new EmptyFragment();
        viewPFragment = new ViewPFragment();
        recyclerFragment = new RecyclerFragment();
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.nav_recycler:
                        tr.replace(R.id.container, recyclerFragment);
                        break;
                    case R.id.nav_empty:
                        tr.replace(R.id.container, emptyFragment);
                        break;
                    case R.id.nav_pager:
                        tr.replace(R.id.container, viewPFragment);
                        break;
                }
                tr.commit();
                return true;
            }
        });
    }

}