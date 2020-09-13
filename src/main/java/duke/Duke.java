package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


class Duke {
    private static int taskNum;
    private static final int MAX_TASK_SIZE = 100;
    private static final int LINE_CHAR_NUM = 40;
    private static Task[] tasks = new Task[MAX_TASK_SIZE];
    private static final String DIR_PATH = "data/";
    private static final String FILE_PATH = "data/save.txt";

    public static void main(String[] args) {
        try {
            loadFile();
        } catch (InvalidFileException exception) {
            System.out.println("Invalid save file detected.");
        }
        Scanner scan = new Scanner(System.in);
        String text = "";

        drawHorizontalLine(LINE_CHAR_NUM);
        showLogo();
        drawHorizontalLine(LINE_CHAR_NUM);
        greet();
        drawHorizontalLine(LINE_CHAR_NUM);

        do {
            text = scan.nextLine();
            echo(text);
        } while (!text.equals("bye"));

    }

    private static void printSuccessfulAddTaskMessage(Task task) {
        System.out.println(String.format("Got it. I've added this task to your list:\n%s", task));
        countTaskNumber();
    }

    private static void addToDo(String description, boolean isPrintingMessage) throws InvalidCommandException {
        description = description.trim();
        if (description.equals("")) throw new InvalidCommandException(1);
        try {
            tasks[taskNum] = new ToDo(description);
            if (isPrintingMessage) printSuccessfulAddTaskMessage(tasks[taskNum]);
            taskNum++;
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Couldn't add this task because the list is full.");
        }

    }

    private static void addDeadline(String text, boolean isPrintingMessage) throws InvalidCommandException {
        int dividePoint = text.indexOf("/by");
        if (dividePoint == -1) throw new InvalidCommandException(2);
        String description = text.substring(0, dividePoint).trim();
        String by = text.substring(dividePoint + 3, text.length()).trim();
        if (description.equals("") || by.equals("")) throw new InvalidCommandException(2);
        try {
            tasks[taskNum] = new Deadline(description, by);
            if (isPrintingMessage) printSuccessfulAddTaskMessage(tasks[taskNum]);
            taskNum++;
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Couldn't add this task because the list is full.");
        }
    }

    private static void addEvent(String text, boolean isPrintingMessage) throws InvalidCommandException {
        int dividePoint = text.indexOf("/at");
        if (dividePoint == -1) throw new InvalidCommandException(3);
        String description = text.substring(0, dividePoint).trim();
        String at = text.substring(dividePoint + 3, text.length()).trim();
        if (description.equals("") || at.equals("")) throw new InvalidCommandException(3);
        try {
            tasks[taskNum] = new Event(description, at);
            if (isPrintingMessage) printSuccessfulAddTaskMessage(tasks[taskNum]);
            taskNum++;
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Couldn't add this task because the list is full.");
        }

    }

    private static void countTaskNumber() {
        switch (taskNum) {
        case 0:
            System.out.println("You don't have any task in the list now.");
            break;
        case 1:
            System.out.println("You have 1 task in the list now.");
            break;
        default:
            System.out.println(String.format("You have %d tasks in the list now.", taskNum));
            break;
        }
    }

    private static void markAsDone(String taskInfo) throws InvalidCommandException {
        taskInfo = taskInfo.trim();
        boolean isNum = true;
        int taskIndex = -1;
        for (int i = 0; i < taskInfo.length(); i++) {
            if (!Character.isDigit(taskInfo.charAt(i))) {
                isNum = false;
                break;
            }
        }
        if (isNum && (!taskInfo.equals(""))) {
            taskIndex = Integer.parseInt(taskInfo) - 1;
        } else {
            throw new InvalidCommandException(4);
        }
        try {
            tasks[taskIndex].isDone = true;
            System.out.println("OK! I've marked this task as done:");
            System.out.println(tasks[taskIndex]);
        } catch (Exception exception) {
            System.out.println("This task doesn't exist!");
        }
    }

    private static void echo(String text) {
        String command = (!text.contains(" ")) ? text : text.substring(0, text.indexOf(" "));
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
                markAsDone(text.substring(4, text.length()));
                saveFile();
                break;
            case "todo":
                addToDo(text.substring(4, text.length()), true);
                saveFile();
                break;
            case "deadline":
                addDeadline(text.substring(8, text.length()), true);
                saveFile();
                break;
            case "event":
                addEvent(text.substring(5, text.length()), true);
                saveFile();
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
                System.out.println("Incorrect command line argument(s).\ndeadline -taskName /by taskTime");
                break;
            case 3:
                System.out.println("Incorrect command line argument(s).\nevent -taskName /at taskTime");
                break;
            case 4:
                System.out.println("Incorrect command line argument(s).\ndone -taskIndex");
                break;
            }

        }
        drawHorizontalLine(40);
    }

    private static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskNum; i++) {
            System.out.println(String.format("%d.%s", i + 1, tasks[i]));
        }
    }

    private static void joke() {
        System.out.println("There are only 10 kinds of people in this world:\nthose who know binary and those who don't.");
    }

    private static void saveFile() {
        try {
            new File(DIR_PATH).mkdir();
            new File(FILE_PATH).createNewFile();
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            String text = "";
            for (int i = 0; i < taskNum; i++) {
                if (tasks[i] instanceof ToDo) {
                    text += (tasks[i].isDone)? "T|1|":"T|0|";
                    text += tasks[i].description + System.lineSeparator();
                } else if (tasks[i] instanceof Deadline) {
                    text += (tasks[i].isDone)? "D|1|":"D|0|";
                    text += tasks[i].description + "|" + ((Deadline)tasks[i]).by + System.lineSeparator();
                } else {
                    text += (tasks[i].isDone)? "E|1|":"E|0|";
                    text += tasks[i].description + "|" + ((Event)tasks[i]).at + System.lineSeparator();
                }
            }
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println("Accessing data failed.");
        }
    }

    private static void loadFile() throws InvalidFileException{
        File file = new File(FILE_PATH);
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String taskInfo = fileScanner.nextLine();
                char taskType = taskInfo.charAt(0);
                boolean taskIsDone = taskInfo.charAt(2) == '1';
                taskInfo = taskInfo.substring(4);
                switch (taskType) {
                case 'T':
                    try {
                        addToDo(taskInfo, false);
                        tasks[taskNum - 1].isDone = taskIsDone;
                    } catch (InvalidCommandException exception) {
                        throw new InvalidFileException();
                    }
                    break;
                case 'D':
                    try {
                        addDeadline(taskInfo.replace("|","/by"), false);
                        tasks[taskNum - 1].isDone = taskIsDone;
                    } catch (InvalidCommandException exception) {
                        throw new InvalidFileException();
                    }
                    break;
                case 'E':
                    try {
                        addEvent(taskInfo.replace("|", "/at"), false);
                        tasks[taskNum - 1].isDone = taskIsDone;
                    } catch (InvalidCommandException exception) {
                        throw new InvalidFileException();
                    }
                }
            }
        } catch (FileNotFoundException exception) {
            saveFile();
            loadFile();
        }

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
