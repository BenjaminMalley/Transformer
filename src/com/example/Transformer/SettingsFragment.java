package com.example.Transformer;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;
import com.example.Transformer.R;

public class SettingsFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {

	public final static String PREF_TAB_STOP = "pref_key_tab_stop";
	public final static String PREF_SYNTAX_HIGHLIGHTING = "pref_key_syntax_highlighting";
	public final static String PREF_TAB_SPACE = "pref_key_tab_space";
	
	@Override
	public void onCreate(Bundle state) {
		super.onCreate(state);
		addPreferencesFromResource(R.xml.preferences);
	}
	
	
	public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        if(prefs.getBoolean(PREF_SYNTAX_HIGHLIGHTING, false)) {
        }
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

	}

	@Override
	public void onPause() {
	    getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	    super.onPause();
	}
}
