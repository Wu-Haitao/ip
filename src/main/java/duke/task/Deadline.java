package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {
    public LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.US)));
    }
}
