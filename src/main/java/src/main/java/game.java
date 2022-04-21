package src.main.java;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

//Simple attempt at making a function wordle
//Could definitely move around some methods and such to clean up code a bit
//Or create objects/classes for some things
//But main goal was just to try a new IDE with a fun project and get it running well
public class game implements ActionListener {

    //List holds our JTextField objects
    //Correct tracks current right on each submit
    //Count tracks our current location in 'Lists'
    InputStream stream = game.class.getResourceAsStream("/src/main/java/answers");
    private final String answer = gameAnswer(stream);
    private final static int wordLength = 5;
    private final int attempts = 5;
    private int count = 0;
    ArrayList<inputRow> lists;
    JFrame frame;

    public game() throws Exception {
        frame = new JFrame("Wordle or Not");
        JOptionPane.showMessageDialog(null,
                "Lowercase Only!  Good luck!",
                "Instructions",
                JOptionPane.WARNING_MESSAGE);
        JButton submit = new JButton("Submit");
        submit.addActionListener(this);
        initLists();
        initGridAndFrame(submit);
    }

    //deals with button click for submit button
    //checks object to see if all values are filled in
    //checks object to see if values are correct and if game has ended in some way
    //increments array counter (count) for next input row object
    //sets next input row object to be editable
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Submit") && lists.get(count).isFilled()) {

            if (lists.get(count).correctChecker(answer)) {
                JOptionPane.showMessageDialog(null,
                        "YOU WIN!",
                        "Victory!",
                        JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }

            count += 1;
            if (count == attempts) {
                JOptionPane.showMessageDialog(null,
                        "Good try!" + "  The word was [" + answer + "]",
                        "Better luck next time!",
                        JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
            lists.get(count).setEditable();

        }

    }

    //fills list with inputRow objects for manipulation
    //calls method for first object to allow for inputs
    public void initLists() {
        lists = new ArrayList<>();
        inputRow rows;
        for (int x = 0; x < attempts; x++) {
            rows = new inputRow(frame);
            lists.add(rows);
        }
        lists.get(0).setEditable();
    }

    //sets visual layout for frames
    //could possibly change towards a grid bag layout
    public void initGridAndFrame(JButton submit) {
        GridLayout gl = new GridLayout(attempts + 1, wordLength);
        frame.setLayout(gl);
        frame.add(submit, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(wordLength * 125, attempts * 125);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //grabs answer from a random line in the text file provided
    //currently not sure of a great way to check the size of the file since I'm using an input stream...
    //unless I just loop through the stream and count the lines that way
    public String gameAnswer(InputStream file) throws Exception {
        int cur = 0;
        String randomString = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));
        long random = System.currentTimeMillis();
        while (random > 210) {
            random %= 100;
        }
        while (cur != random && reader.ready()) {
            randomString = reader.readLine();
            cur++;
        }
        reader.close();
        return randomString;
    }

    public static void main(String[] args) throws Exception {
        new game();
    }

}