package Task_3_3.Server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ChatServer {
    private ServerSocket serverSocket;
    private volatile Set<ClientHandler> loggedClients;
    private Scanner scanner;
    private static Logger log = LogManager.getLogger(ChatServer.class);

    private ExecutorService executorService;

    public ExecutorService getExecutorService() {
        return executorService;
    }



    public ChatServer() {
        try {
            executorService = Executors.newCachedThreadPool();
            loggedClients = new HashSet<>();
            scanner = new Scanner(System.in);
            this.serverSocket = new ServerSocket(8080);
            tryConnectionUsers();

        } catch (IOException e){
            log.error("SWW during a starting server");
        }

    }

    private void tryConnectionUsers() throws IOException{
        while (true){
            log.info("Waiting connection from users");
                Socket client = serverSocket.accept();
                log.info("Connection successfully, starting a new thread");
                executorService.submit(() -> new ClientHandler(client, this));

        }
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
    public void broadcastMessage(String message, String name) {
        loggedClients.forEach(clientHandler -> clientHandler.sendMessage("[" + name + "]: " + message));
    }





    public void sendTargetMessage(String fromUser, String toUser, String message){
       if(loggedClients.stream().anyMatch(clientHandler -> clientHandler.getName().equals(toUser))){
           loggedClients.stream().filter(clientHandler -> clientHandler.getName().equals(toUser)).findFirst().get().sendMessage(message);
       }
       else loggedClients.stream().filter(clientHandler -> clientHandler.getName().equals(fromUser)).findFirst().get().sendMessage(toUser + " not found.");
}


}
