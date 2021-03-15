package com.vh;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SecondMethod {

    private List<Double> inputValues;
    private Integer amountOfSolving;
    private Integer amountOfStage;

    private Double positiveOutcome = 0.6;
    private List<Double> maxValues;
    private List<Double> minValues;

    private Double outputNeg;
    private Double outputPos;
    private Double outputGur;

    public SecondMethod(){}

    public void setInputValues(List<Double> inputValues){
        this.inputValues = new ArrayList<>(inputValues);
    }

    public void setAmountOfSolving(Integer amountOfSolving){
        this.amountOfSolving = amountOfSolving;
    }

    public void setAmountOfStage(Integer amountOfStage){
        this.amountOfStage = amountOfStage;
    }

    public void setPositiveOutcome(Double positiveOutcome) { this.positiveOutcome = positiveOutcome; }

    public Double getOutputPos() { return this.outputPos; }

    public Double getOutputNeg() { return this.outputNeg; }

    public Double getOutputGur() { return this.outputGur; }

    public void calculateAll(){
        positiveMethod();
        negativeMethod();
        gurvicMethod();
    }

    private void positiveMethod(){
        maxValues = findMaxValues();

        outputPos = maxValueAdd(maxValues);
    }

    private void negativeMethod (){
        minValues = findMinValues();

        outputNeg = maxValueAdd(minValues);
    }

    private void gurvicMethod(){
        List<Double> gurvicSums = new ArrayList<>();
        double currentSum;

        for(int i = 0; i < maxValues.size(); ++i){
            currentSum = maxValues.get(i) * positiveOutcome + (1 - positiveOutcome) * minValues.get(i);
            gurvicSums.add(currentSum);
        }

        outputGur = maxValueAdd(gurvicSums);
    }

    private List<Double> findMaxValues() {
        return Lists.partition(inputValues, amountOfStage).parallelStream()
                .map(list -> list.stream()
                        .max(Comparator.comparingDouble(Double::doubleValue))
                        .orElse(Double.MIN_VALUE))
                .collect(Collectors.toList());
    }

    private List<Double> findMinValues() {
        return Lists.partition(inputValues, amountOfStage).parallelStream()
                .map(list -> list.stream()
                        .min(Comparator.comparingDouble(Double::doubleValue))
                        .orElse(Double.MAX_VALUE))
                .collect(Collectors.toList());
    }

    private Double maxValueAdd(List<Double> valuesList){
        return valuesList.stream()
                .max(Comparator.comparingDouble(Double::doubleValue))
                .orElse(Double.MIN_VALUE);
    }

}
