public class InvalidCommandException extends Exception {
    protected int exceptionCode;
    public InvalidCommandException(int exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
