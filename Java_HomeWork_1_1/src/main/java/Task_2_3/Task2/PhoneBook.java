package Task_2_3.Task2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    private final Map<String, ArrayList<Integer>>phoneNumberList = new HashMap<>();


    public void add(String surname, int phoneNumber){
        for (String s : this.phoneNumberList.keySet()) {
            if(s.equals(surname)){
                ArrayList<Integer> list= this.phoneNumberList.get(surname);
                list.add(phoneNumber);
                this.phoneNumberList.put(s, list);
                return;
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(phoneNumber);
        this.phoneNumberList.put(surname, list);
    }
    public void get(String surname){
        System.out.printf("Человек под фамилией %s имеет следующие номера телефонов: %n", surname);
        this.phoneNumberList.get(surname).forEach(System.out::println);

    }
}
