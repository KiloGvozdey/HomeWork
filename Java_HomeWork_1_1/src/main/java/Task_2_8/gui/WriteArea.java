package Task_2_8.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.function.Consumer;

public class WriteArea {
    private final JPanel writeArea;
    private final JTextArea writeField;
    private final JButton sendBtn;
    private final Consumer<String> onSubmit;




    public WriteArea(Consumer<String> onSubmit) {
        this.onSubmit = onSubmit;
        writeArea = new JPanel();
        writeArea.setLayout(new BorderLayout());

        writeField = new JTextArea();
        sendBtn = new JButton("Send");
        sendBtn.addActionListener(e -> {
            onSubmit.accept(writeField.getText());
            writeField.setText("");
        });

        writeField.setBorder(new LineBorder(Color.BLACK));

        writeArea.add(writeField, BorderLayout.CENTER);
        writeArea.add(sendBtn, BorderLayout.EAST);


    }

    public JPanel getWriteArea() {
        return writeArea;
    }


}
