package com.example.Transformer;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import com.example.Transformer.R;

public class SelectionView extends EditText {
	
	private final WeakReference<MainActivity> activityRef;
	private List<Style> suggestionStyles;
	
	private class Style {
		public BackgroundColorSpan background;
		public final StyleSpan textStyle;
		
		public Style(BackgroundColorSpan bg, StyleSpan ts) {
			background = bg;
			textStyle = ts;
		}
	}
	
	public SelectionView(Context context) {
		super(context);
		activityRef = new WeakReference<MainActivity>((MainActivity) context);
		suggestionStyles = new ArrayList<Style>();
	}

	public SelectionView(Context context, AttributeSet attrs) {
		super(context, attrs);
		activityRef = new WeakReference<MainActivity>((MainActivity) context);
		suggestionStyles = new ArrayList<Style>();
	}

	public SelectionView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		activityRef = new WeakReference<MainActivity>((MainActivity) context);
		suggestionStyles = new ArrayList<Style>();
	}
	
	public void highlightMatches(String pattern) {
		String str = getText().toString();
		Spannable span = getText();
		for (Style style : suggestionStyles) {
			span.removeSpan(style.background);
			span.removeSpan(style.textStyle);
		}
		Matcher m = Pattern.compile(pattern).matcher(str);
		while (m.find()) {
			try {
				int begin = m.start();
				int end = begin+pattern.length();
				StyleSpan textStyle = new StyleSpan(Typeface.BOLD);
				BackgroundColorSpan background = new BackgroundColorSpan(0x770033FF);
				span.setSpan(textStyle, begin, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				span.setSpan(background, begin, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				suggestionStyles.add(new Style(background, textStyle));
			} catch (Exception e) {
				break;
			}
		}
	}
	
	
	/*
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (activityRef != null) {
			if (event.getAction() == MotionEvent.ACTION_UP) {
				if (selection != null) {
					MainActivity activity = activityRef.get();
					LinearLayout list = (LinearLayout) activity.findViewById(R.id.suggestions_list);
					LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					EditText et = (EditText) activity.findViewById(R.id.text_area);
					new SuggestionBuilderTask(list, inflater).execute(selection, et.getText().toString());
					return false;
				}
			}
		}
		return false;
	}
	*/
	
	@Override
	protected void onSelectionChanged(int start, int end) {
		if (activityRef != null) {
			MainActivity activity = activityRef.get();
			EditText et = (EditText) activity.findViewById(R.id.text_area);
			String selection;
			if (Math.abs(start - end) > 2) {
				if (start - end > 0) {
					selection = et.getText().subSequence(end, start).toString();
				} else {
					selection = et.getText().subSequence(start, end).toString();
				}
				highlightMatches(selection);
				new SuggestionBuilderTask(activity).execute(selection, et.getText().toString());
			}
		}
	}
}
