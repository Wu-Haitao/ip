import java.util.Scanner;

class Duke {
    private static int taskNum;
    private static Task[] tasks = new Task[100];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String text = "";

        drawHorizontalLine(40);
        showLogo();
        drawHorizontalLine(40);
        greet();
        drawHorizontalLine(40);

        do {
            text = scan.nextLine();
            echo(text);
        } while (!text.equals("bye"));

    }

    private static void addToDo(String text) {
        tasks[taskNum] = new ToDo(text.trim());
        System.out.println(String.format("Got it. I've added this task to your list:\n%s", tasks[taskNum]));
        taskNum++;
        countTaskNumber();
    }

    private static void addDeadline(String text) {
        int dividePoint = text.indexOf("/by");
        if (dividePoint != -1) {
            tasks[taskNum] = new Deadline(text.substring(0, dividePoint).trim(), text.substring(dividePoint + 3, text.length()).trim());
            System.out.println(String.format("Got it. I've added this task to your list:\n%s", tasks[taskNum]));
            taskNum++;
        } else {
            System.out.println("Invalid task.");
        }
        countTaskNumber();
    }

    private static void addEvent(String text) {
        int dividePoint = text.indexOf("/at");
        if (dividePoint != -1) {
            tasks[taskNum] = new Event(text.substring(0, dividePoint).trim(), text.substring(dividePoint + 3, text.length()).trim());
            System.out.println(String.format("Got it. I've added this task to your list:\n%s", tasks[taskNum]));
            taskNum++;
        } else {
            System.out.println("Invalid task.");
        }
        countTaskNumber();
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

    private static void markAsDone(String taskInfo) {
        boolean isNum = true;
        int taskIndex = -1;
        for (int i = 0; i < taskInfo.length(); i++) {
            if (!Character.isDigit(taskInfo.charAt(i))) {
                isNum = false;
                break;
            }
        }
        if (isNum) taskIndex = Integer.parseInt(taskInfo) - 1;
        if ((taskIndex != -1) && (taskIndex < taskNum )) {
            System.out.println("OK! I've marked this task as done:");
            tasks[taskIndex].isDone = true;
            System.out.println(tasks[taskIndex]);

        } else {
            System.out.println("This task doesn't exist!");
        }
    }

    private static void echo(String text) {
        String command = (!text.contains(" "))? text:text.substring(0,text.indexOf(" "));
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
            markAsDone(text.substring(5, text.length()));
            break;
        case "todo":
            addToDo(text.substring(5, text.length()));
            break;
        case "deadline":
            addDeadline(text.substring(9, text.length()));
            break;
        case "event":
            addEvent(text.substring(6, text.length()));
            break;
        default:
            System.out.println("Invalid command.");
            break;
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
        System.out.println("There are only 10 kinds of people in this world:\nthose who know binary and those who don’t.");
    }

    private static void showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
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
