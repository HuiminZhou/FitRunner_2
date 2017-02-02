package com.example.huimin_zhou.Huimin_Zhou_FitRunner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;

import com.example.huimin_zhou.Huimin_Zhou_FitRunner.Database.DatabaseSource;
import com.example.huimin_zhou.Huimin_Zhou_FitRunner.Database.ExerciseEntry;
import com.example.huimin_zhou.Huimin_Zhou_FitRunner.Database.MySQLOpenHelper;

/**
 * Created by Lucidity on 17/2/1.
 */

public class HistoryEntryActivity extends AppCompatActivity {
    private long mID;
    private DatabaseSource mDBSource;
    private ExerciseEntry mEntry;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_history);

        // add toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_history);
        setSupportActionBar(toolbar);

        // get intent extras
        Intent intent = this.getIntent();
        mID = intent.getExtras().getLong(MySQLOpenHelper.ID);
        mDBSource = new DatabaseSource(this);
        mDBSource.open();
        mEntry =  mDBSource.queryOne(mID);

        setView();
    }

    // push the entry values to view
    private void setView() {
        EditText editText1 =  (EditText)findViewById(R.id.edit_input);
        EditText editText2 =  (EditText)findViewById(R.id.edit_activity);
        EditText editText3 =  (EditText)findViewById(R.id.edit_date);
        EditText editText4 =  (EditText)findViewById(R.id.edit_duration);
        EditText editText5 =  (EditText)findViewById(R.id.edit_distance);
        EditText editText6 =  (EditText)findViewById(R.id.edit_calories);
        EditText editText7 =  (EditText)findViewById(R.id.edit_heart);

        editText1.setText(mEntry.getInputType());
        editText2.setText(mEntry.getActivityType());
        editText3.setText("" + mEntry.getTime() + " " + mEntry.getDate());
        editText4.setText("" + mEntry.getDuration() + " secs");
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPref.getString("unit_preference", "Metric (Kilometers)").equals("Imperi")) {
            editText5.setText(String.format("%.2f", mEntry.getDistance()) + " Miles");
        } else {
            editText5.setText(String.format("%.2f", mEntry.getDistance() / 0.62137) + " Kilometers");
        }
        editText6.setText("" + mEntry.getCalories() + " cals");
        editText7.setText("" + mEntry.getHeart() + " bpm");
    }

    // add delete button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                mDBSource.delete(mEntry);
                MainActivity.history.updateAdapter();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
