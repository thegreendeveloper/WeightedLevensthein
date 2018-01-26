package org.nota.search;

import org.apache.lucene.search.spell.*;
/*
 * Implementation of the Levensthein distance using 
 * the dynamic programming apporach (O(nm) time)
 * */
public final class WeightedLevenshteinDistance implements StringDistance {

	private Weights weights;

	public WeightedLevenshteinDistance() {
		weights = new Weights();
	}

	@Override
	public float getDistance(String target, String other) {
		char[] sa;
		int n;
		float p[]; // 'previous' cost array, horizontally
		float d[]; // cost array, horizontally
		float _d[]; // placeholder to assist in swapping p and d

		/* test */
		String placeholder;
		if (other.length() < target.length()) {
			placeholder = other;
			other = target;
			target = placeholder;
		}

		sa = target.toCharArray();
		n = sa.length;
		p = new float[n + 1];
		d = new float[n + 1];

		final int m = other.length();
		if (n == 0 || m == 0) {
			if (n == m) {
				return 1;
			} else {
				return 0;
			}
		}

		// indexes into strings s and t
		int i; // iterates through s
		int j; // iterates through t

		char t_j; // jth character of t

		float cost, runningCost = 0; // cost

		for (i = 0; i <= n; i++) {
			p[i] = i == 0 ? 0 : p[i - 1] + weights.getInsertionWeight(target.charAt(i - 1));// i;i;
		}

		for (j = 1; j <= m; j++) {
			t_j = other.charAt(j - 1);
			d[0] = runningCost + weights.getInsertionWeight(other.charAt(j - 1));// j;j;
			runningCost = d[0];
			for (i = 1; i <= n; i++) {
				cost = sa[i - 1] == t_j ? 0 : weights.getSubstitution().getWeight(sa[i - 1], t_j);
				// minimum of cell to the left+1, to the top+1, diagonally left and up +cost
				d[i] = Math.min(Math.min(d[i - 1] + weights.getDeletionWeight(sa[i - 1]), // deletion
						p[i] + weights.getInsertionWeight(t_j)), // insertion
						p[i - 1] + cost); // substitution

			}
			// copy current distance counts to 'previous row' distance counts
			_d = p;
			p = d;
			d = _d;
		}

		// our last action in the above loop was to switch d and p, so p now
		// actually has the most recent cost counts
		return 1.0f - ((float) p[n] / Math.max(other.length(), sa.length));
	}

	@Override
	public String toString() {
		return "levenshtein distance";
	}

}