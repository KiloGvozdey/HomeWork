package Task_3_2.gui;


import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ChatFrame{
    private final JFrame jFrame;
    private final ChatArea chatArea;
    private final WriteArea writeArea;


    public ChatFrame(Consumer<String> onSubmit) {
        chatArea = new ChatArea();
        writeArea = new WriteArea(onSubmit);
        jFrame = new JFrame();
        jFrame.setTitle("Chat v.1.0");
        jFrame.add(chatArea.getChatArea(), BorderLayout.CENTER);
        jFrame.add(writeArea.getWriteArea(), BorderLayout.SOUTH);

        jFrame.setBounds(new Rectangle(50, 50, 500, 700));

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jFrame.setVisible(true);
    }

    public Consumer<String> onReceive() {
        return chatArea.getOnReceive();
    }

}