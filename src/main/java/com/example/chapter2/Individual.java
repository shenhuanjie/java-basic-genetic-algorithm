/*
 * @Author: shenhuanjie@live.cn
 * @Date: 2022-08-16 18:53:13
 * @LastEditors: shenhuanjie@live.cn
 * @LastEditTime: 2022-08-16 19:00:50
 * @FilePath: \src\main\java\com\example\chapter2\Individual.java
 * @Description: Individual
 * 
 * Copyright (c) 2022 by Guangzhou Qidian Technology Co., Ltd, All Rights Reserved. 
 */
package com.example.chapter2;

public class Individual {
    private int[] chromosome; // 染色体
    private double fitness = -1; // 适应度

    /**
     * 构造函数
     * 
     * @param chromosome 染色体
     */
    public Individual(int[] chromosome) {
        // Create individual
        this.chromosome = chromosome;
    }

    /**
     * 构造函数
     * 
     * @param chromosomeLength 染色体长度
     */
    public Individual(int chromosomeLength) {
        this.chromosome = new int[chromosomeLength];
        for (int gene = 0; gene < chromosomeLength; gene++) {
            if (0.5 < Math.random()) {
                this.setGene(gene, 1);
            } else {
                this.setGene(gene, 0);
            }
        }
    }

    /**
     * 获取染色体
     * 
     * @return 染色体
     */
    public int[] getChromosome() {
        return this.chromosome;
    }

    /**
     * 获取染色体长度
     * 
     * @return 染色体长度
     */
    public int getChromosomeLength() {
        return this.chromosome.length;
    }

    /**
     * 设置基因
     * 
     * @param offset 偏移量
     * @param gene   基因
     */
    public void setGene(int offset, int gene) {
        this.chromosome[offset] = gene;
    }

    /**
     * 获取基因
     * 
     * @param offset 偏移量
     * @return 基因
     */
    public int getGene(int offset) {
        return this.chromosome[offset];
    }

    /**
     * 设置适应度
     * 
     * @param fitness 适应度
     */
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    /**
     * 获取适应度
     * 
     * @return 适应度
     */
    public double getFitness() {
        return this.fitness;
    }

    /**
     * 获取染色体字符串
     * 
     * @return 染色体字符串
     */
    public String toString() {
        String output = "";
        for (int gene = 0; gene < this.chromosome.length; gene++) {
            output += this.chromosome[gene];
        }
        return output;
    }
}
