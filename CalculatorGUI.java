/**
 * This program uses GUI to create a functional calculator
 * @author Aaron Ambrose
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener{

    JPanel[] row = new JPanel[5]; //creates a panel for each row of buttons
    JButton[] button = new JButton[19];
    String[] calculatorButtons = {"7","8","9","+","4","5","6","-","1","2","3","*",".","/","CLR","SQRT","+/-","=","0"};

    int[] width = {100, 50, 75, 100};
    int[] height = {30, 40};

    Dimension displayDimension = new Dimension(width[0], height[0]); //Dimensions of the display for the numbers
    Dimension regularDimension = new Dimension(width[1], height[1]); //Dimensions of most of the buttons
    Dimension rColumnDimension = new Dimension(width[2], height[1]); //Dimensions of the buttons in the right column
    Dimension zeroButtonDimension = new Dimension(width[3], height[1]); //Dimensions of the zero button
    boolean[] mathFunction = new boolean[4];
    double[] temporary = {0, 0};
    JTextArea display = new JTextArea(1, 20); //Creates the display
    Font font = new Font("Times new Roman", Font.PLAIN, 12);

    Calculator(){
        super("Calculator");
        setSize(400, 250); //sets the size for the frame
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridLayout grid = new GridLayout(5, 5); //sets the layout of the calculator
        setLayout(grid);

        mathFunction[0] = false; //initializes each of the booleans
        mathFunction[1] = false;
        mathFunction[2] = false;
        mathFunction[3] = false;

        FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER); //Used for the calculator display
        FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER, 1, 1); //used for the rest of the buttons

        for(int i = 0; i < 5; i++){ row[i] = new JPanel(); } //initializes each row in JPanel

        row[0].setLayout(layout1); //sets the display layout

        for(int i = 1; i < 5; i++){ row[i].setLayout(layout2); } //sets the layout for the rest of the buttons

        for(int i = 0; i < 19; i++) { //runs through each button
            button[i] = new JButton();
            button[i].setText(calculatorButtons[i]);
            button[i].setFont(font);
            button[i].addActionListener(this);
        }

        display.setFont(font);
        display.setEditable(false); //makes input from the keyboard not work
        display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        display.setPreferredSize(displayDimension);

        for(int i = 0; i < 14; i++){ button[i].setPreferredSize(regularDimension); }

        for(int i = 14; i < 18; i++){ button[i].setPreferredSize(rColumnDimension); }

        button[18].setPreferredSize(zeroButtonDimension);

        row[0].add(display); //Adds the display to the top
        add(row[0]); //Adds the first row

        for(int i = 0; i < 4; i++){ row[1].add(button[i]); } //Adds the buttons to the row
        row[1].add(button[14]); //Adds the clear button to the right column
        add(row[1]);

        for(int i = 4; i < 8; i++){ row[2].add(button[i]); }
        row[2].add(button[15]);
        add(row[2]);

        for(int i = 8; i < 12; i++){ row[3].add(button[i]); }
        row[3].add(button[16]);
        add(row[3]);

        row[4].add(button[18]);

        for(int i = 12; i < 14; i++){ row[4].add(button[i]); }
        row[4].add(button[17]);
        add(row[4]);

        setVisible(true);
    }

    /**
     * Clears the display
     */
    public void clear(){
        display.setText(" "); //Sets the display blank
        for(int i = 0; i < 4; i++){ mathFunction[i] = false; } //resets each function

        for(int i = 0; i < 2; i++){ temporary[i] = 0; } //resets the temp variables
    }

    /**
     * Calculates the square root
     */
    public void getSqrt() {
        double value =  Math.sqrt(Double.parseDouble(display.getText()));
        display.setText(Double.toString(value));
    }

    /**
     * Returns the negative or positive of the number
     */
    public void getInverse() {
        double value = Double.parseDouble(display.getText());
        if(value != 0) {
            value = value * -1;
            display.setText(Double.toString(value));
        }
    }

    /**
     * Calculates the result
     */
    public void getResult() {
        double result = 0;
        temporary[1] = Double.parseDouble(display.getText()); //second temporary number
        String temporary0 = Double.toString(temporary[0]); //string for first temp
        String temporary1 = Double.toString(temporary[1]); //string for second temp

        if (temporary0.contains("-")) {
            String[] temporary00 = temporary0.split("-", 2); //split into two stings
            temporary[0] = (Double.parseDouble(temporary00[1]) * -1); //puts string back in with real value
        }
        if (temporary1.contains("-")) { //does same thing as above, only with the second temp
            String[] temp11 = temporary1.split("-", 2);
            temporary[1] = (Double.parseDouble(temp11[1]) * -1);
        }

        if (mathFunction[2] == true) {result = temporary[0] * temporary[1];} //Order of Operations
        else if (mathFunction[3] == true) {result = temporary[0] / temporary[1];}
        else if (mathFunction[0] == true) {result = temporary[0] + temporary[1];}
        else if (mathFunction[1] == true) {result = temporary[0] - temporary[1];}

        display.setText(Double.toString(result)); //displays the final result
        for (int i = 0; i < 4; i++) {
            mathFunction[i] = false;
        }
    }

    /**
     * Performs an action for every button press
     * @param event means an action was performed
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == button[0]) { display.append("7"); }

        if(event.getSource() == button[1]) { display.append("8"); }

        if(event.getSource() == button[2]) { display.append("9"); }

        if(event.getSource() == button[3]) { //saves the first number to be added
            temporary[0] = Double.parseDouble(display.getText());
            mathFunction[0] = true;
            display.setText("");
        }
        if(event.getSource() == button[4]) { display.append("4"); }

        if(event.getSource() == button[5]) { display.append("5"); }

        if(event.getSource() == button[6]) { display.append("6"); }

        if(event.getSource() == button[7]) { //subtraction
            temporary[0] = Double.parseDouble(display.getText());
            mathFunction[1] = true;
            display.setText("");
        }

        if(event.getSource() == button[8]) { display.append("1"); }

        if(event.getSource() == button[9]) { display.append("2"); }

        if(event.getSource() == button[10]) { display.append("3"); }

        if(event.getSource() == button[11]) { //multiplication
            temporary[0] = Double.parseDouble(display.getText());
            mathFunction[2] = true;
            display.setText("");
        }

        if(event.getSource() == button[12]) { display.append("."); }

        if(event.getSource() == button[13]) { //division
            temporary[0] = Double.parseDouble(display.getText());
            mathFunction[3] = true;
            display.setText("");
        }

        if(event.getSource() == button[14]) { clear(); }

        if(event.getSource() == button[15]) { getSqrt(); }

        if(event.getSource() == button[16]) { getInverse(); }

        if(event.getSource() == button[17]) { getResult(); }

        if(event.getSource() == button[18]) { display.append("0"); }
    }
    public static void main(String[] arguments) {
        Calculator c = new Calculator();
    }
}
