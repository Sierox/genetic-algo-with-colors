package com.sierox.genetic.model;

import com.sierox.genetic.Main;

public class Dna {

	private Gene[] genes = new Gene[24];
	
	public Dna() {
	}
	
	public Dna(Gene[] genes) {
		this.genes = genes;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < genes.length; i++) {
			sb.append(genes[i].toString());
		}
		return new String(sb);
	}
	
	public void setGenes(Gene[] genes) {
		this.genes = genes;
	}
	
	public Gene[] getGenes() {
		return genes;
	}
	
	public Dna clone(){
		Gene[] copyGenes = new Gene[Main.TARGET_DNA.genes.length];
		for (int i = 0; i < genes.length; i++) {
			copyGenes[i] = new Gene(genes[i].getGene());
		}
		return new Dna(copyGenes);
	}
	
	public boolean equals(Dna dna) {
		for (int i = 0; i < genes.length; i++) {
			if(dna.genes[i].getGene() != genes[i].getGene()){
				return false;
			}
		}
		return true;
	}
}
