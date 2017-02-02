package com.example.huimin_zhou.Huimin_Zhou_FitRunner;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static HistoryFragment history;
    public static boolean pref = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        WelcomeFragment welcome = new WelcomeFragment();
        history = new HistoryFragment();
        SettingFragment setting = new SettingFragment();
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(welcome);
        fragments.add(history);
        fragments.add(setting);

        MyFragmentAdapter myFragmentPagerAdapter = new MyFragmentAdapter(getFragmentManager(), fragments);
        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
        viewPager.setAdapter(myFragmentPagerAdapter);

        TabLayout tableLayout = (TabLayout)findViewById(R.id.tab_layout);
        tableLayout.setupWithViewPager(viewPager);

        loadPref();
    }

    private void loadPref() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPref.getString("unit_preference", "Metric (Kilometers)").equals("Imperi")) {
            pref = false;
        }
    }
}