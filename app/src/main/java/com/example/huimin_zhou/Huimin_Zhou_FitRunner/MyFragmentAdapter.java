package com.example.huimin_zhou.Huimin_Zhou_FitRunner;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Lucidity on 17/1/17.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragments = null;

    public MyFragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int pos) {
        return mFragments.get(pos);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int pos) {
        if (pos == 0) {
            return "Welcome";
        } else if (pos == 1) {
            return "History";
        } else if (pos == 2) {
            return "Setting";
        } else {
            return null;
        }
    }
}