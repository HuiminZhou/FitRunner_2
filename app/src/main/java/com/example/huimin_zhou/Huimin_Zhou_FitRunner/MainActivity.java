package com.example.huimin_zhou.Huimin_Zhou_FitRunner;

import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WelcomeFragment welcome = new WelcomeFragment();
        HistoryFragment history = new HistoryFragment();
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
    }
}