package com.sierox.genetic.algorithm;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

import com.sierox.genetic.Main;
import com.sierox.genetic.model.Dna;
import com.sierox.genetic.model.Gene;
import com.sierox.genetic.model.Individual;
import com.sierox.genetic.model.Pair;
import com.sierox.genetic.model.Population;

public class Algorithm {

	public static boolean found = false;
	public static int foundNumber;
	private static Random r = new Random();

	public static void simulateStep(Population population) {
		checkForTargetDna(population);
		if (!found) {
			calculatePopulationFitness(population);
			createMatingPool(population);
			simulateMating(population);
			crossOverPopulationDna(population);
			mutatePopulationDna(population);
			createNextGeneration(population);
			
			population.incrementGenerationNumber();
			System.out.println(population);
			checkForTargetDna(population);
			Main.frame.fill(population.getIndividualPhenotypes());
		}
	}

	public static void simulateSteps(Population population, int n) {
		for (int i = 0; i < n; i++) {
			simulateStep(population);
		}
	}

	public static double calculateFitness(Individual i) {
		int fitGenes = 0;
		for (int j = 0; j < i.getDna().getGenes().length; j++) {
			if (i.getDna().getGenes()[j].toString().equals(Main.TARGET_DNA.getGenes()[j].toString()))
				fitGenes++;
		}
		NumberFormat formatter = new DecimalFormat("#0.000");
		i.setFitness(1000 * (Double.parseDouble(
				formatter.format((double) Math.pow(fitGenes, Main.GENE_FACTOR) / Main.TARGET_DNA.getGenes().length)
						.replace(",", "."))));
		return i.getFitness();
	}

	public static void calculatePopulationFitness(Population p) {
		double fitnesses[] = new double[p.getIndividuals().size()];
		for (int i = 0; i < p.getIndividuals().size(); i++) {
			fitnesses[i] = Algorithm.calculateFitness(p.getIndividuals().get(i));
		}
	}

	public static void createMatingPool(Population population) {
		population.selector = new RandomSelector(population.getIndividuals());
	}

	public static void simulateMating(Population population) {

		Pair[] pairs = new Pair[Main.POPULATION_SIZE];
		for (int i = 0; i < Main.POPULATION_SIZE; i++) {
			pairs[i] = new Pair(population.selectRandom(), population.selectRandom());

			while (pairs[i].getIndividual1() == pairs[i].getIndividual2()) {
				pairs[i].setIndividual2(population.selectRandom());
			}
		}
		population.setPairs(pairs);
	}

	public static void crossOverDna(Dna dna1, Dna dna2) {
		int cut = r.nextInt(Main.TARGET_DNA.getGenes().length);
		for (int i = 0; i < cut; i++) {
			if (dna1.getGenes()[i] != dna2.getGenes()[i]) {
				Gene replaced = dna1.getGenes()[i];
				dna1.getGenes()[i] = dna2.getGenes()[i];
				dna2.getGenes()[i] = replaced;
			}
		}
	}

	public static void crossOverPair(Pair p) {
		p.setOffspring1(new Individual((p.getIndividual1().getDna().clone())));
		p.setOffspring2(new Individual((p.getIndividual2().getDna().clone())));
		crossOverDna(p.getOffspring1().getDna(), p.getOffspring2().getDna());
	}

	public static void crossOverPopulationDna(Population p) {
		for (int i = 0; i < p.getPairs().length; i++) {
			crossOverPair(p.getPairs()[i]);
		}
	}

	public static void mutateDna(Dna dna) {
		int mutatedGene = r.nextInt(Main.TARGET_DNA.getGenes().length);
		dna.getGenes()[mutatedGene].mutate();
	}

	public static void mutatePopulationDna(Population p) {
		for (int i = 0; i < p.getPairs().length; i++) {
			mutateDna(p.getPairs()[i].getOffspring1().getDna());
			mutateDna(p.getPairs()[i].getOffspring2().getDna());
		}
	}

	public static void createNextGeneration(Population p) {
		p.getIndividuals().clear();
		for (int i = 0; i < p.getPairs().length; i++) {
			if (r.nextInt(2) == 0)
				p.getIndividuals().add(p.getPairs()[i].getOffspring1());
			else
				p.getIndividuals().add(p.getPairs()[i].getOffspring2());
		}
	}

	public static void checkForTargetDna(Population p) {
		for (int i = 0; i < p.getIndividuals().size(); i++) {
			if (!found) {
				if (p.getIndividuals().get(i).getDna().equals(Main.TARGET_DNA)) {
					System.out.println("Found the target DNA in " + p.getGenerationNumber()
							+ " generations on individual #" + i + "!");
					foundNumber = i;
					found = true;
				}
			}
		}
	}

	public static void reset() {
		foundNumber = 0;
		found = false;
	}
}