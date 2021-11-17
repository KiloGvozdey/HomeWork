package Task_3_2.SQL_Lib;

public class Main {
    public static void main(String[] args) {


        System.out.println(ClientsService.SINGLETON_SERVICE.authentication("l4", "p1"));

//        clientsService.addClient(new Client("User3", "l3", "p3"));
        ClientsService.SINGLETON_SERVICE.findAll().forEach(System.out::println);
    }
}
