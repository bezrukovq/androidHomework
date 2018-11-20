package com.example.vladimir.bottomnav;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

public class RecyclerFragment extends Fragment {

    Toolbar toolbar;
    AppAdapter appAdapter;
    ArrayList<App> a=getMyList();


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

    @SuppressLint("CheckResult")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setView(LayoutInflater.from(getContext()).inflate(R.layout.dialog_progress, null))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).create();
        dialog.show();
        ProgressBar pb = dialog.findViewById(R.id.pb_dialog);
        Observable<App> observable = Observable.fromIterable(getMyList());
        observable
                .take(12)
                .doOnNext(itm -> {
                    itm.setName(itm.getName() + itm.getName().length());
                    pb.setProgress(pb.getProgress() + 1); });
        switch (item.getItemId()) {
            case R.id.action_by_id:
                observable.toSortedList((p1,p2)-> p1.getId()-p2.getId())
                        .subscribe(list-> a= (ArrayList<App>) list);
                appAdapter.submitList(a);
                Toast.makeText(getContext(), "ID", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_by_name:
                observable.toSortedList((p1,p2)-> p1.getName().compareTo(p2.getName()))
                        .subscribe(list-> a= (ArrayList<App>) list);
                appAdapter.submitList(a);
                Toast.makeText(getContext(), "NAME", Toast.LENGTH_LONG).show();
                break;
        }
        dialog.dismiss();
        return true;
    }
    /*Collections.sort(a, new Comparator<App>() {
                       @Override
                       public int compare(App app, App t1) {
                           return app.getName().charAt(0) - t1.getName().charAt(0);
                       }
                   });*/ /*Collections.sort(a, new Comparator<App>() {
                    @Override
                    public int compare(App app, App app2) {
                        return app.getId() - app2.getId();
                    }
                });*/
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
        a.add(new App(R.drawable.realm, "Realm", 10, "desc desc desc desc desc desc"));
        a.add(new App(R.drawable.andr, "Android Studio", 11, "desc desc desc desc desc desc"));
        a.add(new App(R.drawable.idea, "IDEA", 12, "desc desc desc desc desc desc"));
        a.add(new App(R.drawable.chrome, "Chrome", 13, "desc desc desc desc desc desc"));
        a.add(new App(R.drawable.realm, "Realm", 14, "desc desc desc desc desc desc"));
        a.add(new App(R.drawable.andr, "Android Studio", 15, "desc desc desc desc desc desc"));
        a.add(new App(R.drawable.idea, "IDEA", 16, "desc desc desc desc desc desc"));
        return a;
    }
}
