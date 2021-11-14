package Level_3.Task_3_1;

import java.util.ArrayList;

public class SecondTask {
    public static void main(String[] args) {
        Integer[] a = {1,2,4,6,8,9,7,5,3,41,6,2,1,58,5,4,6,4, 5};
        printArray(a);
        ArrayList<Object> arrayList = convertToArrayList(a);
        System.out.println(arrayList.getClass().getSimpleName());
        printArray(a);


    }

    public static <T> ArrayList<T> convertToArrayList(T[] array){
        ArrayList<T> arrayList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            arrayList.add(array[i]);
        }
        return arrayList;
    }

    public static <T> void printArray(T [] a){
        for(T i : a){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
