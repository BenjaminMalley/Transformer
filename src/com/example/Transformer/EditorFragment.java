package com.example.Transformer;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.example.Transformer.R;

public class EditorFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
		LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.editor_fragment, container, false);
		/*MainActivity activity = (MainActivity) getActivity();
		EditText et = (EditText) layout.findViewById(R.id.text_area);
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences((Context) activity);
		CharSequence text = (CharSequence) prefs.getString(SettingsFragment.PREF_TAB_STOP, "Hello");
		et.setText(text);*/
		return layout;
	}
}
