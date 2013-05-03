package com.example.Transformer;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.Transformer.R;

public class SuggestionBuilderTask extends AsyncTask<String, Void, Set<String>> {
	
	private final WeakReference<MainActivity> activityRef;
	
	public SuggestionBuilderTask(MainActivity activity) {
		activityRef = new WeakReference<MainActivity>(activity);
	}
	
	
	@Override
	protected Set<String> doInBackground(String... params) {
		Set<String> resultSet = new HashSet<String>(FuzzyMatcher.getMatchingStrings(params[1], params[0], 1));
		resultSet.remove(params[0]);
		if (resultSet.size() > 6) {
			resultSet = new HashSet<String>();
		}
		return resultSet;
	}

	@Override
	protected void onPostExecute(Set<String> result) {
		if (activityRef != null) {
			final MainActivity activity = activityRef.get();
			if (activity != null) {
				LinearLayout list = (LinearLayout) activity.findViewById(R.id.suggestions_list);
				LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				list.removeAllViews();
				Iterator<String> iter = result.iterator();
				while (iter.hasNext()) {
					String val = iter.next();
					SuggestionButton suggestion = (SuggestionButton) inflater.inflate(R.layout.suggestion, list, false);
					suggestion.setText(val);
					list.addView(suggestion);
				}
			}
		}
	}
}
