package duke.command;

public class CommandResult {
    private String message;

    public CommandResult(String message) {
        this.message = message;
    }

    public String getResult() {
        return message;
    }
}
