package Task_2_8.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientHandler {
    private final Socket client;
    private final ChatServer server;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
    private boolean isConnected = false;

    public String getName() {
        return name;
    }


    public ClientHandler(Socket client, ChatServer server) {
        this.client = client;
        this.server = server;
        openDataStreams();
        doAuthentication();
        if(isConnected) {
            setSocketTimeout(0);
            sendMessage("Приветствуем тебя [" + this.name + "]");
            listenMessage();
        } else closeConnection();

    }

    private void doAuthentication(){
        try{
            doPerformAuthentication();
        } catch (IOException e){
            System.out.println("SWW");
        }
    }

    private void doPerformAuthentication() throws IOException {
        setSocketTimeout(120000);
        while (true) {
            String inboundMessage = in.readUTF();
            AtomicBoolean isSuccess = new AtomicBoolean(false);
            if (inboundMessage.startsWith("-auth")) {
                String[] credentials = inboundMessage.split("\\s");
                String login = credentials[1];
                String password = credentials[2];
                server.getAuthService().findUsernameByLoginAndPassword(login, password).ifPresentOrElse(username ->{
                    if(!server.isUserNameOccupied(username)){
                        server.broadcastMessage(String.format("User[%s]: logged in.", username));
                        name = username;
                        server.addClients(this);
                        isSuccess.set(true);
                    } else {
                        sendMessage("Current username is already occupied.");
                    }
                },
                        () ->{
                            sendMessage("BadCredentials");
                });
            }
            if(isSuccess.get()) {
                isConnected = true;
                break;
            }
        }
    }


    public void openDataStreams(){
        try {
            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
            System.out.println("Подключение потоков успешно выполнено");
        } catch (IOException e ){
            System.out.println("Ошибка в ходе подключения потоков");
        }
    }

    public void sendMessage(String outboundMessage){
        try {
            out.writeUTF(outboundMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMessage(){
        try {
            String message = in.readUTF();
            if (message.startsWith("/w")) {
                sendTargetMessage(message);
            } else if (message.equalsIgnoreCase("/end")) {
                closeConnection();
            } else server.broadcastMessage(message);
        } catch (IOException e){
            closeConnection();
        }
        }


    public void listenMessage() {
        while (isConnected){
                readMessage();
        }
    }

    private void sendTargetMessage(String message){
        String[] strings = message.split("\\s");
        StringBuilder targetMessage = new StringBuilder("Сообщение от пользователя [" + this.name + "] ");
        for (int i = 2; i < strings.length; i++) {
            targetMessage.append(strings[i]).append(" ");
        }
        server.sendTargetMessage(this.name, strings[1], targetMessage.toString());
    }

    private void closeConnection(){
        try {
            isConnected = false;
            in.close();
            out.close();
            client.close();
            server.removeClients(this);
            System.out.println("Connection close complete");
        } catch (IOException e){
            System.out.println("Close connection failed");
        }
    }

    private void setSocketTimeout(int millis){
        try {
            client.setSoTimeout(millis);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }


}
