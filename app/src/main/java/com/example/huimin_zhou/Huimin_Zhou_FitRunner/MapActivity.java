package com.example.huimin_zhou.Huimin_Zhou_FitRunner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Lucidity on 17/1/29.
 */

public class MapActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_map);
    }

    public void onSaveClick(View view) {
        saveToDB(view);
        finish();
    }

    public void onCancelClick(View view) {
        finish();
    }

    private void saveToDB(View view) {
        
    }
}
