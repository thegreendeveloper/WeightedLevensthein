package org.nota.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Weight {

	private HashMap<Character, Integer> charIndex;
	private float[][] matrix;
	private float DEFAULT = 1.0f;

	public void setWeights(HashSet<Mapping> map) {
		charIndex = new HashMap<Character, Integer>();

		int index = 0;
		for (Mapping m : map) {
			if (!charIndex.containsKey(m.getA())) {
				charIndex.put(m.getA(), index);
				index++;
			}

			if (!charIndex.containsKey(m.getB())) {
				charIndex.put(m.getB(), index);
				index++;
			}

		}

		/* write default values */
		matrix = new float[charIndex.size()][charIndex.size()];
		for (int i = 0; i < charIndex.size(); i++)
			for (int j = 0; j < charIndex.size(); j++)
				matrix[i][j] = DEFAULT;

		/* write matrix infro */
		for (Mapping m : map) {
			matrix[charIndex.get(m.getA())][charIndex.get(m.getB())] = m.getWeight();
			matrix[charIndex.get(m.getB())][charIndex.get(m.getA())] = m.getWeight();
		}
	}

	public float getWeight(char a, char b) {
		if (charIndex != null && charIndex.containsKey(a) && charIndex.containsKey(b))
			return matrix[charIndex.get(a)][charIndex.get(b)];
		return DEFAULT;
	}

	@Override
	public String toString() {
		return "Weight2Dim [charIndex=" + charIndex.keySet() + ", matrix=\n" + matrixToString() + ", DEFAULT=" + DEFAULT
				+ "]";
	}

	private String matrixToString() {
		if (charIndex == null)
			return "not initialized";
		String temp = "";
		for (int i = 0; i < charIndex.size(); i++) {
			for (int j = 0; j < charIndex.size(); j++) {
				temp += matrix[i][j] + " ";
			}
			temp += "\n";
		}
		return temp;
	}

}
