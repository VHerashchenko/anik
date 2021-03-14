package com.vh;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private GridPane gridPane;
    private Integer expertCount = 0;
    private Integer alternativeCount = 0;

    private List<Double> marks = new ArrayList<>();
    private List<Double> calculatedMarks = new ArrayList<>();
    FirstMethod normalMarks = new FirstMethod();
    SecondMethod secondMethod = new SecondMethod();

    @Override
    public void start(Stage stage) {

        gridPane = new GridPane();

        createGridPane();

//        List<String> choices = new ArrayList<>();
//        choices.add("Method 1");
//        choices.add("Method 2");
//        choices.add("Method 3");
//        choices.add("Method 4");
//
//        ChoiceBox choiceBox = new ChoiceBox();
//
//        choiceBox.getItems().addAll(choices);
//        choiceBox.setValue(choices.get(0));
//
//        HBox hBox = new HBox(choiceBox);
//
//        Label chooseMethodLabel = new Label("Choose method: ");

//        gridPane.add(hBox, 1,0);
//        gridPane.add(acceptButton, 2, 0);
//        gridPane.add(chooseMethodLabel, 0, 0);

        Scene scene = new Scene(gridPane, 640, 480);
        stage.setScene(scene);

        stage.show();
    }

    private void alternativeQuestion() {
        Label alternativeQuestionLabel = new Label("How many alternative do we have?   ");
        TextField alternativeTextField = new TextField();
        Button acceptButton = new Button("Accept");

        gridPane.add(alternativeQuestionLabel, 0, 0);
        gridPane.add(alternativeTextField, 1, 0);
        gridPane.add(acceptButton, 2, 0);

        acceptButton.setOnAction(value -> {
            alternativeCount = Integer.valueOf(alternativeTextField.getText());
            expertQuestion();
        });
    }

    private void expertQuestion() {
        Label expertQuestionLabel = new Label("How many experts do we have?   ");
        TextField expertTextField = new TextField();
        Button acceptButton = new Button("Accept");

        gridPane.add(expertQuestionLabel, 0, 1);
        gridPane.add(expertTextField, 1, 1);
        gridPane.add(acceptButton, 2, 1);

        acceptButton.setOnAction(value -> {
            expertCount = Integer.valueOf(expertTextField.getText());
            markQuestion();
        });
    }

    private void markQuestion() {
        List<TextField> markFields = new ArrayList<>();
        TextField timeValue;
        Button acceptButton = new Button("Accept");

        for (int i = 1; i <= expertCount; ++i) {
            gridPane.add(new Label("Expert" + i), 0, 3 + i - 1);
            for (int j = 1; j <= alternativeCount; ++j) {
                if (i == 1) {
                    gridPane.add(new Label(Integer.toString(j)), 1 + j - 1, 2);
                }
                timeValue = new TextField();
                markFields.add(timeValue);
                gridPane.add(timeValue, 1 + j - 1, 3 + i - 1);
            }
        }

        gridPane.add(acceptButton, 1 + alternativeCount, 3 + expertCount);

        acceptButton.setOnAction(value -> {
            marks = new ArrayList<>();
            for (int i = 0; i < markFields.size(); ++i) {
                marks.add(Double.valueOf(markFields.get(i).getText()));
            }
            calculate();
        });
    }

    private void createGridPane() {
        alternativeQuestion();
    }

    private void calculate() {
//        normalMarks.setInputValues(marks);
//        normalMarks.setAmountOfAlternatives(alternativeCount);
//        normalMarks.setAmountOfExpert(expertCount);
//
//        normalMarks.calculateMarks();

        secondMethod.setInputValues(marks);
        secondMethod.setAmountOfSolving(expertCount);
        secondMethod.setAmountOfStage(alternativeCount);

        secondMethod.calculateAll();

        calculatedMarks.add(secondMethod.getOutputGur());
        calculatedMarks.add(secondMethod.getOutputNeg());
        calculatedMarks.add(secondMethod.getOutputPos());
//        calculatedMarks = normalMarks.getNormalMark();

        showResult();
    }

    private void showResult() {
        List<Label> resultNameLabels = new ArrayList<>();
        List<Label> resultLabels = new ArrayList<>();

        for (int i = 0; i < calculatedMarks.size(); ++i) {
            resultNameLabels.add(new Label("x" + (i + 1)));
            resultLabels.add(new Label(calculatedMarks.get(i).toString()));
        }

        for (int i = 0; i < resultLabels.size(); ++i) {
            gridPane.add(resultNameLabels.get(i), 0, 4 + expertCount + i - 1);
            gridPane.add(resultLabels.get(i), 1, 4 + expertCount + i - 1);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}