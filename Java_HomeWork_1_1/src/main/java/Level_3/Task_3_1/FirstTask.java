package Level_3.Task_3_1;

public class FirstTask {
    public static void main(String[] args) {
        Integer[] a = {1,2,4,6,8,9,7,5,3,41,6,2,1,58,5,4,6,4, 5};
        printArray(a);
        switchElementFromArray(a);
        printArray(a);

    }
    public static <T> void switchElementFromArray(T[] a){
        int switchCount = a.length - a.length % 2;
        for (int i = 0; i < switchCount; i+=2) {
            T temp = a[i];
            a[i] = a[i + 1];
            a[i + 1] = temp;
        }
    }

    public static <T> void printArray(T [] a){
        for(T i : a){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
