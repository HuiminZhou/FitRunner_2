package com.example.huimin_zhou.Huimin_Zhou_FitRunner;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

/**
 * Created by Lucidity on 17/1/17.
 */

public class SettingFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        addPreferencesFromResource(R.xml.settingpreference);

        ListPreference list = (ListPreference) findPreference("unit_preference");
        list.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if ((newValue).equals("Imperi")) {
                    MainActivity.pref = false;
                } else {
                    MainActivity.pref = true;
                }
                MainActivity.history.updateAdapter();
                return true;
            }
        });
    }
}