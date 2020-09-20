package duke.exception;

public class InvalidCommandException extends Exception {
    public int exceptionCode;
    public final String[] EXCEPTION_MESSAGE = {"No such command!",
            "Incorrect command line argument(s).\ntodo -taskName",
            "Incorrect command line argument(s).\ndeadline -taskName /by -taskTime",
            "Incorrect command line argument(s).\nevent -taskName /at -taskTime",
            "Incorrect command line argument(s).\ndone -taskIndex",
            "Incorrect command line argument(s).\ndelete -taskIndex"};
    public InvalidCommandException(int exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
    public String getExceptionMessage() {
        return EXCEPTION_MESSAGE[exceptionCode];
    }
}
