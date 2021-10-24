package Task_2_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Messenger {
    private final JFrame mainFrame;
    public Messenger() {
        mainFrame = new JFrame();
        mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));
        mainFrame.setTitle("Онлайн-чат начало=)");
        mainFrame.setBounds(new Rectangle(30, 30 , 450, 700));
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());
        center.setPreferredSize(new Dimension(450, 650));
        JTextPane readField = new JTextPane();
        readField.setEditable(false);
        center.add(readField, BorderLayout.CENTER);
        readField.setBackground(new Color(255, 239, 213));



        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
        setSizeForField(bottom, 450, 50);
        JTextField writeField = new JTextField();
        JButton sendButton = new JButton("Send");
        setSizeForField(sendButton, 70, 50);
        sendButton.addActionListener(e -> {
            if(!writeField.getText().isEmpty()){
               setText(readField, writeField);
            }
        });
        writeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(!writeField.getText().isEmpty()){
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) setText(readField, writeField);
                }
            }
        });


        bottom.add(writeField);
        bottom.add(sendButton);

        mainFrame.add(center);
        mainFrame.add(bottom);

        mainFrame.setVisible(true);
    }
    static void setSizeForField(JComponent component, int width, int height){
        component.setPreferredSize(new Dimension(width, height));
        component.setMinimumSize(new Dimension(width, height));
        component.setMaximumSize(new Dimension(width, height));
    }
    static void setText(JTextPane readField, JTextField writeField){
        StringBuilder builder = new StringBuilder(readField.getText());
        builder.append(writeField.getText());
        builder.append("\n");
        readField.setText(builder.toString());
        writeField.setText("");

    }
}
