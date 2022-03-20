package src.main.java;


import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class textFieldLimit extends PlainDocument {
    private int limit;

    textFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
        if (str == null) return;

        if (((getLength() + str.length()) <= limit)   && str.matches("[a-zA-Z]")) {
            super.insertString(offset, str, attr);
        }
    }
}
