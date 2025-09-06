package homeworks.homework13.exception;

public class WrongConditionException extends IllegalArgumentException {
    public WrongConditionException() {
    }

    public WrongConditionException(String message) {
        super(message);
    }

    public WrongConditionException(String message, Throwable cause) {
        super(message, cause);
    }
}
