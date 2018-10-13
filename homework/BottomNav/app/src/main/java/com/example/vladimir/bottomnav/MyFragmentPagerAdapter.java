package com.example.vladimir.bottomnav;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    PageFragment f1;
    PageFragment f2;
    PageFragment f3;

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        f1 = PageFragment.newInstance(0);
        f2 = PageFragment.newInstance(1);
        f3 = PageFragment.newInstance(2);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return f1;
            case 1:
                return f2;
            case 2:
                return f3;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }
}