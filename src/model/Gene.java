package com.sierox.genetic.model;

public class Gene {

	private boolean gene;
	
	public Gene(int gene) {
		setGene(gene);
	}
	
	@Override
	public String toString() {
		if(gene)
			return "1";
		return "0";
	}
	
	public void mutate() {
		gene = !gene;
	}
	
	public int getGene() {
		if(gene)
			return 1;
		else
			return 0;
	}
	
	public void setGene(int gene){
		if(gene == 1)
			this.gene = true;
		if(gene == 0)
			this.gene = false;
	}
}
