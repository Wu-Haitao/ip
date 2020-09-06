package duke.task;

public class Task {
    protected String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        char status = (this.isDone)? 'V':'X';
        return String.format("[%c] %s", status, this.description);
    }
}
