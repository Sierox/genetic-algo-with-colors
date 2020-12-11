package com.sierox.genetic.algorithm;
import java.util.ArrayList;

import com.sierox.genetic.model.Individual;
import com.sierox.genetic.model.Population;


public class PopulationCreator {

	public static Population getRandomPopulation(int numberOfIndividuals) {
		ArrayList<Individual> i = new ArrayList<>();
		
		for (int j = 0; j < numberOfIndividuals; j++) {
			i.add(IndividualCreator.getRandomIndividual());
		}
		Population p = new Population(i);
		return p;
	}
}
