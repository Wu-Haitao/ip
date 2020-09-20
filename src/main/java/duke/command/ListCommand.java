package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public CommandResult execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showTaskList(tasks);
        return null;
    }
}
