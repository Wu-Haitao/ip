package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(TaskList taskList, Storage storage, Ui ui) throws IOException {
        ui.printTasksFilteredByKeyword(taskList.tasks, keyword);
        return null;
    }
}
