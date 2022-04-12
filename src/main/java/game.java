package src.main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

//This is an attempt at making wordle
public class game implements ActionListener {

    //List holds our JTextField objects
    //Correct tracks current right on each submit
    //Count tracks our current location in 'Lists'
    //answer is a placeholder for a random word for now
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
            int i = 0;
            correct = 0;
            while (i < 5) {
                if (answer.charAt(i) == lists.get(count).getText().charAt(0)) {
                    lists.get(count).setBackground(Color.green);
                    correct++;
                } else if (answer.contains(lists.get(count).getText())) {
                    lists.get(count).setBackground(Color.yellow);
                } else {
                    lists.get(count).setBackground(Color.gray);
                }
                lists.get(count).setEditable(false);
                count++;
                i++;
            }
            if (correct != 5) {
                lineEditable();
            }

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
    //,so it will only run with proper input
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