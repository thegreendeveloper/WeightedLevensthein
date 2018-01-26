package org.nota.search;
import org.apache.lucene.search.spell.StringDistance;
import java.util.HashMap;
import java.util.Map;

/*
 * Implementation of the true damerau-Levenshtein method.
 * 
 * */

public final class WeightedDamerauLevenshteinDistanceT implements StringDistance {

	private Weights weights;

	public WeightedDamerauLevenshteinDistanceT() {
		weights = new Weights();
	}

	@Override
	public float getDistance(String target, String other) {
		// Create and initialize the character array indices
		String placeholder;
		if(other.length() < target.length()) {
			placeholder = other;
			other = target;
			target = placeholder;
		}
		
		Map<Character, Integer> da = new HashMap<Character, Integer>();
		for (int i = 0; i < target.length(); i++)
			da.put(target.charAt(i), 0);

		for (int i = 0; i < other.length(); i++)
			da.put(other.charAt(i), 0);

		int maxDist = target.length() + other.length();

		float[][] d = new float[target.length() + 2][other.length() + 2];

		d[0][0] = maxDist;
		for (int i = 0; i <= target.length(); i++) {
			d[i + 1][0] = maxDist;
			d[i + 1][1] = i == 0 ? 0 : d[i][1] + weights.getInsertionWeight(target.charAt(i-1));//i;
		}
		for (int j = 0; j <= other.length(); j++) {
			d[0][j + 1] = maxDist;
			d[1][j + 1] = j == 0 ? 0 : d[1][j] + weights.getInsertionWeight(other.charAt(j-1));//j;
		}

		int db = 0, k = 0, l = 0;
	
		for (int i = 1; i <= target.length(); i++) {
			db = 0;
			for (int j = 1; j <= other.length(); j++) {
				k = da.get(other.charAt(j - 1));
				l = db;
				
				if (target.charAt(i - 1) == other.charAt(j - 1)) {
					d[i + 1][j + 1] = d[i][j];
					db = j;
				}
				else {
					d[i + 1][j + 1] = Math.min(d[i][j] + weights.getSubstitution().getWeight(target.charAt(i - 1), other.charAt(j - 1)), //substitution 
									Math.min(d[i + 1][j] + weights.getInsertionWeight(other.charAt(j - 1)),//Math.min(weights.getInsertionWeight(target.charAt(i - 1)),weights.getInsertionWeight(other.charAt(j - 1))), //insertion
											d[i][ j + 1] + weights.getDeletionWeight(target.charAt(i - 1))));//Math.min(weights.getDeletionWeight(target.charAt(i - 1)), weights.getDeletionWeight(other.charAt(j - 1)) )));//deletion
				}
				d[i + 1][j + 1] = Math.min(d[i + 1][j + 1], // current price 
											d[k][l] + (i - k - 1) +
												weights.getTransposition().getWeight(target.charAt(i - 1), other.charAt(j - 1)) + 
												(j - l - 1)); //transposition
//				System.out.printf("%s %s %.2f %.2f %.2f",target.charAt(i - 1),  other.charAt(j - 1),d[i + 1][j + 1],  
//						weights.getInsertionWeight(target.charAt(i - 1)),weights.getInsertionWeight(other.charAt(j - 1)));
			}
			da.put(target.charAt(i - 1), i);
//			System.out.println();
		}
		return 1.0f - ((float) d[target.length() + 1][other.length() + 1] / Math.max(other.length(), target.length()));
	}


}
