package Task_2_6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;
    private  DataInputStream in;
    private DataOutputStream out;
    private Scanner scanner;



    public Server() {
        try {
            System.out.println("Try connection");
            this.serverSocket = new ServerSocket(8080);

            System.out.println("Connect complete.");
            this.scanner = new Scanner(System.in);
            start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void start() throws IOException {
        System.out.println("Please start the client");
        Socket accepted = serverSocket.accept();
        System.out.println("Client connect successfully");
        in = new DataInputStream(accepted.getInputStream());
        out = new DataOutputStream(accepted.getOutputStream());
        System.out.println("Stream creating successfully");

        new Thread(this::listenInboundMessages).start();

        while (true) {
            try {
                System.out.println("Please enter a message...");
                String outboundMessage = scanner.nextLine();
                out.writeUTF(outboundMessage);
            } catch (IOException ex) {
                System.out.println("Connection closed.");
                break;
            }
        }

    }

    public void listenInboundMessages(){
        while (true) {
            try {
                String s = in.readUTF();
                System.out.println("Новое сообщение!");
                System.out.print(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }


}
