package duke.exceptions;

public class InvalidCommandException extends Exception {
    public int exceptionCode;
    public InvalidCommandException(int exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
