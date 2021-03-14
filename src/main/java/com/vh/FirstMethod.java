package com.vh;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FirstMethod {

    private List<Integer> inputValues;
    private Integer amountOfExpert;
    private Integer amountOfAlternative;

    private List<Integer> sumExpertMarks = new ArrayList<>();
    private List<Double> normalMark = new ArrayList<>();

    public FirstMethod(){}

    private void calculateSum(){
        sumExpertMarks = Lists.partition(inputValues, amountOfAlternative).parallelStream()
                .map(list -> list.stream().reduce(0, Integer::sum))
                .collect(Collectors.toList());
    }

    private void calculateNormalMark(){
        double currentValue;
        double currentSum = 0;

        for(int i = 0; i < amountOfAlternative; ++i){
            for (int j = 0; j < amountOfExpert; ++j){
                currentValue = ((double)inputValues.get(amountOfAlternative * j + i) / sumExpertMarks.get(j));
                currentSum += currentValue;
            }

            currentSum /= amountOfExpert;
            normalMark.add(currentSum);
            currentSum = 0;
        }
    }

    public void calculateMarks(){
        calculateSum();
        calculateNormalMark();
    }

    public List<Double> getNormalMark(){
        return normalMark;
    }

    public void setAmountOfAlternatives(Integer amountOfAlternative){
        this.amountOfAlternative = amountOfAlternative;
    }

    public void setAmountOfExpert(Integer amountOfExpert){
        this.amountOfExpert = amountOfExpert;
    }

    public void setInputValues(List<Integer> inputValues){
        this.inputValues = new ArrayList<>(inputValues);
    }

}
