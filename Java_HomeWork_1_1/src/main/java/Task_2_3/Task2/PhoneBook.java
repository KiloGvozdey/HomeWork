package Task_2_3.Task2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    private final Map<String, ArrayList<Integer>>phoneNumberList = new HashMap<>();


    public void add(String surname, int phoneNumber){
        for (String s : phoneNumberList.keySet()) {
            if(s.equals(surname)){
                ArrayList<Integer> list= phoneNumberList.get(surname);
                list.add(phoneNumber);
                phoneNumberList.put(s, list);
                return;
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(phoneNumber);
        phoneNumberList.put(surname, list);
    }
    public ArrayList<Integer> get(String surname){

        return phoneNumberList.getOrDefault(surname, null);

    }
}
