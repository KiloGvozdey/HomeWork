package Task_3_3.SQL_Lib;

public class Main {
    public static void main(String[] args) {




//        clientsService.addClient(new Client("User3", "l3", "p3"));
        ClientsService.SINGLETON_SERVICE.findAll().forEach(System.out::println);
    }
}
