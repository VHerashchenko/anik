package com.vh;

import java.util.ArrayList;
import java.util.List;

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
        maxValues = new ArrayList<>(findMinMaxValues(true));

        outputPos = maxValueAdd(maxValues);
    }

    private void negativeMethod (){
        minValues = new ArrayList<>(findMinMaxValues(false));

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

    private List<Double> findMinMaxValues(Boolean finder){
        double currentValue;
        double maxValue = inputValues.get(0);
        double minValue = maxValue;

        List<Double> maxValueList = new ArrayList<>();
        List<Double> minValueList = new ArrayList<>();

        for(int i = 0; i < amountOfSolving; ++i){
            for(int j = 0; j < amountOfStage; ++j){
                currentValue = inputValues.get(amountOfStage * i + j);
                if(maxValue < currentValue){
                    maxValue = currentValue;
                }
                else if(minValue > currentValue) {
                    minValue = currentValue;
                }
            }
            maxValueList.add(maxValue);
            minValueList.add(minValue);
            maxValue = inputValues.get(amountOfStage * i);
            minValue = maxValue;
        }



        return finder? maxValueList : minValueList;
    }

    private Double maxValueAdd(List<Double> valuesList){
        double maxValue = valuesList.get(0);
        double currentValue;

        for(int i = 0; i < valuesList.size(); ++i){
            currentValue = valuesList.get(i);
            if(currentValue > maxValue){
                maxValue = currentValue;
            }
        }
        return maxValue;
    }

}
