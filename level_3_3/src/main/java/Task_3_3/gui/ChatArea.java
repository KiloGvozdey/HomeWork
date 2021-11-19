package Task_3_3.gui;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ChatArea {
    private final JPanel chatArea;
    private final JTextArea chatPane;
    private final Consumer<String> onReceive;

    public JPanel getChatArea() {
        return chatArea;
    }

    public ChatArea() {
        chatArea = new JPanel();
        chatArea.setLayout(new BorderLayout());
        chatPane = new JTextArea();
        chatArea.add(chatPane, BorderLayout.CENTER);
        chatPane.setEditable(false);

        onReceive = message -> {
            chatPane.append(message);
            chatPane.append("\n");
        };
    }

    public Consumer<String> getOnReceive() {
        return onReceive;
    }
}
