package com.example.vladimir.bottomnav;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewPFragment extends Fragment {

    ViewPager pager;
    MyFragmentPagerAdapter pagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_viewp, container, false);
        pager = v.findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager());
        pagerAdapter.add(PageFragment.newInstance(0));
        pagerAdapter.add(PageFragment.newInstance(1));
        pagerAdapter.add(PageFragment.newInstance(2));
        pager.setAdapter(pagerAdapter);
        TabLayout tabLayout = v.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
        return v;
    }
}
