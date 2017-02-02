package com.example.huimin_zhou.Huimin_Zhou_FitRunner;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.huimin_zhou.Huimin_Zhou_FitRunner.Database.DatabaseSource;
import com.example.huimin_zhou.Huimin_Zhou_FitRunner.Database.ExerciseEntry;
import com.example.huimin_zhou.Huimin_Zhou_FitRunner.Database.MySQLOpenHelper;


/**
 * Created by Lucidity on 17/1/17.
 */

public class ManualActivity extends ListActivity {
    static final String[] LIST_ITEM = new String[] {"Date", "Time", "Duration", "Distance",
            "Calories", "Heart Rate", "Comment"};
    DatabaseSource mDBSource = null;
    public static ExerciseEntry mEntry = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        // get input type and activity type
        Intent intent = this.getIntent();
        String inputType = intent.getExtras().getString(MySQLOpenHelper.INPUT_TYPE);
        String activType = intent.getExtras().getString(MySQLOpenHelper.ACTIVITY_TYPE);
        mEntry = new ExerciseEntry();
        mEntry.setInputType(inputType);
        mEntry.setActivityType(activType);

        // list view
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String> (
                this,
                android.R.layout.simple_list_item_1,
                LIST_ITEM);
        setListAdapter(mAdapter);
        ListView mListView = getListView();
        AdapterView.OnItemClickListener mListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onDialog(position);
            }
        };
        mListView.setOnItemClickListener(mListener);

        mDBSource = new DatabaseSource(this);
        mDBSource.open();
    }

    // trigger dialog
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
        saveToDB();
        finish();
    }
    public void onCancelClick(View view) {
        Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent();
        setResult(1, intent);
        finish();
    }

    // save the data to database
    private void saveToDB() {
        mDBSource.insertEntry(mEntry);
        Intent intent = new Intent();
        setResult(0, intent);
    }


}
