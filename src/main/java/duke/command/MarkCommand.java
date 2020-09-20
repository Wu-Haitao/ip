package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    private int indexForMarking;
    public MarkCommand(int indexForMarking) {
        this.indexForMarking = indexForMarking;
    }

    @Override
    public CommandResult execute(TaskList taskList, Storage storage, Ui ui) throws IOException, IndexOutOfBoundsException {
        taskList.getTaskByIndex(indexForMarking).isDone = true;
        storage.saveFile(taskList);
        return new CommandResult(String.format("OK! I've marked this task as done:\n%s", taskList.getTaskByIndex(indexForMarking)));
    }
}
