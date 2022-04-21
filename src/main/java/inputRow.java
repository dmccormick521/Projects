package src.main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class inputRow {
    //so what do I want this class to do
    //take over functionality for inputs
    //should i make it one big object?
    private int correct;
    private Font font1 = new Font("SansSerif", Font.BOLD, 30);
    private ArrayList<JTextField> lists;
    private final static int wordLength = 5;

    public inputRow() {
        lists = new ArrayList<>();
        for (int x = 0; x < wordLength; x++) {
            JTextField text = new JTextField();
            text.setDocument(new src.main.java.textFieldLimit(1));
            text.setFont(font1);
            text.setEditable(false);
            lists.add(text);
        }
    }

    //checks if all entries are filled and submission is allowed
    public boolean isFilled() {
        for (JTextField tField : lists) {
            if (tField.getText().equals(null)) {
                return false;
            }
        }
        return true;
    }

    public boolean correctChecker(String answer) {
        ArrayList<String> compareArray = new ArrayList<>();
        int initArr = 0;
        correct = 0;
        //initializes comparison array
        while (initArr < wordLength) {
            compareArray.add(String.valueOf(answer.charAt(initArr)));
            initArr++;
        }
        //loop to compare values and change background
        for (int x = 0; x < wordLength; x++) {
            if (answer.charAt(x) == lists.get(x).getText().charAt(0)) {
                lists.get(x).setBackground(Color.green);
                correct++;
                compareArray.remove(String.valueOf(answer.charAt(x)));
            } else if (compareArray.contains(lists.get(x).getText()) && lists.get(x).getBackground() != Color.yellow && lists.get(x).getBackground() != Color.green) {
                lists.get(x).setBackground(Color.yellow);
                compareArray.remove(lists.get(x).getText());
            } else {
                lists.get(x).setBackground(Color.gray);
            }
        }
        if (correct == wordLength) {
            return true;
        }
        return false;
    }

    public void setEditable() {
        for (JTextField tField : lists) {
            tField.setEditable(true);
        }
    }

}
