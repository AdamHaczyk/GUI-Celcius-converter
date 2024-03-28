import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

    public static int currentScaleTo = 1; // == Fahrenheit
    // 2 == Kelvin
    // 3 == Celsius

    public static int currentScaleFrom = 1; // == Celsius
    // 2 == Fahrenheit
    // 3 == Kelvin


    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                temperatureConverter();
            }
        });
    }

    public static void temperatureConverter(){


        JFrame frame = new JFrame("Converter");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel resultLabel = new JLabel("Converting to Fahrenheit");
        JLabel label1 = new JLabel("Input temperature in Celsius:");

        JButton convertButton = new JButton("Convert");
        JButton switchButtonFrom2 = new JButton("\u2B9E");  // switches to the right
        JButton switchButtonFrom1 = new JButton("\u2B9C");  // switches to the left
        JButton switchButtonTo1 = new JButton("\u2B9C");    // switches to the left
        JButton switchButtonTo2 = new JButton("\u2B9E");    // switches to the right

        JTextField textField = new JTextField(20);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel centralPanel = new JPanel();

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        //Northern panel setup
        panel1.setLayout(new BorderLayout());
        panel1.add(switchButtonFrom1, BorderLayout.WEST);
        panel1.add(switchButtonFrom2, BorderLayout.EAST);
        panel1.add(label1, BorderLayout.CENTER);

        //Central panel setup
        centralPanel.setLayout(new BorderLayout());
        centralPanel.add(textField, BorderLayout.NORTH);
        centralPanel.add(convertButton, BorderLayout.SOUTH);

        //Southern panel setup
        panel2.setLayout(new BorderLayout());
        panel2.add(switchButtonTo1, BorderLayout.WEST);
        panel2.add(resultLabel);
        panel2.add(switchButtonTo2, BorderLayout.EAST);
        //panel1.setBorder(BorderFactory.createBevelBorder(1));                               //Create a border for the panel1 component.
        //centralPanel.setBorder((BorderFactory.createEtchedBorder(Color.GREEN, Color.BLACK)));   //Create a border for the centralPanel component.


        //ActionListeners
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temperature = textField.getText();
                Float temp = Float.parseFloat(temperature);
                Float result = Convert(temp, currentScaleFrom, currentScaleTo);


                if(currentScaleTo == 1) {
                    resultLabel.setText(result + "°F"); // Converting from Celsius to Fahrenheit
                }
                if(currentScaleTo == 2) {
                    resultLabel.setText(result + "K"); //Converting from Celsius to Kelvin
                }
                if(currentScaleTo == 3) {
                    resultLabel.setText(result + "°C"); //Converting from Celsius to Celsius
                }
                //convertButton.setEnabled(false);                             //this disables the button.
            }
        });

        //Below is a KeyListener. I made it so that when you press ENTER while on the text field,
        //convertButton gets pressed and the temperature is converted.
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    convertButton.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        switchButtonTo1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                currentScaleTo--;

                if(currentScaleTo < 1) currentScaleTo = 3;

                if (currentScaleTo == 1) resultLabel.setText("Converting to Fahrenheit");
                if (currentScaleTo == 2) resultLabel.setText("Converting to Kelvin");
                if (currentScaleTo == 3) resultLabel.setText("Converting to Celsius");
            }
        });

        switchButtonTo2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                currentScaleTo++;

                if(currentScaleTo > 3) currentScaleTo = 1;

                if (currentScaleTo == 1) resultLabel.setText("Converting to Fahrenheit");
                if (currentScaleTo == 2) resultLabel.setText("Converting to Kelvin");
                if (currentScaleTo == 3) resultLabel.setText("Converting to Celsius");
            }
        });

        switchButtonFrom1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                currentScaleFrom--;

                if (currentScaleFrom < 1) currentScaleFrom = 3;

                if (currentScaleFrom == 1) label1.setText("Input temperature in Celsius");
                if (currentScaleFrom == 2) label1.setText("Input temperature in Fahrenheit");
                if (currentScaleFrom == 3) label1.setText("Input temperature in Kelvin");
            }
        });

        switchButtonFrom2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                currentScaleFrom++;

                if(currentScaleFrom > 3) currentScaleFrom = 1;

                if (currentScaleFrom == 1) label1.setText("Input temperature in Celsius");
                if (currentScaleFrom == 2) label1.setText("Input temperature in Fahrenheit");
                if (currentScaleFrom == 3) label1.setText("Input temperature in Kelvin");
            }
        });

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(centralPanel, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



    }

    public static Float Convert(Float temperature, int from, int to){

        Float result = 0F;

        //Converting from Celsius
        if(from == 1){

            if(to == 1){
                result = (float) ((temperature * 1.8) + 32);
            } //To Fahrenheit

            if(to == 2){
                result = (float) (temperature + 273);
            } //To Kelvin

            if (to == 3) result = temperature; // To Celsius
        }

        //Converting from Fahrenheit
        if(from == 2){

            if (to == 1) result = temperature; // To Fahrenheit

            if (to == 2){
                result = (float) ((temperature - 32) * 5 / 9 + 273);
            } //To Kelvin

            if(to == 3){
                result = (float) ((temperature  - 32) * 5/9);
            } // To Celsius
        }

        //Converting from Kelvin
        if (from == 3){

            if (to == 1){
                result = (float) ((temperature - 273) * 1.8 + 32);
            } // To Fahrenheit

            if (to == 2) result = temperature; // To Kelvin

            if (to == 3){
                result = (float) (temperature - 273);
            } // To Celsius

        }

        return result;
    }
}

/*
***Ideas***
1.Experiment with layouts, this program is a good opportunity to understand them.
2.Maybe add some small exercise features such as counters, new types of elements (gotta ask chatGPT for inspiration).
3.Create the option to choose the resulting temperature unit. DONE+
4.Add the option to switch left and right between temperatures. DONE
5.Add arrow symbols to the buttons from the above (DONE) and then add keyboard input substitutes. WORK IN PROGRESS
*/