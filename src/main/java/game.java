package src.main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class game implements ActionListener {
    ArrayList<JTextField> lists;
    private int count=0;
    private int correct=0;
    JFrame frame;
    private String answer = "hello";

    public game() {
        frame = new JFrame("Wordle or Not");
        JButton submit = new JButton("Submit");
        submit.addActionListener(this);
        lists = new ArrayList<>();
        for(int x = 0 ; x<20; x++){
            lists.add(new JTextField());
            lists.get(x).setDocument(new textFieldLimit(1));
            frame.add(lists.get(x));
            if(x>4) {
                lists.get(x).setEditable(false);
            }
        }

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
        if(s.equals("Submit")  && correct!=5 && allFilled()){
            int i = 0;
            correct=0;
            while(i<5){
                if(answer.charAt(i)==lists.get(count).getText().charAt(0) ){
                    lists.get(count).setBackground(Color.green);
                    correct++;
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
            if(correct!=5) {
                lineEditable();
            }

        }

    }

    public void lineEditable(){
        if(count<20){
        for(int x = count; x<count+5; x++){
        lists.get(x).setEditable(true);
        }
    }
    }

    public boolean allFilled(){
            for(int x = count; x<count+5; x++) {
                try{
                    lists.get(x).getText().charAt(0);
                }
                catch(Exception e){
                        return false;
                }
            }
        return true;
    }

    public static void main(String[]args){
        new game();
    }

}