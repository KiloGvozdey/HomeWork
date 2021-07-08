import java.sql.SQLOutput;

public class HomeWorkApp {
    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
    }
    static void printThreeWords(){
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }
    static void checkSumSign(){
        int a = 8;
        int b = 15;
        if(a + b >= 0) System.out.println("Сумма положительная");
        else System.out.println("Сумма отрицательная");
    }
    static void printColor(){
        int value = 55;
        if(value <= 0) System.out.println("Красный");
        if(value <= 100) System.out.println("Жёлтый");
        else System.out.println("Зелёный");
    }
    static void compareNumbers(){
        int a = 8, b = 14;
        if(a >= b) System.out.println("a >= b");
        else System.out.println("a < b");
    }
}
