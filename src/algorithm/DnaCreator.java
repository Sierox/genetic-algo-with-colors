package com.sierox.genetic.algorithm;

import java.util.Random;

import com.sierox.genetic.Main;
import com.sierox.genetic.model.Dna;
import com.sierox.genetic.model.Gene;

public class DnaCreator {

	public static Dna dnaFromPhenotype(int[] phenotype) {
		StringBuilder sbBig = new StringBuilder();
		for (int i = 0; i < phenotype.length; i++) {
			StringBuilder sbSmall = new StringBuilder();
			sbSmall.append(Integer.toBinaryString(phenotype[i]));
			while(sbSmall.length() < 8){
				sbSmall = new StringBuilder("0" + sbSmall);
			}
			sbBig.append(sbSmall);
		}
		
		Gene[] genes = new Gene[Main.TARGET_DNA.getGenes().length];
		for (int i = 0; i < genes.length; i++) {
			genes[i] = new Gene(Integer.parseInt(sbBig.substring(i, i+1)));
		}
		return new Dna(genes);
	}
	
	public static Dna getRandomDna() {
		Dna dna = new Dna();
		Random r = new Random();
		for (int i = 0; i < Main.TARGET_DNA.getGenes().length; i++) {
			dna.getGenes()[i] = new Gene(r.nextInt(2));
		}
		return dna;
	}
	 
	public static Dna getDefaultDna() {
		return new Dna(new Gene[] {
				/* Red */
				new Gene(0), new Gene(0), new Gene(0), new Gene(0), new Gene(0), new Gene(0), new Gene(0), new Gene(0),
				/* Green */
				new Gene(0), new Gene(0), new Gene(0), new Gene(0), new Gene(0), new Gene(0), new Gene(0), new Gene(0), 
				/* Blue */
				new Gene(0), new Gene(0), new Gene(0), new Gene(0), new Gene(0), new Gene(0), new Gene(0), new Gene(0)
				});
	}
}
