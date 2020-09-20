package duke;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidFileException;
import duke.command.Command;

import java.io.IOException;

class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    public void run() {
        initialize();
        runCommandLoopUntilExit();
        exit();
    }

    public void initialize() {
        try {
            taskList = storage.loadFile();
        } catch (InvalidFileException exception) {
            ui.showInvalidFileMessage();
            taskList = new TaskList();
        }
        ui.greet();
    }

    public void runCommandLoopUntilExit() {
        String userInput;
        userInput = ui.getUserInput();
        while (!userInput.equals("bye")) {
            try {
                Command command = new Parser().parseCommand(userInput);
                ui.showCommandResult(command.execute(taskList, storage, ui));
            } catch (InvalidCommandException e) {
                ui.showInvalidCommandMessage(e);
            } catch (IOException e) {
                ui.showInvalidFileMessage();
            } catch (IndexOutOfBoundsException e) {
                ui.showInvalidIndexMessage();
            }
            ui.drawHorizontalLine();
            userInput = ui.getUserInput();
        }
    }

    public void exit() {
        ui.showExitMessage();
    }

    public static void main(String[] args) {
        new Duke("data/save.txt").run();
    }
}
