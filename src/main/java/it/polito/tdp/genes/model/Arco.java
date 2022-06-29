package it.polito.tdp.genes.model;

public class Arco {

	Classification c1; 
	Classification c2; 
	String type;
	
	public Arco(Classification c1, Classification c2, String type) {
		super();
		this.c1 = c1;
		this.c2 = c2;
		this.type = type;
	}

	public Classification getC1() {
		return c1;
	}

	public void setC1(Classification c1) {
		this.c1 = c1;
	}

	public Classification getC2() {
		return c2;
	}

	public void setC2(Classification c2) {
		this.c2 = c2;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	} 
	
	
	
	
}
