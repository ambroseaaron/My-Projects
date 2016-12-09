/**
 * ColorChooser class and MyColor class that creates a GUI with 3 sliders and a display area
 * The display area shows the color that the three sliders end up creating
 * @author Aaron Ambrose
 */
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorChooser extends JFrame {
    public ColorChooser() {
        getContentPane().add(new MyColor());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }
}

class MyColor extends JPanel {
    //Creates the color canvas, text boxes for the value
    //of the sliders, the three sliders, and a slider listener
    DrawingCanvas canvas = new DrawingCanvas();
    JTextArea textFieldR = new JTextArea(1,1);
    JTextArea textFieldG = new JTextArea(1,1);
    JTextArea textFieldB = new JTextArea(1,1);
    JSlider sliderR, sliderG, sliderB;
    ChangeListener listener;

    /**
     * MyColor constructor that creates the GUI with 3 sliders
     * and a color display
     */
    public MyColor() {
        //Initialize each of the three sliders
        sliderR = getSlider(0, 255, 0, 50, 5);
        sliderG = getSlider(0, 255, 0, 50, 5);
        sliderB = getSlider(0, 255, 0, 50, 5);
        sliderR.addChangeListener(listener);
        sliderG.addChangeListener(listener);
        sliderB.addChangeListener(listener);

        //Creates the panel to display sliders, textAreas, and the color canvas
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 20, 5));

        panel.add(new JLabel("Red Slider (0 - 255)"));
        panel.add(sliderR);
        panel.add(textFieldR);
        panel.add(new JLabel("Green Slider (0 - 255)"));
        panel.add(sliderG);
        panel.add(textFieldG);
        panel.add(new JLabel("Blue Slider (0 - 255)"));
        panel.add(sliderB);
        panel.add(textFieldB);

        add(panel, BorderLayout.CENTER);
        add(canvas);
    }

    /**
     * GetSlider method that
     * @param min lowest number the slider can be
     * @param max highest number the slider can be
     * @param init initial value the slider is set to
     * @param mjrTkSp spacing between displayed number increments
     * @param mnrTkSp spacing between displayed tick marks
     * @return slider
     */
    public JSlider getSlider(int min, int max, int init, int mjrTkSp, int mnrTkSp) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, init);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(mjrTkSp);
        slider.setMinorTickSpacing(mnrTkSp);
        slider.setPaintLabels(true);

        //updates the slider value and the color canvas whenever
        //a change in a slider occurs
        slider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e) {
                JSlider slider = (JSlider) e.getSource();
                if(slider == sliderR){
                    textFieldR.setText(String.valueOf(slider.getValue()));
                    canvas.redValue = slider.getValue();
                    displayRGBColor();
                }
                if(slider == sliderG){
                    textFieldG.setText(String.valueOf(slider.getValue()));
                    canvas.greenValue = slider.getValue();
                    displayRGBColor();
                }
                if(slider == sliderB){
                    textFieldB.setText(String.valueOf(slider.getValue()));
                    canvas.blueValue = slider.getValue();
                    displayRGBColor();
                }
            }

            /**
             * Method that displays the color that the three sliders create
             */
            public void displayRGBColor() {
                canvas.setBackgroundColor();
                Color.RGBtoHSB(canvas.redValue, canvas.greenValue, canvas.blueValue,canvas.hsbValues);
            }
        });

        textFieldR.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                String typed = textFieldR.getText();
                sliderR.setValue(0);
                //if text is not a digit or is too big of a number
                if(!typed.matches("\\d+") || typed.length() > 3) {
                    return;
                }
                int value = Integer.parseInt(typed);
                sliderR.setValue(value);

            }
        });

        textFieldG.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                String typed = textFieldG.getText();
                sliderG.setValue(0);
                //if text is not a digit or is too big of a number
                if(!typed.matches("\\d+") || typed.length() > 3) {
                    return;
                }
                int value = Integer.parseInt(typed);
                sliderG.setValue(value);
            }
        });

        textFieldB.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent ke) {
                String typed = textFieldB.getText();
                sliderB.setValue(0);
                //if text is not a digit or is too big of a number
                if(!typed.matches("\\d+") || typed.length() > 3) {
                    return;
                }
                int value = Integer.parseInt(typed);
                sliderB.setValue(value);
            }
        });

        return slider;
    }

    class DrawingCanvas extends Canvas {
        Color color;
        int redValue, greenValue, blueValue;
        int alphaValue = 255;
        float[] hsbValues = new float[3];

        /**
         * Initialized the colored canvas to black
         */
        public DrawingCanvas() {
            setSize(350, 350);
            color = new Color(0, 0, 0);
            setBackgroundColor();
        }

        /**
         * Updates the color whenever a slider value is changed
         */
        public void setBackgroundColor() {
            color = new Color(redValue, greenValue, blueValue, alphaValue);
            setBackground(color);
        }
    }
}
