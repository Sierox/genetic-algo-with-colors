package com.sierox.genetic.model;

import java.awt.Color;

import com.sierox.genetic.Main;

public class Individual implements Comparable<Individual>{

	private Dna dna;
	private double fitness;

	public Individual(Dna dna) {
		this.dna = dna;
	}

	public Gene[][] getDnaGenotypeParts(int partLength) {
		Gene[][] gparts = new Gene[Main.TARGET_DNA.getGenes().length/partLength][partLength];
		for (int i = 0; i < gparts.length; i++) {
			for (int j = 0; j < gparts[i].length; j++) {
				gparts[i][j] = dna.getGenes()[i*partLength + j];	
			}
		}
		return gparts;
	}
	
	public Color getDnaPhenotype(int partBitLength) {
		int[] pparts = new int[3];
		for (int i = 0; i < getDnaGenotypeParts(partBitLength).length; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < getDnaGenotypeParts(partBitLength)[i].length; j++) {
				sb.append((getDnaGenotypeParts(partBitLength)[i][j].getGene()));
			}
			pparts[i] = Integer.parseInt(sb.toString(), 2);
		}
		Color phenotype = new Color(pparts[0], pparts[1], pparts[2]);
		return phenotype;
	}
	
	public String getStringDna(){
		return dna.getGenes().toString();
	}

	public Dna getDna() {
		return dna;
	}

	public void setFitness(double d) {
		this.fitness = d;
	}

	public double getFitness() {
		return fitness;
	}

	@Override
	public int compareTo(Individual i) {
		if(this.fitness > i.fitness)
			return 1;
		if(this.fitness < i.fitness)
			return -1;
		return 0;
	}
}