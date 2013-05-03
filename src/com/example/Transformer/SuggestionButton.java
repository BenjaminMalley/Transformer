package com.example.Transformer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.example.Transformer.R;

public class SuggestionButton extends Button implements OnClickListener {
	public SuggestionButton (Context context) {
	    super(context);
	    this.setOnClickListener(this);
	}

	public SuggestionButton (Context context, AttributeSet attrs) {
	    super(context, attrs);
	    this.setOnClickListener(this);
	}

	public SuggestionButton (Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
	    this.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		MainActivity activity = (MainActivity) view.getContext();
		if (activity != null) {
			SelectionView sv = (SelectionView) activity.findViewById(R.id.text_area);
			sv.highlightMatches((String) getText());
		}
	}
}
