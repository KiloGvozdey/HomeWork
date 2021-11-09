package Task_2_7;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ChatServer {
    private ServerSocket serverSocket;
    private AuthService authService;
    private Set<ClientHandler> loggedClients;
    private Scanner scanner;


    public ChatServer() {
        try {
            authService = new AuthService();
            loggedClients = new HashSet<>();
            scanner = new Scanner(System.in);
            this.serverSocket = new ServerSocket(8080);
            tryConnectionUsers();

        } catch (IOException e){
            System.out.println("SWW");
        }

    }

    private void tryConnectionUsers() throws IOException{
        while (true){
            System.out.println("Ожидание подключения пользователя");
                Socket client = serverSocket.accept();
                System.out.println("Подключение прошло успешно, запускаю новый поток");
                new Thread(() -> new ClientHandler(client, this)).start();

        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public void addClients(ClientHandler client){
        loggedClients.add(client);
    }

    public void removeClients(ClientHandler client){
        loggedClients.remove(client);
    }

    public boolean isUserNameOccupied(String username){
        return loggedClients.stream().anyMatch(client -> client.getName().equals(username));
    }

    public void broadcastMessage(String message){
        loggedClients.forEach(clientHandler -> clientHandler.sendMessage(message));
    }

    public void sendTargetMessage(String fromUser, String toUser, String message){
       if(loggedClients.stream().anyMatch(clientHandler -> clientHandler.getName().equals(toUser))){
           loggedClients.stream().filter(clientHandler -> clientHandler.getName().equals(toUser)).findFirst().get().sendMessage(message);
       }
       else loggedClients.stream().filter(clientHandler -> clientHandler.getName().equals(fromUser)).findFirst().get().sendMessage("Пользователь с ником " + toUser + " не найден.");
}
}
