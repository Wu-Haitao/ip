package duke;

import duke.command.CommandResult;
import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

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
    //Number of '-' consisted in the horizontal line
    private static final int LINE_CHAR_NUM = 40;

    public Ui() {
        this(new Scanner(System.in), System.out);
    }

    public Ui(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public void greet() {
        drawHorizontalLine();
        out.println("Hello from");
        out.println(logo);
        drawHorizontalLine();
        out.println("Hello! I'm Duke.\nWhat can I do for you?");
        drawHorizontalLine();
    }

    public void drawHorizontalLine() {
        out.println("-".repeat(LINE_CHAR_NUM));
    }

    public void showInvalidFileMessage() {
        out.println("Accessing local data failed");
    }

    public void showInvalidCommandMessage(InvalidCommandException exception) {
        out.println(exception.getExceptionMessage());
    }

    public void showInvalidIndexMessage() {
        out.println("Invalid index!");
    }

    public void showExitMessage() {
        out.println("Bye. Hope to see you again soon!");
    }

    public String getUserInput() {
        return in.nextLine();
    }

    public void showCommandResult(CommandResult result) {
        if (result != null) out.println(result.getResult());
    }

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

    public void printTasksFilteredByKeyword(ArrayList<Task> tasks, String keyword) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter((t) -> (t.description.contains(keyword)))
                .collect(Collectors.toList());

        if (filteredList.isEmpty()) {
            out.println("No tasks found!");
        } else {
            out.println("Here are the matching tasks in your list:");
            filteredList.stream()
                    .forEach((t) -> out.println(String.format("%d.%s", tasks.indexOf(t) + 1, t)));
        }
    }
    public void printTasksFilterByDate(ArrayList<Task> tasks, LocalDate expectedDate) {
        ArrayList<Task> filteredTaskList = (ArrayList<Task>) tasks.stream()
                .filter((t) -> (((t instanceof Deadline) && (((Deadline) t).by.toLocalDate().compareTo(expectedDate) == 0)) ||
                        ((t instanceof Event) && (((Event)t).at.toLocalDate().compareTo(expectedDate) == 0))))
                .collect(Collectors.toList());

        if (filteredTaskList.size() == 0) {
            out.println("No tasks occurring on this date.");
        } else {
            out.println("Here are the tasks occurring on this date:");
            filteredTaskList.stream()
                    .forEach((t) -> out.println(String.format("%d.%s", tasks.indexOf(t) + 1, t)));
        }
    }
}
