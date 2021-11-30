package Task_3_3.Server;

import Task_3_3.File_IO.ChatFileReader;
import Task_3_3.File_IO.ChatFileWriter;
import Task_3_3.SQL_Lib.Client;
import Task_3_3.SQL_Lib.ClientsService;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
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
    private final ChatFileReader chatFileReader;
    private final ChatFileWriter chatFileWriter;

    public String getName() {
        return name;
    }


    public ClientHandler(Socket clientSocket, ChatServer server) throws FileNotFoundException {
        this.clientSocket = clientSocket;
        this.server = server;
        openDataStreams();
        doAuthentication();
        this.chatFileReader = new ChatFileReader(name);
        this.chatFileWriter = new ChatFileWriter(name);
        if(isConnected) {
            showHistory(chatFileReader.read(chatFileReader.getStartPosition()));
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
            chatFileWriter.write(outboundMessage + "\n");
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
                chatFileWriter.getFile().renameTo(new File(String.format("Chat_(%s).txt", name)));
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

    private void showHistory(List<String> list){
        try {

            if(list.size() <= 100) {
                for (String s : list) {
                    out.writeUTF(s);
                }
            }
            else {
                int startPos = list.size() - 100;
                for(;startPos < list.size(); startPos++){
                    out.writeUTF(list.get(startPos));
                }
            }
        } catch (IOException e){
            throw new RuntimeException("SWW during a show history");
        }
    }
}