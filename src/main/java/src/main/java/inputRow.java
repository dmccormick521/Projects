package src.main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class inputRow {
    private final ArrayList<JTextField> lists;
    private final static int wordLength = 5;

    public inputRow(JFrame input) {
        Font font1 = new Font("SansSerif", Font.BOLD, 30);
        lists = new ArrayList<>();
        for (int x = 0; x < wordLength; x++) {
            JTextField text = new JTextField();
            text.setDocument(new textFieldLimit(1));
            text.setFont(font1);
            text.setEditable(false);
            lists.add(text);
            input.add(text);
        }
    }

    //checks if all entries are filled and submission is allowed
    public boolean isFilled() {
        for (JTextField tField : lists) {
            if (tField.getText().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    //handles logic for comparing object values to answer
    public boolean correctChecker(String answer) {
        ArrayList<String> compareArray = new ArrayList<>();
        int initArr = 0;
        int correct = 0;
        //initializes comparison array
        while (initArr < wordLength) {
            compareArray.add(String.valueOf(answer.charAt(initArr)));
            initArr++;
        }
        //loop to compare values and change background
        //green loop
        for (int x = 0; x < wordLength; x++) {
            if (answer.charAt(x) == lists.get(x).getText().charAt(0)) {
                lists.get(x).setBackground(Color.green);
                correct++;
                compareArray.remove(String.valueOf(answer.charAt(x)));
            }
        }
        //yellow/gray loop
        for (int x = 0; x < wordLength; x++) {
            if (compareArray.contains(lists.get(x).getText()) && lists.get(x).getBackground() != Color.green) {
                lists.get(x).setBackground(Color.yellow);
                compareArray.remove(lists.get(x).getText());
            } else if (lists.get(x).getBackground() != Color.green && lists.get(x).getBackground() != Color.yellow) {
                lists.get(x).setBackground(Color.gray);
            }
        }
        return correct == wordLength;
    }

    //helper that allows values to be changed
    public void setEditable() {
        for (JTextField tField : lists) {
            tField.setEditable(true);
        }
    }

}
