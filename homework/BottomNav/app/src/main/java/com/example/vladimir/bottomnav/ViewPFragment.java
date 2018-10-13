package com.example.vladimir.bottomnav;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewPFragment extends Fragment {

    ViewPager pager;
    PagerAdapter pagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_viewp, container, false);
        pager = v.findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager());
        pager.setAdapter(pagerAdapter);
        return v;
    }
}
