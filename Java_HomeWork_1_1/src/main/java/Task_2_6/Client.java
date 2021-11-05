package Task_2_6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Запуск клиента разворачивает фрейм в конструкторе ниже
 * 1. Отрисовывается фрейм клиента
 * 2. Отрабатывается нажатие клавиши "Send" на панельке и клавиши Enter на клавиатуре
 * 3. Выполняется подключение клиента к серверу
 * 4. Вывод сообщений полученных от сервера
 */

public class Client {

    private final JFrame mainFrame;
    public JTextPane getReadField() {
        return readField;
    }
    private JTextPane readField = new JTextPane();
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;


    public Client() {
        //1 отрисовка фрейма клиента
        mainFrame = new JFrame();
        mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));
        mainFrame.setTitle("Онлайн-чат начало=)");
        mainFrame.setBounds(new Rectangle(30, 30, 450, 700));
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());
        center.setPreferredSize(new Dimension(450, 650));
        readField.setEditable(false);
        center.add(readField, BorderLayout.CENTER);
        readField.setBackground(new Color(220, 195, 147));
        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
        setSizeForField(bottom, 450, 50);
        JTextField writeField = new JTextField();
        JButton sendButton = new JButton("Send");
        setSizeForField(sendButton, 70, 50);
        bottom.add(writeField);
        bottom.add(sendButton);
        mainFrame.add(center);
        mainFrame.add(bottom);
        mainFrame.setVisible(true);


        //2 отработка нажатий
        sendButton.addActionListener(e -> {
            if (!writeField.getText().isEmpty()) {
                try {
                    String messageToServer = writeField.getText();
                    out.writeUTF(messageToServer);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                writeField.setText("");
            }
        });
        writeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(!writeField.getText().isEmpty()){
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        try {
                            String messageToServer = writeField.getText();
                            out.writeUTF(messageToServer);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });


        //3 подключение к серверу
        try {
            this.socket = new Socket("localhost", 8080);
            start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        void start() throws IOException {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(this::listenInboundMessages).start();

        }

        //4 отработка сообщений от сервера
        private void listenInboundMessages(){
            try {
                while (true) {
                    String inboundMessage = in.readUTF();
                    StringBuilder stringBuilder = new StringBuilder(getReadField().getText());
                    LocalTime localTime = LocalTime.now();
                    String currentTime = localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                    stringBuilder.append(inboundMessage).append("\t").append(currentTime).append("\n");
                    getReadField().setText(stringBuilder.toString());
                }
            } catch (IOException e) {
                System.out.println("SWW" + e);
            }
        }


    static void setSizeForField(JComponent component, int width, int height){
        component.setPreferredSize(new Dimension(width, height));
        component.setMinimumSize(new Dimension(width, height));
        component.setMaximumSize(new Dimension(width, height));
    }



}
