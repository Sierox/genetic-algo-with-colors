package com.sierox.genetic.model;

public class Pair {

	private Individual individual1;
	private Individual individual2;
	private Individual offspring1;
	private Individual offspring2;
	
	public Pair(Individual individual1, Individual individual2) {
		this.individual1 = individual1;
		this.individual2 = individual2;
	}
	
	public Individual getIndividual1() {
		return individual1;
	}
	
	public void setIndividual1(Individual individual1) {
		this.individual1 = individual1;
	}
	
	public Individual getIndividual2() {
		return individual2;
	}
	
	public void setIndividual2(Individual individual2) {
		this.individual2 = individual2;
	}

	public Individual getOffspring1() {
		return offspring1;
	}

	public void setOffspring1(Individual offspring1) {
		this.offspring1 = offspring1;
	}

	public Individual getOffspring2() {
		return offspring2;
	}

	public void setOffspring2(Individual offspring2) {
		this.offspring2 = offspring2;
	}
}
