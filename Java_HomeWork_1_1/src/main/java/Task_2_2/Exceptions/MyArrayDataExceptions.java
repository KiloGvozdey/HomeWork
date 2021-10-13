package Task_2_2.Exceptions;

public class MyArrayDataExceptions extends RuntimeException{
    public MyArrayDataExceptions(String message) {
        super(message);
    }

    public MyArrayDataExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
