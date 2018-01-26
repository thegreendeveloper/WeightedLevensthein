package org.nota.search;
/*
 * This object contains mapping-info of two characters i.e.
 * It contains the characters and a weight. 
 * 
 * */
public class Mapping {

	private char a;
	private char b;
	private Float weight;

	public Mapping(char a, char b, Float weight) {
		this.a = a;
		this.b = b;
		this.weight = weight;

	}

	public char getA() {
		return a;
	}

	public char getB() {
		return b;
	}

	public Float getWeight() {
		return weight;
	}

}
