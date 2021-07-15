public class HomeWork_1_2 {
    public static void main(String[] args) {
    }
    static boolean checkSum(int a, int b){
        return (a + b >= 10 && a + b <=20);
    }
    static void isPositive(int value){
        if (value < 0){
            System.out.println("Value is negative");
            return;
        }
        System.out.println("Value is positive");
    }
    static boolean isNegative(int value){
        return value < 0;
    }
    static void printString(String s, int a){
        for (; a > 0; a--){
            System.out.println(s);
        }
    }
    static boolean checkYear(int year){
        if(year % 400 == 0) return true;
        if(year % 100 == 0) return false;
        return (year % 4 == 0);
    }
}
