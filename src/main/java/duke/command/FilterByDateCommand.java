package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

public class FilterByDateCommand extends Command {
    LocalDate expectedDate;
    public FilterByDateCommand(LocalDate expectedDate) {
        this.expectedDate = expectedDate;
    }

    @Override
    public CommandResult execute(TaskList taskList, Storage storage, Ui ui)  {
        ui.printTasksFilterByDate(taskList.tasks, expectedDate);
        return null;
    }
}
