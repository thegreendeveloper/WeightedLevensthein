package org.nota.lucene.search;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Weight {

	private HashMap<Character, Integer> charIndex;
	private float[][] matrix;
	private float DEFAULT = 1.0f;
	
	public void setWeights(ArrayList<String> mappings) {
		if(mappings == null || (mappings.size() <= 1 || mappings.get(0).equals("")))
			return;
		int counter = 0;
		charIndex = new HashMap<Character, Integer>();
		/*Create char mapping*/
		for(String line : mappings) {
			String[] temp = line.toLowerCase().split(";");
			
			/*if the file is correctly setup, the line will contain a char c and a floating point value*/
			if((temp.length == 3) && (temp[0].length() == 1) && (temp[1].length() == 1)) {
				char a = temp[0].charAt(0);
				char b = temp[1].charAt(0);
				
				/*inserting the characters*/
				if(!charIndex.containsKey(a)) {
					charIndex.put(a, counter);
					counter++;
				}
				if(!charIndex.containsKey(b)) {
					charIndex.put(b, counter);
					counter++;
				}
				
			}
		}
		
		/*write default values*/
		matrix = new float[charIndex.size()][charIndex.size()];
		for(int i = 0; i < charIndex.size(); i++) 
			for(int j = 0; j < charIndex.size();j++)
				matrix[i][j] = DEFAULT;
		
		/*write matrix infro*/
		for(String line : mappings) {
			String[] temp = line.toLowerCase().split(";");
			char a = temp[0].charAt(0);
			char b = temp[1].charAt(0);
			float weight = Float.parseFloat(temp[2]);
			
			matrix[charIndex.get(a)][charIndex.get(b)] = weight;
			matrix[charIndex.get(b)][charIndex.get(a)] = weight;
		}
	}
	
	public float getWeight(char a, char b) {
		if(charIndex != null && charIndex.containsKey(a) && charIndex.containsKey(b))
			return matrix[charIndex.get(a)][charIndex.get(b)];
		return DEFAULT;
	}
	
	@Override
	public String toString() {
		return "Weight2Dim [charIndex=" + charIndex.keySet() + ", matrix=\n" + matrixToString() + ", DEFAULT=" + DEFAULT
				+ "]";
	}
	
	private String matrixToString() {
		if(charIndex == null)
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
