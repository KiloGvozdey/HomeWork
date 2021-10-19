package Task_2_3.Task2;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Ilya", 145);
        phoneBook.add("Ilya", 564);
        phoneBook.add("Ilya", 888);
        phoneBook.add("Ilya", 123);
        phoneBook.add("Ilya", 333);
        phoneBook.add("Vladimir", 221);
        phoneBook.add("Vladimir", 332);
        phoneBook.add("Sam", 223);
        phoneBook.add("Sam", 556);
        phoneBook.get("Ilya");
        phoneBook.get("Sam");
        phoneBook.get("Vladimir");
    }
}
