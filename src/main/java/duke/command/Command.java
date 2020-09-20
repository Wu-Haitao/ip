package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

import java.io.IOException;

public abstract class Command {
    public CommandResult execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        return null;
    }
}
