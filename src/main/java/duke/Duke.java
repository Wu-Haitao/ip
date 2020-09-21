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

    /** Runs the program until termination. */
    public void run() {
        initialize();
        runCommandLoopUntilExit();
        exit();
    }

    /**
     * Initializes the program.
     * Prints welcome messages.
     */
    public void initialize() {
        try {
            taskList = storage.loadFile();
        } catch (InvalidFileException e) {
            ui.showInvalidFileMessage();
            taskList = new TaskList();
        }
        ui.greet();
    }

    /**
     * Receives user's input.
     * Executes commands.
     * Terminates the program when receives exit command.
     */
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

    /** Prints exit messages */
    public void exit() {
        ui.showExitMessage();
    }

    public static void main(String[] args) {
        new Duke("data/save.txt").run();
    }
}
