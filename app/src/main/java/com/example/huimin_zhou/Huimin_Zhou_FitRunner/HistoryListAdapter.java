package com.example.huimin_zhou.Huimin_Zhou_FitRunner;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.huimin_zhou.Huimin_Zhou_FitRunner.Database.ExerciseEntry;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Lucidity on 17/1/31.
 */

public class HistoryListAdapter extends BaseAdapter {
    private Activity mActivity;
    private ArrayList<ExerciseEntry> mEntries;

    public HistoryListAdapter(Activity activity, ArrayList<ExerciseEntry> arrayList) {
        this.mActivity = activity;
        this.mEntries = arrayList;
    }

    @Override
    public int getCount() {
        return mEntries.size();
    }

    @Override
    public Object getItem(int position) {
        return mEntries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // set two lines in a row
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ExerciseEntry entry = mEntries.get(position);

        LayoutInflater inflater = mActivity.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_historyentry, null, false);

        TextView textView1 = (TextView) view.findViewById(R.id.history_text1);
        TextView textView2 = (TextView) view.findViewById(R.id.history_text2);
        String line1 = entry.getInputType() + ": " + entry.getActivityType() + ", " + entry.getTime() + " " + entry.getDate();
        String line2 = String.format("%.2f", entry.getDistance());
        if (MainActivity.pref == false) {
            line2 += " Miles, " + entry.getDuration() + " secs";
        } else {
            line2 += " Kilometers, " + entry.getDuration() + " secs";
        }

        textView1.setText(line1);
        textView2.setText(line2);

        return view;
    }
}
