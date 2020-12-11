package com.sierox.genetic.algorithm;
import java.util.ArrayList;
import java.util.Random;

import com.sierox.genetic.model.Individual;

public class RandomSelector {
    ArrayList<Individual> items = new ArrayList<Individual>();
    Random rand = new Random();
    int totalSum = 0;

    public RandomSelector(ArrayList<Individual> items) {
    	this.items = items;
        for(Individual item : items) {
            totalSum = (int) (totalSum + item.getFitness());
        }
    }

    public Individual getRandom() {

        int index = rand.nextInt(totalSum);
        int sum = 0;
        int i=0;
        while(sum < index ) {
             sum = (int) (sum + items.get(i++).getFitness());
        }
        return items.get(Math.max(0,i-1));
    }
}