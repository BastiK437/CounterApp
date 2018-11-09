package com.example.basti_pc.bouldern;

import android.os.Bundle;

public class SettingsActivity extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
