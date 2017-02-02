package com.example.huimin_zhou.Huimin_Zhou_FitRunner;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.huimin_zhou.Huimin_Zhou_FitRunner.Database.MySQLOpenHelper;

/**
 * Created by Lucidity on 17/1/17.
 */

public class WelcomeFragment extends Fragment implements Button.OnClickListener{
    private Spinner inputType = null;
    private Spinner activType = null;
    private String[] inputlist = {"Manual Entry", "GPS", "Automatic"};
    private String[] activlist = {"Running", "Walking", "Standing", "Cycling", "Hiking",
            "Downhill Skiing", "Cross-Country Skiing", "Snowboarding", "Skating",
            "Swimming", "Mountain Biking", "Wheelchair", "Elliptical", "Other"};
    private int REQUEST_CODE = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.fragment_welcome, container, false);
        Button btns = (Button) v.findViewById(R.id.btn_start);
        btns.setOnClickListener(this);
        inputType = ((Spinner) v.findViewById(R.id.spinner_input));
        activType = ((Spinner) v.findViewById(R.id.spinner_activity));
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                int inputIndex = inputType.getSelectedItemPosition();
                int activIndex = activType.getSelectedItemPosition();
                Intent intent = null;
                switch (inputIndex) {
                    case 0:
                        intent = new Intent(getActivity(), ManualActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(MySQLOpenHelper.INPUT_TYPE, inputlist[inputIndex]);
                        bundle.putString(MySQLOpenHelper.ACTIVITY_TYPE, activlist[activIndex]);
                        intent.putExtras(bundle);
                        break;
                    default:
                        intent = new Intent(getActivity(), MapActivity.class);
                        break;
                }
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.btn_syn:
                Toast.makeText(getActivity(), "Sync", Toast.LENGTH_LONG);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 0) {
            MainActivity.history.updateAdapter();
        }
    }
}
