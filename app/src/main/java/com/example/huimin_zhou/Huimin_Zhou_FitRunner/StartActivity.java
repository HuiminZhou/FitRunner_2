package com.example.huimin_zhou.Huimin_Zhou_FitRunner;

import android.app.DialogFragment;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * Created by Lucidity on 17/1/17.
 */

public class StartActivity extends ListActivity {
    static final String[] LIST_ITEM = new String[] {"Date", "Time", "Duration", "Calories",
            "Distance", "Heart Rate", "Comment"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                LIST_ITEM);
        setListAdapter(mAdapter);

        AdapterView.OnItemClickListener mListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onDialog(position);
            }
        };

        ListView mListView = getListView();
        mListView.setOnItemClickListener(mListener);
    }

    public void onDialog(int position) {
        DialogFragment dialogFragment = DialogsFragment.newInstance(position);
        switch (position) {
            case 0:
                dialogFragment.show(getFragmentManager(), "DIALOG_DATE_PICKER");
                break;
            case 1:
                dialogFragment.show(getFragmentManager(), "DIALOG_TIME_PICKER");
                break;
            case 2:
                dialogFragment.show(getFragmentManager(), "DIALOG_DURATION");
                break;
            case 3:
                dialogFragment.show(getFragmentManager(), "DIALOG_DISTANCE");
                break;
            case 4:
                dialogFragment.show(getFragmentManager(), "DIALOG_CALORIES");
                break;
            case 5:
                dialogFragment.show(getFragmentManager(), "DIALOG_HEART_RATE");
                break;
            case 6:
                dialogFragment.show(getFragmentManager(), "DIALOG_COMMENT");
                break;
        }
    }

    public void onSaveClick(View view) {
        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void onCancelClick(View view) {
        Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
        finish();
    }
}
