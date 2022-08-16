/*
 * @Author: shenhuanjie@live.cn
 * @Date: 2022-08-16 19:03:45
 * @LastEditors: shenhuanjie@live.cn
 * @LastEditTime: 2022-08-16 19:12:05
 * @FilePath: \src\main\java\com\example\chapter2\Population.java
 * @Description: population
 * 
 * Copyright (c) 2022 by Guangzhou Qidian Technology Co., Ltd, All Rights Reserved. 
 */
package com.example.chapter2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * 种群
 * 
 * @author shenhuanjie
 * @version 1.0
 * @date 2020/8/16 19:03
 */
public class Population {
    private Individual population[]; // 种群
    private double populationFitness = -1; // 种群适应度

    public Population(int populationSize) {
        this.population = new Individual[populationSize];
    }

    public Population(int populationSize, int chromosomeLength) {
        this.population = new Individual[populationSize];

        for (int individualCount = 0; individualCount < populationSize; individualCount++) {
            Individual individual = new Individual(chromosomeLength);
            this.population[individualCount] = individual;
        }
    }

    public Individual[] getIndividuals() {
        return this.population;
    }

    public Individual getFittest(int offset) {
        Arrays.sort(this.population, new Comparator<Individual>() {
            @Override
            public int compare(Individual o1, Individual o2) {
                if (o1.getFitness() > o2.getFitness()) {
                    return -1;
                } else if (o1.getFitness() < o2.getFitness()) {
                    return 1;
                }
                return 0;
            }
        });
        return this.population[offset];
    }

    public void setPopulationFitness(double fitness) {
        this.populationFitness = fitness;
    }

    public double getPopulationFitness() {
        return this.populationFitness;
    }

    public int size() {
        return this.population.length;
    }

    public Individual setIndividual(int offset, Individual individual) {
        return population[offset] = individual;
    }

    public Individual getIndividual(int offset) {
        return population[offset];
    }

    public void shuffle() {
        Random rnd = new Random();
        for (int i = population.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Individual a = population[index];
            population[index] = population[i];
            population[i] = a;
        }
    }
}
