package org.nota.lucene.search;


import java.util.ArrayList;
import java.util.HashMap;

public class Weights {

	HashMap<Character, Float> insertion;
	HashMap<Character, Float> deletion;
	Weight substitution;
	Weight transposition;

	float DEFAULT = 1.0f;

	public Weight getSubstitution() {
		return substitution;
	}

	public Weight getTransposition() {
		return transposition;
	}

	public void setInsertionWeights(ArrayList<String> mappings) {
		insertion = createHashMap(mappings);
	}

	public float getInsertionWeight(char c) {
		if (insertion != null && insertion.containsKey(c))
			return insertion.get(c);
		return DEFAULT;
	}

	public void setDeletionWeights(ArrayList<String> mappings) {
		deletion = createHashMap(mappings);
	}

	public float getDeletionWeight(char c) {
		if (deletion != null && deletion.containsKey(c))
			return deletion.get(c);
		return DEFAULT;
	}

	private HashMap<Character, Float> createHashMap(ArrayList<String> mappings) {
		if (mappings.size() == 0)
			return null;
		HashMap<Character, Float> mapping = new HashMap<Character, Float>();
		for (String line : mappings) {
			String[] temp = line.toLowerCase().split(";");

			/*
			 * if the file is correctly setup, the line will contain a char c and a floating
			 * point value
			 */
			if (temp.length == 2 && temp[0].length() == 1) {
				char c = temp[0].charAt(0);
				float weight = Float.parseFloat(temp[1]);

				mapping.put(c, weight);
			}
		}
		return mapping;
	}

	public void setSubstitution(Weight weight) {
		substitution = weight;
	}

	public void setTransposition(Weight weight) {
		transposition = weight;
	}
}
