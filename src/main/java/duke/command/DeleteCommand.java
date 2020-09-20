package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    int indexForDeletion;
    public DeleteCommand(int indexForDeletion) {
        this.indexForDeletion = indexForDeletion;
    }

    @Override
    public CommandResult execute(TaskList taskList, Storage storage, Ui ui) throws IOException, IndexOutOfBoundsException {
        taskList.delete(indexForDeletion);
        storage.saveFile(taskList);
        return new CommandResult(String.format("OK! I've deleted the task.\n%s", ui.checkNumberOfTask(taskList)));
    }

}
