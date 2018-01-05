package org.nota.search;
import org.apache.lucene.search.spell.StringDistance;

public final class WeightedDamerauLevenshteinDistanceR implements StringDistance {

	private Weights weights;

	public WeightedDamerauLevenshteinDistanceR() {
		weights = new Weights();
	}

	@Override
	public float getDistance(String target, String other) {
		char[] sa;
		int n;
		float p[], p2[]; // 'previous' cost array, horizontally
		float d[]; // cost array, horizontally
		float _d[]; // second 'previous' cost array, horizontally

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
		p2 = new float[n + 1];
		_d = new float[n + 1];

		final int m = other.length();
		if (n == 0 || m == 0) {
			if (n == m) {
				return 1;
			} else {
				return 0;
			}
		}

		// indexes into strings s and t
		int i, j;
		float cost, runningCost = 0;

		char t_j; // jth character of t

		for (i = 0; i <= n; i++) {
			p[i] = i == 0 ? 0 : p[i - 1] + weights.getInsertionWeight(target.charAt(i - 1));// i;
		}

		for (j = 1; j <= m; j++) {
			t_j = other.charAt(j - 1);
			d[0] = runningCost + weights.getInsertionWeight(other.charAt(j - 1));// j;
			runningCost = d[0];
			for (i = 1; i <= n; i++) {
				cost = sa[i - 1] == t_j ? 0 : weights.getSubstitution().getWeight(sa[i - 1], t_j);
				// minimum of cell to the left+1, to the top+1, diagonally left and up +cost
				d[i] = Math.min(Math.min(d[i - 1] + weights.getDeletionWeight(sa[i - 1]), // deletion
						p[i] + weights.getInsertionWeight(t_j)), // insertion
						p[i - 1] + cost); // substitution

				/* Damerau specific : transposition part */
				if ((i > 1 && j > 1) && (sa[i - 1] == other.charAt(j - 2)) && (sa[i - 2] == t_j)) {
					cost = sa[i - 1] == t_j ? 0 : weights.getTransposition().getWeight(sa[i - 1], t_j); // changed by TR
					d[i] = Math.min(d[i], p2[i - 2] + cost); // transposition same cost as subst.

				}
			}
			// copy current distance counts to 'previous row' distance counts. This goes
			// faster than iterating through loop
			_d = p2;
			p2 = p;
			p = d;
			d = _d;

		}

		// our last action in the above loop was to switch d and p, so p now
		// actually has the most recent cost counts
		return 1.0f - ((float) p[n] / Math.max(other.length(), sa.length));
	}

	@Override
	public String toString() {
		return "Damerau Levenshtein (restricted)";
	}

}