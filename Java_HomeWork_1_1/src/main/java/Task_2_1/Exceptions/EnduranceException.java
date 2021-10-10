package Task_2_1.Exceptions;

/**
 * При отлове этого исключения вызывается родительский конструктор с параметром String message
 */
public class EnduranceException extends Exception{
    public EnduranceException(String message) {
        super(message);
    }
}
