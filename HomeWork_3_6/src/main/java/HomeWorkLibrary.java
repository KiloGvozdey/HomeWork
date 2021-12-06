import java.util.ArrayList;

public final class HomeWorkLibrary {
    public static void main(String[] args) {
        for (int a: returnArrayAfterLastFour(new int[]{1,4,1,6,7,1,1})) {
            System.out.print(a + " ");
        }
        System.out.println(checkContainsOneAndFour(new int[]{1,2,1,6,7,1,1}));
    }

    static boolean checkContainsOneAndFour(int [] array){
        boolean isContainOne = false;
        boolean isContainFour = false;
        for (int i : array) {
            if(i == 1) isContainOne = true;
            if(i == 4) isContainFour = true;
        }
        return isContainOne && isContainFour;
    }

    static int[] returnArrayAfterLastFour(int [] array) {
        int cursor = -1;
        int[] newArray = new int[0];
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == 4) {
                cursor = i + 1;
                newArray = new int[array.length - cursor];
                for (int j = 0; j < newArray.length; j++) {
                    newArray[j] = array[cursor];
                    cursor++;
                }
            }
        }
        if(cursor == -1){
            throw new RuntimeException("Отсутствует 4 в массиве");
        }
        return newArray;
    }
}
