package com.example.huimin_zhou.Huimin_Zhou_FitRunner;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.huimin_zhou.Huimin_Zhou_FitRunner.Database.DatabaseSource;
import com.example.huimin_zhou.Huimin_Zhou_FitRunner.Database.ExerciseEntry;
import com.example.huimin_zhou.Huimin_Zhou_FitRunner.Database.MySQLOpenHelper;

import java.util.ArrayList;

/**
 * Created by Lucidity on 17/1/17.
 */

public class HistoryFragment extends ListFragment {
    DatabaseSource mDBSource = null;
    HistoryListAdapter mAdapter;
    ArrayList<ExerciseEntry> mEntries = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initial
        mDBSource = new DatabaseSource(this.getActivity());
        mDBSource.open();
        mEntries = mDBSource.queryAll();

        if (MainActivity.pref == false) {
            for (ExerciseEntry entry : mEntries) {
                entry.setDistance((float) (entry.getDistance() * 0.62137));
            }
        }

        // adapter
        mAdapter = new HistoryListAdapter(getActivity(), mEntries);
        setListAdapter(mAdapter);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);
        // set click listener to involve activity with more details
        ListView mListView = getListView();
        AdapterView.OnItemClickListener mListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), HistoryEntryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong(MySQLOpenHelper.ID, mEntries.get(position).getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        };
        mListView.setOnItemClickListener(mListener);
    }


    // update adapter
    public void updateAdapter() {
        if (mDBSource == null) {
            mDBSource = new DatabaseSource(this.getActivity());
            mDBSource.open();
        }

        if (mEntries == null) {
            mEntries = mDBSource.queryAll();
        } else {
            mEntries.clear();
            mEntries.addAll(mDBSource.queryAll());
        }
        if (MainActivity.pref == true) {
            for (ExerciseEntry entry : mEntries) {
                entry.setDistance((float) (entry.getDistance() / 0.62137));
            }
        }

        // adapter
        mAdapter.notifyDataSetChanged();
    }
}
