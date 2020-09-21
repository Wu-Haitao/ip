package duke.exception;

public class InvalidCommandException extends Exception {
    public int exceptionCode;
    public final String[] EXCEPTION_MESSAGE = {"No such command!",
            "Incorrect command line argument(s).\ntodo TASK_DESCRIPTION",
            "Incorrect command line argument(s).\ndeadline TASK_DESCRIPTION /by TASK_TIME(yyyy-MM-dd HH:mm)",
            "Incorrect command line argument(s).\nevent TASK_DESCRIPTION /at TASK_TIME(yyyy-MM-dd HH:mm)",
            "Incorrect command line argument(s).\ndone TASK_INDEX",
            "Incorrect command line argument(s).\ndelete TASK_INDEX",
            "Incorrect command line argument(s).\ndate EXPECTED_DATE(yyyy-MM-dd)"};
    public InvalidCommandException(int exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
    public String getExceptionMessage() {
        return EXCEPTION_MESSAGE[exceptionCode];
    }
}
