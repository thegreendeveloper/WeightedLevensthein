package org.nota.search;

import java.util.HashMap;

/*
 * This class contains the weights mapping of the four types of operations the Damerau-Levensthein
 * distance implements. 
 * */
public class Weights {

	private HashMap<Character, Float> insertion;
	private HashMap<Character, Float> deletion;
	private Weight substitution;
	private Weight transposition;

	float DEFAULT = 1.0f;

	public Weights() {
		initialize();
	}

	private void initialize() {
		insertion = CustomWeightSetup.getInsertionWeights();
		deletion = CustomWeightSetup.getDeletionsSetup();
		substitution = CustomWeightSetup.getSubstitutionSetup();
		transposition = CustomWeightSetup.getTranspositionSetup();
	}

	public Weight getSubstitution() {
		return substitution;
	}

	public Weight getTransposition() {
		return transposition;
	}

	public float getInsertionWeight(char c) {
		if (insertion != null && insertion.containsKey(c))
			return insertion.get(c);
		return DEFAULT;
	}

	public float getDeletionWeight(char c) {
		if (deletion != null && deletion.containsKey(c))
			return deletion.get(c);
		return DEFAULT;
	}

}
