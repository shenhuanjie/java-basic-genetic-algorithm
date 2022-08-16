/*
 * @Author: shenhuanjie@live.cn
 * @Date: 2022-08-16 18:48:39
 * @LastEditors: shenhuanjie@live.cn
 * @LastEditTime: 2022-08-16 20:05:37
 * @FilePath: \src\main\java\com\example\chapter2\AllOnesGA.java
 * @Description: All Ones GA
 * 
 * Copyright (c) 2022 by Guangzhou Qidian Technology Co., Ltd, All Rights Reserved. 
 */
package com.example.chapter2;

public class AllOnesGA {
    public static void main(String[] args) {
        // Create GA object
        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.001, 0.95, 2);

        // Initialize population
        Population population = ga.initPopulation(50);

        // Evaluate population
        ga.evalPopulation(population);

        // Keep track of current generation
        int generation = 1;

        while (ga.isTerminationConditionMet(population) == false) {
            // Print fittest individual from population
            System.out.println("Best solution: " + population.getFittest(0).toString());

            // Apply crossover
            population = ga.crossoverPopulation(population);

            // Apply mutation
            population = ga.mutatePopulation(population);

            // Evaluate population
            ga.evalPopulation(population);

            // Increment the current generation
            generation++;
        }

        System.out.println("Found solution in " + generation + " generations");
        System.out.println("Best solution: " + population.getFittest(0).toString());
    }
}