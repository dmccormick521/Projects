package src.main.java;

import javax.swing.*;
import java.awt.*;

public class game {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Wordle or Not");
        JLabel emptyLabel = new JLabel("");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}