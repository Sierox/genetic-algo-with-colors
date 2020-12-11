package com.sierox.genetic;

import com.sierox.genetic.algorithm.DnaCreator;
import com.sierox.genetic.algorithm.PopulationCreator;
import com.sierox.genetic.gui.ViewFrame;
import com.sierox.genetic.model.Dna;
import com.sierox.genetic.model.Population;

public class Main {
	/* Default black */
	public static Dna TARGET_DNA = DnaCreator.getDefaultDna();
	
	public static int POPULATION_SIZE = 100;
	public static int GENE_FACTOR = 3;
	
	public static ViewFrame frame;
	public static Population population;
	
	public static void main(String[] args) {
		init();
	}
	
	public static void reset() {
		frame.dispose();
		init();
	}
	
	public static void init() {
		population = PopulationCreator.getRandomPopulation(POPULATION_SIZE);
		frame = new ViewFrame();
		frame.fill(population.getIndividualPhenotypes());
	}
}