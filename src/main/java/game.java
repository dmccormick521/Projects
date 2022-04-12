package src.main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

//Simple attempt at making a function wordle
//Could definitely move around some methods and such to clean up code a bit
//Or create objects/classes for some things
//But main goal was just to try a new IDE with a fun project and get it running well
public class game implements ActionListener {

    //List holds our JTextField objects
    //Correct tracks current right on each submit
    //Count tracks our current location in 'Lists'
    File fileInput = new File("src/main/java/answers");
    ArrayList<JTextField> lists;
    Font font1 = new Font("SansSerif", Font.BOLD, 30);
    private int count = 0;
    private int correct = 0;
    JFrame frame;
    private final String answer = gameAnswer(fileInput);

    //textFieldLimit class is connected here and is what restricts our input correctly for JTextFields
    public game() throws Exception {
        frame = new JFrame("Wordle or Not");
        JButton submit = new JButton("Submit");
        submit.addActionListener(this);
        initLists();
        initGridAndFrame(submit);
    }

    //Need to check for edge cases later
    //Multiple of same character - how to highlight correctly and not overwrite
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Submit") && correct != 5 && allFilled()) {
            ArrayList<String> arr = new ArrayList<>();
            int initArr = 0;
            correct = 0;

            while (initArr < 5) {
                arr.add(String.valueOf(answer.charAt(initArr)));
                System.out.println(arr.get(initArr));
                initArr++;

            }
            correctHelper(arr);
            count += 5;
            if (correct != 5) {
                lineEditable();
            }

        }

    }

    //Helper for readability purposes - loop logic
    //If value is correct and right spot it removes it from an array with all the possible values and makes frame green.
    //If value is correct but wrong spot it checks to make sure it isn't green first
    //Then it sets the frame yellow and removes it from the array
    //otherwise it defaults gray
    public void correctHelper(ArrayList<String> arr) {
        int x = 0;
        for (int loop = count; loop < count + 5; loop++) {
            if (answer.charAt(x) == lists.get(loop).getText().charAt(0)) {
                lists.get(loop).setBackground(Color.green);
                correct++;
                arr.remove(String.valueOf(answer.charAt(x)));
            } else if (arr.contains(lists.get(loop).getText()) && lists.get(loop).getBackground() != Color.yellow && lists.get(loop).getBackground() != Color.green) {
                lists.get(loop).setBackground(Color.yellow);
                arr.remove(lists.get(loop).getText());
            } else {
                lists.get(loop).setBackground(Color.gray);
            }
            x++;
        }
    }

    //Helper method to only allow current line to receive input
    //Uses count variable to set each JTextField to be editable on current line
    public void lineEditable() {
        if (count < 20) {
            for (int x = count; x < count + 5; x++) {
                lists.get(x).setEditable(true);
            }
        }
    }

    //Checks if current line is filled up
    //If we hit the 'catch' it's likely because a null pointer and won't take down the program
    //So it will only run with proper input
    public boolean allFilled() {
        for (int x = count; x < count + 5; x++) {
            try {
                lists.get(x).getText().charAt(0);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    //contains many jframe values that are manipulated through this list
    public void initLists() {
        lists = new ArrayList<>();
        for (int x = 0; x < 20; x++) {
            lists.add(new JTextField());
            lists.get(x).setDocument(new textFieldLimit(1));
            lists.get(x).setFont(font1);
            frame.add(lists.get(x));
            if (x > 4) {
                lists.get(x).setEditable(false);
            }
        }
    }

    public void initGridAndFrame(JButton submit) {
        GridLayout gl = new GridLayout(5, 5);
        frame.setLayout(gl);
        frame.add(submit);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //grabs answer from a random line in the text file provided
    public String gameAnswer(File file) throws Exception {
        RandomAccessFile f = new RandomAccessFile(file, "r");
        long random = System.currentTimeMillis();
        while (random > f.length()) {
            random %= 100;
        }
        f.seek(random);
        f.readLine();
        String randomString = f.readLine();
        f.close();
        return randomString;
    }

    public static void main(String[] args) throws Exception {
        new game();
    }

}