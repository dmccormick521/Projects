package src.main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javafx.scene.text.*;
public class game implements ActionListener {
    ArrayList<JTextField> lists = new ArrayList<JTextField>();
    private int count=0;
    private JFrame frame;
    private String answer = "hello";

    public game() {
        frame = new JFrame("Wordle or Not");
        JButton submit = new JButton("Submit");
        submit.addActionListener(this);
        lists = new ArrayList<JTextField>();
        for(int x = 0 ; x<20; x++){
            lists.add(new JTextField());
            frame.add(lists.get(x));
        }

        JTextField Input = new JTextField(0);
        GridLayout gl = new GridLayout(5,5);
        frame.setLayout(gl);
        frame.add(submit);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //Need to check for edge cases later
    //Multiple of same character - how to highlight correctly and not overwrite
    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();
        if(s.equals("Submit")){
            int i = 0;
            while(i<5){
                if(answer.charAt(i)==lists.get(count).getText().charAt(0) ){
                    lists.get(count).setBackground(Color.green);
                }
                else if(answer.contains(lists.get(count).getText())){
                    lists.get(count).setBackground(Color.yellow);
                }
                else{
                    lists.get(count).setBackground(Color.gray);
                }
                lists.get(count).setEditable(false);
                count++;
                i++;
            }
        }

    }


    public static void main(String[]args){
        new game();
    }

}