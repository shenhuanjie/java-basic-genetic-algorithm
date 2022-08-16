/*
 * @Author: shenhuanjie@live.cn
 * @Date: 2022-08-16 18:32:16
 * @LastEditors: shenhuanjie@live.cn
 * @LastEditTime: 2022-08-16 19:42:13
 * @FilePath: \src\main\java\com\example\chapter2\GeneticAlgorithm.java
 * @Description: Genetic Algorithm
 * 
 * Copyright (c) 2022 by Guangzhou Qidian Technology Co., Ltd, All Rights Reserved. 
 */
package com.example.chapter2;

/**
 * Genetic Algorithm
 * 
 * @author shenhuanjie
 * @version 1.0
 * @date 2020/8/16 18:32
 */
public class GeneticAlgorithm {
    private int populationSize; // 种群大小
    private double mutationRate; // 突变率
    private double crossoverRate; // 交叉率
    private int elitismCount; // 精英个数

    public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
    }

    public Population initPopulation(int chromosomeLength) {
        Population population = new Population(this.populationSize, chromosomeLength);
        return population;
    }

    public double calcFitness(Individual individual) {
        // Track number of correct genes
        int correctGenes = 0;

        // Loop over individual's genes
        for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
            // Add one fitness point for each "1" found
            if (individual.getGene(geneIndex) == 1) {
                correctGenes += 1;
            }
        }

        // Calculate fitness
        double fitness = (double) correctGenes / individual.getChromosomeLength();

        // Store fitness
        individual.setFitness(fitness);

        return fitness;
    }

    public void evalPopulation(Population population) {
        double populationFitness = 0;
        for (Individual individual : population.getIndividuals()) {
            populationFitness += calcFitness(individual);
        }

        population.setPopulationFitness(populationFitness);
    }

    public boolean isTerminationConditionMet(Population population) {
        for (Individual individual : population.getIndividuals()) {
            if (individual.getFitness() == 1) {
                return true;
            }
        }
        return false;
    }

    public Individual selectParent(Population population) {
        // Get individuals
        Individual individuals[] = population.getIndividuals();

        // Spin roulette wheel
        double populationFitness = population.getPopulationFitness();
        double rouletteWheelPosition = Math.random() * populationFitness;

        // Find parent
        double spinWheel = 0;
        for (Individual individual : individuals) {
            spinWheel += individual.getFitness();
            if (spinWheel >= rouletteWheelPosition) {
                return individual;
            }
        }
        return individuals[population.size() - 1];
    }

    public Population crossoverPopulation(Population population) {
        // Create new population
        Population newPopulation = new Population(population.size());

        // Loop over current population by fitness
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            Individual parent1 = population.getFittest(populationIndex);

            // Apply crossover to this individual?
            if (this.crossoverRate > Math.random() && populationIndex > this.elitismCount) {
                // Initialize offspring
                Individual offspring = new Individual(parent1.getChromosomeLength());

                // Find second parent
                Individual parent2 = selectParent(population);

                // Loop over genome
                for (int geneIndex = 0; geneIndex < parent1.getChromosomeLength(); geneIndex++) {
                    // Use half of parent1's genes and half of parent2's genes
                    if (0.5 > Math.random()) {
                        offspring.setGene(geneIndex, parent1.getGene(geneIndex));
                    } else {
                        offspring.setGene(geneIndex, parent2.getGene(geneIndex));
                    }
                }

                // Add offspring to new population
                newPopulation.setIndividual(populationIndex, offspring);
            } else {
                // Add individual to new population without applying crossover
                newPopulation.setIndividual(populationIndex, parent1);
            }
        }

        return newPopulation;
    }

    public Population mutatePopulation(Population population) {

        // Initialize new population
        Population newPopulation = new Population(this.populationSize);

        // Loop over current population by fitness
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            Individual individual = population.getFittest(populationIndex);

            // Loop over individual's genes
            for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
                // Skip mutation if this is an elite individual
                if (populationIndex >= this.elitismCount) {
                    // Does this gene need mutation?
                    if (this.mutationRate > Math.random()) {
                        // Get new gene
                        int newGene = 1;
                        if (individual.getGene(geneIndex) == 1) {
                            newGene = 0;
                        }
                        // Mutate gene
                        individual.setGene(geneIndex, newGene);
                    }
                }
            }
            // Add individual to population
            newPopulation.setIndividual(populationIndex, individual);
        }

        // Return mutated population
        return newPopulation;
    }
}
