package com.sierox.genetic.algorithm;

import com.sierox.genetic.model.Individual;

public class IndividualCreator {
	
	public static Individual getRandomIndividual() {
		Individual i = new Individual(DnaCreator.getRandomDna());
		return i;
	}
}
