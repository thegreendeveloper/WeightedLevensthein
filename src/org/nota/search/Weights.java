package org.nota.search;

import java.util.HashMap;

public class Weights {

	HashMap<Character, Float> insertion;
	HashMap<Character, Float> deletion;
	Weight substitution;
	Weight transposition;

	float DEFAULT = 1.0f;

	public Weights() {
		insertion = StaticWeightSetup.getInsertionWeights();
		deletion = StaticWeightSetup.getDeletionsSetup();
		substitution = StaticWeightSetup.getSubstitutionSetup();
		transposition = StaticWeightSetup.getTranspositionSetup();
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
