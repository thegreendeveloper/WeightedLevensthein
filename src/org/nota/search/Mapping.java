package org.nota.search;

public class Mapping {

	char a;
	char b;

	public Mapping(char a, char b, Float weight) {
		this.a = a;
		this.b = b;
		this.weight = weight;
		
	}
	
	public char getA() {
		return a;
	}

	public void setA(char a) {
		this.a = a;
	}

	public char getB() {
		return b;
	}

	public void setB(char b) {
		this.b = b;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	Float weight;
}
