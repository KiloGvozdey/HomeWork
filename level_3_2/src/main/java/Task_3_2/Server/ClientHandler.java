package Task_3_2.Server;

import Task_3_2.SQL_Lib.Client;
import Task_3_2.SQL_Lib.ClientsService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientHandler {
    private final Socket clientSocket;
    private final ClientsService clientsService = ClientsService.SINGLETON_SERVICE;
    private final ChatServer server;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
    private boolean isConnected = false;
    private Client client;

    public String getName() {
        return name;
    }


    public ClientHandler(Socket clientSocket, ChatServer server) {
        this.clientSocket = clientSocket;
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
                this.client = clientsService.authentication(login, password);
                if (client != null) {
                    if (!server.isUserNameOccupied(client.getUserName())) {
                        server.broadcastMessage(String.format("User[%s]: logged in.", client.getUserName()));
                        name = client.getUserName();
                        server.addClients(this);
                        isSuccess.set(true);
                    } else {
                        sendMessage("Current username is already occupied.");
                    }
                } else {
                    sendMessage("BadCredentials");
                }
            }
            if(isSuccess.get()) {
                isConnected = true;
                break;
            }
        }
    }


    public void openDataStreams(){
        try {
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
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
            } else if (message.startsWith("/u")){
                this.name = clientsService.updateName(client, message);
                sendMessage("Имя пользователя успешно изменено на " + "[" + this.name + "]");
            } else server.broadcastMessage(message, this.name);
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
            clientSocket.close();
            server.removeClients(this);
            System.out.println("Connection close complete");
        } catch (IOException e){
            System.out.println("Close connection failed");
        }
    }

    private void setSocketTimeout(int millis){
        try {
            clientSocket.setSoTimeout(millis);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }


}
