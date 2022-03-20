package src.main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javafx.scene.text.*;
public class game implements ActionListener {

    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private JTextField one;
    public game() {
        frame = new JFrame("Wordle or Not");
        JButton submit = new JButton("Submit");
        submit.addActionListener(this);
        //Text t = new Text(10, 50, "This is a test");


        one = new JTextField(0);
        JTextField Input = new JTextField(0);

        // submit.addActionListener(t);
        frame.getContentPane().add(Input, BorderLayout.NORTH);
        frame.getContentPane().add(submit, BorderLayout.SOUTH);
        frame.getContentPane().add(one, BorderLayout.CENTER);
        one.setBounds(50, 200, 50, 50);

        //field.addActionListener(this);
        //frame.getContentPane().add(one, new Dimension(50, 50, 50, 50));


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();
        if(s.equals("Submit")){
            one.setBackground(Color.yellow);
        }
    }

    public static void main(String[]args){
        new game();
    }

}