package HomeWork_1_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFirstFrame {

    private final JFrame windowForCalc;
    private JTextField textField;

    public MyFirstFrame(String title){
        this.windowForCalc = new JFrame(title);
        initWindow();
        windowForCalc.setLayout(new BorderLayout());
        windowForCalc.add(createTop(), BorderLayout.NORTH);
        windowForCalc.add(createDigits(), BorderLayout.CENTER);
        windowForCalc.add(createArithmeticOperations(), BorderLayout.WEST);




        windowForCalc.setVisible(true);
    }

    public MyFirstFrame(){
        this("My first desktop application");
    }

    private JPanel createTop() {
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(1, 1));

        textField = new JTextField();
        textField.setEditable(false);
        textPanel.add(textField);

        return textPanel;
    }

    private JPanel createDigits(){
        JPanel digitsPanel = new JPanel();
        digitsPanel.setLayout(new GridLayout(5, 3));
        DigitsButtonActionListener buttonActionListener = new DigitsButtonActionListener(textField);
        EqualActionListener equalActionListener = new EqualActionListener(textField);

        for (int i = 0; i < 10; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.addActionListener(buttonActionListener);
            digitsPanel.add(btn);
        }
        JButton equalButton = new JButton("=");
        digitsPanel.add(equalButton);
        equalButton.addActionListener(equalActionListener);

        JButton clearButton = new JButton("C");
        digitsPanel.add(clearButton);
        clearButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
            }
        });

        JButton btnEqual = new JButton("=");
        btnEqual.addActionListener(equalActionListener);
        btnEqual.add(digitsPanel);
        return digitsPanel;
    }
    private JPanel createArithmeticOperations() {
        JPanel arithmeticPanel = new JPanel();
        arithmeticPanel.setLayout(new GridLayout(5, 1));
        DigitsButtonActionListener arithmeticOperationsActionListener = new DigitsButtonActionListener(textField);
        JButton btnPlus = new JButton("+");
        JButton btnMinus = new JButton("-");
        JButton btnSplitting = new JButton("/");
        JButton btnMultiplication = new JButton("*");
        JButton btnSqrt = new JButton("sqrt");
        btnPlus.addActionListener(arithmeticOperationsActionListener);
        btnMinus.addActionListener(arithmeticOperationsActionListener);
        btnSplitting.addActionListener(arithmeticOperationsActionListener);
        btnMultiplication.addActionListener(arithmeticOperationsActionListener);
        btnSqrt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(String.valueOf(Math.sqrt(Double.parseDouble(textField.getText()))));
            }
        });
        arithmeticPanel.add(btnPlus);
        arithmeticPanel.add(btnMinus);
        arithmeticPanel.add(btnSplitting);
        arithmeticPanel.add(btnMultiplication);
        arithmeticPanel.add(btnSqrt);


        return arithmeticPanel;
    }


    private void initWindow() {
        windowForCalc.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Rectangle windowSize = new Rectangle(100, 100, 300, 500);
        windowForCalc.setBounds(windowSize);
    }


}
