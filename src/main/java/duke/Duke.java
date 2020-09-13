package duke;

import java.util.ArrayList;

import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidTaskIndexException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;

class Duke {
    private static int LINE_CHAR_NUM = 40;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        String text;

        drawHorizontalLine(LINE_CHAR_NUM);
        showLogo();
        drawHorizontalLine(LINE_CHAR_NUM);
        greet();
        drawHorizontalLine(LINE_CHAR_NUM);

        do {
            text = readInput();
            echo(text);
        } while (!text.equals("bye"));

    }

    private static String readInput() {
        return scan.nextLine();
    }

    private static void addToDo(String description) throws InvalidCommandException {
        description = description.trim();
        if (description.equals("")) throw new InvalidCommandException(1);
        tasks.add(new ToDo(description));
        System.out.println(String.format("Got it. I've added this task to your list:\n%s", tasks.get(tasks.size() - 1)));
        countTaskNumber();
    }

    private static void addDeadline(String text) throws InvalidCommandException{
        int dividePoint = text.indexOf("/by");
        if (dividePoint == -1) throw new InvalidCommandException(2);
        String description = text.substring(0, dividePoint).trim();
        String by = text.substring(dividePoint + 3).trim();
        if (description.equals("") || by.equals("")) throw new InvalidCommandException(2);
        tasks.add(new Deadline(description, by));
        System.out.println(String.format("Got it. I've added this task to your list:\n%s", tasks.get(tasks.size() - 1)));
        countTaskNumber();
    }

    private static void addEvent(String text) throws InvalidCommandException{
        int dividePoint = text.indexOf("/at");
        if (dividePoint == -1) throw new InvalidCommandException(3);
        String description = text.substring(0, dividePoint).trim();
        String at = text.substring(dividePoint + 3).trim();
        if (description.equals("") || at.equals("")) throw new InvalidCommandException(3);
        tasks.add(new Event(description, at));
        System.out.println(String.format("Got it. I've added this task to your list:\n%s", tasks.get(tasks.size() - 1)));
        countTaskNumber();
    }

    private static void countTaskNumber() {
        switch (tasks.size()) {
        case 0:
            System.out.println("You don't have any task in the list now.");
            break;
        case 1:
            System.out.println("You have 1 task in the list now.");
            break;
        default:
            System.out.println(String.format("You have %d tasks in the list now.", tasks.size()));
            break;
        }
    }

    private static int getTaskIndex(String taskInfo) throws InvalidTaskIndexException {
        taskInfo = taskInfo.trim();
        boolean isNum = true;
        int taskIndex;
        for (int i = 0; i < taskInfo.length(); i++) {
            if (!Character.isDigit(taskInfo.charAt(i))) {
                isNum = false;
                break;
            }
        }
        if (isNum && (!taskInfo.equals(""))) {
            taskIndex = Integer.parseInt(taskInfo) - 1;
        } else {
            throw new InvalidTaskIndexException();
        }
        return taskIndex;
    }

    private static void markAsDone(String taskInfo) throws InvalidCommandException {
        int taskIndex;
        try {
            taskIndex = getTaskIndex(taskInfo);
        } catch (InvalidTaskIndexException exception) {
            throw new InvalidCommandException(4);
        }
        try {
            tasks.get(taskIndex).isDone = true;
            System.out.println("OK! I've marked this task as done:");
            System.out.println(tasks.get(taskIndex));
        } catch (Exception exception) {
            System.out.println("This task doesn't exist!");
        }
    }

    private static void deleteTask(String taskInfo) throws InvalidCommandException {
        int taskIndex;
        try {
            taskIndex = getTaskIndex(taskInfo);
        } catch (InvalidTaskIndexException exception) {
            throw new InvalidCommandException(5);
        }
        try {
            System.out.println(String.format("You're deleting this task:%n%s%nType yes to delete, no to cancel.", tasks.get(taskIndex)));
            String answer = readInput();
            while (!((answer.equals("yes")) || (answer.equals("no")))) {
                System.out.println("Type yes to delete, no to cancel.");
                answer = readInput();
            }
            if (answer.equals("yes")) {
                tasks.remove(taskIndex);
                System.out.println("OK! I've deleted the task.");
            } else {
                System.out.println("Deletion cancelled.");
            }
            countTaskNumber();
        } catch (Exception exception) {
            System.out.println("This task doesn't exist!");
        }
    }

    private static void echo(String text) {
        String command = (!text.contains(" "))? text:text.substring(0,text.indexOf(" "));
        try {
            switch (command) {
            case "list":
                list();
                break;
            case "joke":
                joke();
                break;
            case "bye":
                exit();
                break;
            case "done":
                markAsDone(text.substring(4));
                break;
            case "delete":
                deleteTask(text.substring(6));
                break;
            case "todo":
                addToDo(text.substring(4));
                break;
            case "deadline":
                addDeadline(text.substring(8));
                break;
            case "event":
                addEvent(text.substring(5));
                break;
            default:
                throw new InvalidCommandException(0);
            }
        } catch (InvalidCommandException exception) {
            switch (exception.exceptionCode) {
            case 0:
                System.out.println("No such command!");
                break;
            case 1:
                System.out.println("Incorrect command line argument(s).\ntodo -taskName");
                break;
            case 2:
                System.out.println("Incorrect command line argument(s).\ndeadline -taskName /by -taskTime");
                break;
            case 3:
                System.out.println("Incorrect command line argument(s).\nevent -taskName /at -taskTime");
                break;
            case 4:
                System.out.println("Incorrect command line argument(s).\ndone -taskIndex");
                break;
            case 5:
                System.out.println("Incorrect command line argument(s).\ndelete -taskIndex");
                break;
            }

        }
        drawHorizontalLine(40);
    }

    private static void list() {
        if (tasks.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(String.format("%d.%s", i + 1, tasks.get(i)));
            }
        } else {
            System.out.println("You don't have any tasks in your list now.");
        }
    }

    private static void joke() {
        System.out.println("There are only 10 kinds of people in this world:\nthose who know binary and those who don't.");
    }

    private static void showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "Modified by Wu Haitao\n";
        System.out.println("Hello from\n" + logo);
    }

    private static void drawHorizontalLine(int length) {
        System.out.println("-".repeat(Math.max(0, length)));
    }

    private static void greet() {
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
