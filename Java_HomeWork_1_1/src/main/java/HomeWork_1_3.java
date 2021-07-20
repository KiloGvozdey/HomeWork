public class HomeWork_1_3 {
    public static void main(String[] args) {
        doTask8();
    }
    static int[] getArray1and0(int size){
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random() * 2);
        }
        return array;
    }
    static void changeArray1and0(int[] array){
        for (int i = 0; i < array.length; i++) {
            if(array[i] == 0) array[i] = 1;
            else array[i] = 0;
        }
    }
    static void print1DimArray(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    static void print2DimArray(int[][] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
    static int[][] getSquareArray(int size){
        int[][] array = new int[size][size];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(i == j) array[i][j] = 1;
                else if(i + j == size - 1) array[i][j] = 1;
            }
        }
        return array;
    }
    static int[] getArrayForTask5(int len, int initialValue){
        int[] array = new int[len];
        for (int i = 0; i < array.length; i++) {
            array[i] = initialValue;
        }
        return array;
    }
    static int getMaxOf1DimArray(int[] array){
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if(array[i] > max) max = array[i];
        }
        return max;
    }
    static int getMinOf1DimArray(int[] array){
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if(array[i] < min) min = array[i];
        }
        return min;
    }
    static int[] getRandom1DimArray(int size,int possibleMin ,int range){
        int[] array = new int[size];
            for (int i = 0; i < array.length; i++) {
                array[i] = (int) (Math.random() * (range + 1) + possibleMin);
            }
        return array;
    }
    static boolean checkBalance(int[] array){
        int sumLeft, sumRight;
        int border = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            sumLeft = 0;
            sumRight = 0;
            for (int j = 0; j < border; j++){
                sumLeft += array[j];
            }
            for (int k = border; k < array.length; k++){
                sumRight += array[k];
            }
            if (sumLeft == sumRight) return true;
            border --;
        }
        return false;
    }
    static void doShiftingArray(int[] array, int shift){
        int bufferValue;
        if(shift < 0) {
            for (int i = 0; i > shift; i--) {
                for (int j = 0; j < array.length - 1; j++) {
                    bufferValue = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = bufferValue;
                }
            }
        }
        if(shift > 0) {
            for (int i = 0; i < shift; i++) {
                for (int j = array.length - 1; j > 0; j--) {
                    bufferValue = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = bufferValue;
                }
            }
        }
    }


    static void doTask1(){
        int[] array = getArray1and0(10);
        print1DimArray(array);
        changeArray1and0(array);
        print1DimArray(array);
    }
    static void doTask2(){
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        print1DimArray(array);
    }
    static void doTask3(){
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < array.length; i++) {
            if(array[i] < 6) array[i] *= 2;
        }
        print1DimArray(array);
    }
    static void doTask4(){
        print2DimArray(getSquareArray(5));
    }
    static void doTask5(){
        print1DimArray(getArrayForTask5(5,5));
    }
    static void doTask6(){
       int[] array = getRandom1DimArray(10, -100, 200);
       print1DimArray(array);
        System.out.println("Min value is: " + getMinOf1DimArray(array));
        System.out.println("Max value is: " + getMaxOf1DimArray(array));
    }
    static void doTask7(){
        int[] array = {11, 1, 1, 1, 15};
        System.out.println(checkBalance(array));
    }
    static void doTask8(){
        int[] array = {1, 2, 3, 4, 5};
        print1DimArray(array);
        doShiftingArray(array, 2);
        print1DimArray(array);


    }





}
