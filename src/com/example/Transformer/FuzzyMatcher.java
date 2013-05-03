package com.example.Transformer;

import com.example.Transformer.R;
import java.util.ArrayList;
import java.util.List;

public class FuzzyMatcher {

	public static List<Integer> getMatches(String text, String pattern, int k) {
		
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		int range = 128;
		int firstMatch = -1;
		long[] r = new long[k + 1];
		long[] mask = new long[range];
		for (int i = 0; i <= k; i++) { r[i] = 1; }
		for (int i = 0; i < pattern.length(); ++i) {
			mask[(int) pattern.charAt(i)] |= 1 << i;
		}
		for (int i = 0; i < text.length(); i++) {
			long current = 0;
			long next = 0;
			for (int d = 0; d <= k; ++d) {
				long sub = (current | (r[d] & mask[text.charAt(i)])) << 1;
				long ins = current | ((r[d] & mask[text.charAt(i)]) << 1);
				long del = (next | (r[d] & mask[text.charAt(i)])) << 1;
				current = r[d];
				r[d] = sub | ins | del | 1;
				next = r[d];
			}
			if (0 < (r[k] & (1 << pattern.length()))) {
				if ((firstMatch == -1) || (i - firstMatch > pattern.length())) {
					firstMatch = i;
					indexes.add(firstMatch - pattern.length() + 1);
				}
			}
		}
		return indexes;
	}
	
	public static List<String> getMatchingStrings(String text, String pattern, int k) {
		List<Integer> results = getMatches(text, pattern, k);
		List<String> strings = new ArrayList<String>();
		for (Integer index : results) {
			try {
				strings.add(text.substring(index, index + pattern.length()));
			} catch (Exception e) {}
		}
		return strings;
		
	}
}