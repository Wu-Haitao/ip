package duke;

import duke.command.CommandResult;
import duke.exception.InvalidCommandException;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private Scanner in;
    private PrintStream out;
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "Modified by Wu Haitao";
    private static final String JOKE = "There are only 10 kinds of people in this world:\n" +
            "those who know binary and those who don't.";
    /** Number of '-' consisted in the horizontal line */
    private static final int LINE_CHAR_NUM = 40;

    public Ui() {
        this(new Scanner(System.in), System.out);
    }

    public Ui(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    /** Prints welcome messages at the beginning of the program. */
    public void greet() {
        drawHorizontalLine();
        out.println("Hello from");
        out.println(logo);
        drawHorizontalLine();
        out.println("Hello! I'm Duke.\nWhat can I do for you?");
        drawHorizontalLine();
    }

    /** Draws a separate line. */
    public void drawHorizontalLine() {
        out.println("-".repeat(LINE_CHAR_NUM));
    }

    /** Prints an error message if the program failed to load data from the file. */
    public void showInvalidFileMessage() {
        out.println("Accessing local data failed");
    }

    /** Prints an error message if the user gives a invalid command. */
    public void showInvalidCommandMessage(InvalidCommandException exception) {
        out.println(exception.getExceptionMessage());
    }

    /** Prints an error message when user tries to modify a nonexistent task. */
    public void showInvalidIndexMessage() {
        out.println("Invalid index!");
    }

    /** Says goodbye when user exits the program */
    public void showExitMessage() {
        out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Reads the text entered by the user.
     *
     * @return command (full line) entered by the user
     */
    public String getUserInput() {
        return in.nextLine();
    }

    /**
     * Prints the messages accordingly when a command is successfully executed.
     * Ignores empty result.
     *
     * @param result result of the command.
     */
    public void showCommandResult(CommandResult result) {
        if (result != null) out.println(result.getResult());
    }

    /**
     * Prints the whole list of tasks with each has an index.
     * Prompts if the list is empty.
     *
     * @param taskList task list to be printed.
     */
    public void showTaskList(TaskList taskList) {
        if (taskList.getTaskListSize() > 0) {
            out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.getTaskListSize(); i++) {
                out.println(String.format("%d.%s", i + 1, taskList.getTaskByIndex(i)));
            }
        } else {
            out.println("You don't have any tasks in your list now.");
        }
    }

    /**
     * Counts the number of tasks in the list.
     * returns messages with number of tasks.
     *
     * @param taskList task list to be checked.
     * @return prompt messages with number of tasks in the list.
     */
    public String checkNumberOfTask(TaskList taskList) {
        switch (taskList.getTaskListSize()) {
        case 0:
            return "You don't have any task in the list now.";
        case 1:
            return "You have 1 task in the list now.";
        default:
            return String.format("You have %d tasks in the list now.", taskList.getTaskListSize());
        }
    }
}
