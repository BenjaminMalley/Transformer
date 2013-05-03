package com.example.Transformer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private final String TRANSFORM_TEXT = "transformText";

	@Override
	protected void onCreate(Bundle state) {
		super.onCreate(state);
		
		Pattern p = Pattern.compile("aaa|abb");
		Matcher m = p.matcher("aaa|abb");
		Log.v("Hello", m.pattern().pattern());
		
		Intent intent = getIntent();
		
		setContentView(R.layout.activity_main);
		if (findViewById(R.id.fragment_container) != null) {
			
			if (state != null) { return; }
			
			EditorFragment editor = new EditorFragment();
			editor.setArguments(intent.getExtras());
			
			getFragmentManager().beginTransaction()
				.add(R.id.fragment_container, editor).commit();
			
		}
		
		String action = intent.getAction();
		String type = intent.getType();
		EditText et = (EditText) findViewById(R.id.text_area);
		
		if(Intent.ACTION_SEND.equals(action) && type != null) {
		    String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
		    if (sharedText != null) {
				et.setText(sharedText);
		    }
		}
		
	}
	
	
	@Override
	public void onRestoreInstanceState(Bundle state) {
		super.onRestoreInstanceState(state);
		EditText et = (EditText) findViewById(R.id.text_area);
		et.setText(state.getCharSequence(TRANSFORM_TEXT));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_settings:
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.id.fragment_container, new SettingsFragment());
				transaction.addToBackStack(null);
				transaction.commit();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public void onSaveInstanceState(Bundle state) {
		EditText et = (EditText) findViewById(R.id.text_area);
		if (et != null) {
			CharSequence seq = et.getText();
			state.putCharSequence(TRANSFORM_TEXT, seq);
		}
		super.onSaveInstanceState(state);
	}
	

	
}
