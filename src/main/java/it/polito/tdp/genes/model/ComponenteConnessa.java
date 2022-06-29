package it.polito.tdp.genes.model;

public class ComponenteConnessa {

	private Classification c; 
	private double peso;
	
	public ComponenteConnessa(Classification c, double peso) {
		super();
		this.c = c;
		this.peso = peso;
	}

	public Classification getC() {
		return c;
	}

	public void setC(Classification c) {
		this.c = c;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return c+" "+peso;
	} 
	
	
	
	
}
