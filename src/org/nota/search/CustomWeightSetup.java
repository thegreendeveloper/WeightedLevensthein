package org.nota.search;

import java.util.HashMap;
import java.util.HashSet;

/*
 * This is the custom weights setup. This has been setup specifically based on
 * common Danish misspelling tendencies. 
 * */
public class CustomWeightSetup {

	public static HashMap<Character, Float> getDeletionsSetup() {
		HashMap<Character, Float> map = new HashMap<Character, Float>();
		map.put('n', 0.95f);
		map.put('t', 0.95f);
		map.put('d', 0.95f);
		map.put('l', 0.95f);
		map.put('m', 0.95f);
		map.put('f', 0.95f);
		map.put('r', 0.95f);
		map.put('k', 0.95f);
		map.put('s', 0.95f);
		return map;
	}

	public static HashMap<Character, Float> getInsertionWeights() {
		HashMap<Character, Float> map = new HashMap<Character, Float>();

		map.put('r', 0.9f);
		map.put('h', 0.95f);
		map.put('l', 0.95f);
		map.put('t', 0.9f);
		map.put('k', 0.95f);
		map.put('m', 0.95f);
		map.put('d', 0.9f);
		map.put('g', 0.95f);
		map.put('p', 0.95f);
		map.put('f', 0.95f);
		map.put('s', 0.95f);
		map.put('e', 0.95f);
		map.put('v', 0.95f);
		map.put('n', 0.95f);

		return map;

	}

	public static Weight getSubstitutionSetup() {
		HashSet<Mapping> map = new HashSet<Mapping>();

		Mapping m = new Mapping('a', 'e', 0.75f);
		map.add(m);
		m = new Mapping('b', 'p', 0.8f);
		map.add(m);
		m = new Mapping('c', 'k', 0.8f);
		map.add(m);
		m = new Mapping('c', 's', 0.75f);
		map.add(m);
		m = new Mapping('d', 't', 0.75f);
		map.add(m);
		m = new Mapping('d', 'l', 0.8f);
		map.add(m);
		m = new Mapping('d', 'n', 0.8f);
		map.add(m);
		m = new Mapping('e', 'æ', 0.75f);
		map.add(m);
		m = new Mapping('e', 'i', 0.9f);
		map.add(m);
		m = new Mapping('g', 'v', 0.8f);
		map.add(m);
		m = new Mapping('g', 'j', 0.8f);
		map.add(m);
		m = new Mapping('g', 'k', 0.75f);
		map.add(m);
		m = new Mapping('j', 'i', 0.75f);
		map.add(m);
		m = new Mapping('o', 'å', 0.8f);
		map.add(m);
		m = new Mapping('s', 't', 0.8f);
		map.add(m);
		m = new Mapping('x', 'z', 0.8f);
		map.add(m);
		m = new Mapping('ø', 'y', 0.8f);
		map.add(m);
		m = new Mapping('å', 'u', 0.8f);

		Weight weight = new Weight(map);
		return weight;
	}

	public static Weight getTranspositionSetup() {
		HashSet<Mapping> map = new HashSet<Mapping>();

		Mapping m = new Mapping('e', 'r', 0.8f);
		map.add(m);
		m = new Mapping('n', 'g', 0.8f);
		map.add(m);
		m = new Mapping('r', 't', 0.8f);
		map.add(m);
		m = new Mapping('c', 'h', 0.8f);
		map.add(m);

		Weight weight = new Weight(map);
		return weight;
	}

}
