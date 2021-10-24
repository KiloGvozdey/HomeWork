package Task_2_3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task1 {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        addWordToArray(strings);
        Set<String> uniqueWords = new HashSet<>();
        addWordToSet(strings, uniqueWords);
        uniqueWords.forEach(System.out::println);
        showCountWord(strings, uniqueWords);
    }

    public static void addWordToArray(List<String> strings){
        strings.add("Table");
        strings.add("Speaker");
        strings.add("Mouse");
        strings.add("Display");
        strings.add("Chair");
        strings.add("Cup");
        strings.add("Keyboard");
        strings.add("Phone");
        strings.add("Book");
        strings.add("Mouse");
        strings.add("Book");
        strings.add("Table");
        strings.add("Book");
        strings.add("Gamepad");
        strings.add("Headphones");
        strings.add("Cup");
        strings.add("Chair");
        strings.add("Book");
        strings.add("Mouse");
        strings.add("Display");
    }
    public static void addWordToSet(List<String> strings, Set<String> uniqueWords){
        uniqueWords.addAll(strings);
    }
    public static void showCountWord(List<String> strings, Set<String> uniqueWords){
        for (String unique : uniqueWords) {
            int count = 0;
            for(String s : strings){
                if(s.equals(unique)) count++;
            }
            System.out.printf("Слово %s встречается %s раз %n", unique, count);
        }
    }

}
