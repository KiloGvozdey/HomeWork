package Task_3_3.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

public class WriteArea {
    private final JPanel writeArea;
    private final JTextField writeField;
    private final JButton sendBtn;
    private final Consumer<String> onSubmit;




    public WriteArea(Consumer<String> onSubmit) {
        this.onSubmit = onSubmit;
        writeArea = new JPanel();
        writeArea.setLayout(new BorderLayout());

        writeField = new JTextField();
        sendBtn = new JButton("Send");

        sendBtn.addActionListener(e -> {
            onSubmit.accept(writeField.getText() + getCurrentTime());
            writeField.setText("");
        });

        writeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendBtn.doClick();
                }
            }
        });
        writeField.setBorder(new LineBorder(Color.BLACK));

        writeArea.add(writeField, BorderLayout.CENTER);
        writeArea.add(sendBtn, BorderLayout.EAST);


    }

    public JPanel getWriteArea() {
        return writeArea;
    }
    private String getCurrentTime(){
        return  "\t" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }



}
