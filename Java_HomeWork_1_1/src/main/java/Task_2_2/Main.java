package Task_2_2;

import Task_2_2.Exceptions.MyArrayDataExceptions;
import Task_2_2.Exceptions.MyArraySizeException;


public class Main {
    public static void main(String[] args) {
        String[][] myArray = {{"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "g", "4"},
                {"1", "2", "g", "4"}};
        try {
            System.out.println(sumElementsOfArray(myArray));
        } catch (MyArrayDataExceptions dataExceptions){
            System.out.println(dataExceptions.getMessage());
            dataExceptions.printStackTrace();
            dataExceptions.getCause();
        } catch (MyArraySizeException sizeException){
            System.out.println(sizeException.getMessage());
            sizeException.printStackTrace();
        }

    }
    public static void checkArraySize(String[][] array) throws MyArrayDataExceptions{
        for(String[] row : array){
            if(row.length != 4 || array.length != 4){
                    throw new MyArraySizeException("Неверный размер массива!");
            }
        }
    }

    public static int parseInt(String s){
        return Integer.parseInt(s);
    }

    public static int sumElementsOfArray(String[][] strings)throws MyArrayDataExceptions, MyArraySizeException{
        checkArraySize(strings);
        int sum = 0;
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {
                try {
                    sum += parseInt(strings[i][j]);
                } catch (NumberFormatException e){
                    throw new MyArrayDataExceptions("Ошибка данных в строке " + (++i)+ " колонке " + (++j), e);
                }
            }
        }
        return sum;
    }
}
