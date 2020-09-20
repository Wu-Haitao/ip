package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

public class AddCommand extends Command{
    private Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public CommandResult execute(TaskList taskList, Storage storage, Ui ui) throws IOException {
        taskList.add(taskToAdd);
        storage.saveFile(taskList);
        return new CommandResult(String.format("Got it. I've added this task to your list:\n%s\n%s", taskToAdd, ui.checkNumberOfTask(taskList)));
    }

}
