package com.example.vladimir.bottomnav;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RecyclerFragment extends Fragment {

    Toolbar toolbar;
    AppAdapter appAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        RecyclerView mRecyclerView = view.findViewById(R.id.rv);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        appAdapter = new AppAdapter(new AppListDiffCallback());
        mRecyclerView.setAdapter(appAdapter);
        appAdapter.submitList(getMyList());
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.tb_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ArrayList<App> a = getMyList();
        switch (item.getItemId()) {
            case R.id.action_by_name:
                Collections.sort(a, new Comparator<App>() {
                    @Override
                    public int compare(App app, App app2) {
                        return app.getId() - app2.getId();
                    }
                });
                appAdapter.submitList(a);
                Toast.makeText(getContext(), "ID", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_by_pop:
                Collections.sort(a, new Comparator<App>() {
                    @Override
                    public int compare(App app, App t1) {
                        return app.getName().charAt(0) - t1.getName().charAt(0);
                    }
                });
                appAdapter.submitList(a);
                Toast.makeText(getContext(), "NAME", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

    public static ArrayList<App> getMyList() {
        ArrayList<App> a = new ArrayList<>();
        a.add(new App(R.drawable.chrome, "Chrome", 1, "desc desc desc desc desc desc"));
        a.add(new App(R.drawable.realm, "Realm", 2, "desc desc desc desc desc desc"));
        a.add(new App(R.drawable.andr, "Android Studio", 3, "desc desc desc desc desc desc"));
        a.add(new App(R.drawable.idea, "IDEA", 4, "desc desc desc desc desc desc"));
        a.add(new App(R.drawable.chrome, "Chrome", 5, "desc desc desc desc desc desc"));
        a.add(new App(R.drawable.realm, "Realm", 6, "desc desc desc desc desc desc"));
        a.add(new App(R.drawable.andr, "Android Studio", 7, "desc desc desc desc desc desc"));
        a.add(new App(R.drawable.idea, "IDEA", 8, "desc desc desc desc desc desc"));
        a.add(new App(R.drawable.chrome, "Chrome", 9, "desc desc desc desc desc desc"));
        a.add(new App(R.drawable.realm, "Realm", 11, "desc desc desc desc desc desc"));
        a.add(new App(R.drawable.andr, "Android Studio", 12, "desc desc desc desc desc desc"));
        a.add(new App(R.drawable.idea, "IDEA", 13, "desc desc desc desc desc desc"));
        return a;
    }
}
