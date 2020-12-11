package com.sierox.genetic.model;
import java.awt.Color;
import java.util.ArrayList;

import com.sierox.genetic.Main;
import com.sierox.genetic.algorithm.RandomSelector;

public class Population {
	
	private ArrayList<Individual> individuals;
	private Pair[] pairs;
	private int generationNumber;
	public RandomSelector selector;
	
	public Population(ArrayList<Individual> individuals) {
		this.individuals = individuals;
	}
	
	public ArrayList<Individual> getIndividuals() {
		return individuals;
	}
	
	public void setIndividuals(ArrayList<Individual> individuals) {
		this.individuals = individuals;
	}

	public Pair[] getPairs() {
		return pairs;
	}

	public void setPairs(Pair[] pairs) {
		this.pairs = pairs;
	}
	
	public void incrementGenerationNumber() {
		generationNumber++;
	}
	
	public int getGenerationNumber() {
		return generationNumber;
	}
	
	public Individual selectRandom() {
		return selector.getRandom();
	}
	
	public Color[] getIndividualPhenotypes(){
		Color[] phenotypes = new Color[Main.POPULATION_SIZE];
		for (int i = 0; i < phenotypes.length; i++) {
			phenotypes[i] = getIndividuals().get(i).getDnaPhenotype(8);
		}
		return phenotypes;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Generation #" + getGenerationNumber() + ":\n"
				+ "--------------- \n");
		for (int i = 0; i < getIndividuals().size(); i++) {
			sb.append("Individual #" + i + ": " + getIndividuals().get(i).getDna().toString() + "\n");
		}
		return new String(sb);
	}
}
