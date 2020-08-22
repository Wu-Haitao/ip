import java.util.Scanner;

class Duke {
    private static int taskNum;
    private static Task[] tasks = new Task[100];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String command = "";

        drawHorizontalLine(40);
        showLogo();
        drawHorizontalLine(40);
        greet();
        drawHorizontalLine(40);

        do {
            command = scan.nextLine();
            echo(command);
        } while (!command.equals("bye"));

    }

    private static void add(String text) {
        tasks[taskNum] = new Task(text);
        taskNum++;
        System.out.println("Added: " + text);
    }

    private static void markAsDone(int taskIndex) {
        System.out.println("OK! I've marked this task as done:");
        System.out.println("[✓] " + tasks[taskIndex].description);
        tasks[taskIndex].isDone = true;
    }

    private static void echo(String command) {
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
        default:
            if (command.substring(0, 5).equals("done ")) {
                boolean isNum = true;
                for (int i = 5; i < command.length(); i++) {
                    if (!Character.isDigit(command.charAt(i))) {
                        isNum = false;
                        break;
                    }
                }
                if (isNum) {
                    int taskIndex = Integer.parseInt(command.substring(5, command.length())) - 1;
                    if ((taskIndex < taskNum) && (taskIndex >= 0)) {
                        markAsDone(taskIndex);
                        break;
                    }
                }
                System.out.println("This task doesn't exist!");
                break;
            }
            add(command);
            break;
        }
        drawHorizontalLine(40);
    }

    private static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskNum; i++) {
            Character status = (tasks[i].isDone)? '✓':'✗';
            System.out.println(String.format("%d.[%c] %s", i + 1, status, tasks[i].description));
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
        String horizontalLine = "";
        for (int i = 0; i < length; i++) horizontalLine += '-';
        System.out.println(horizontalLine);
    }

    private static void greet() {
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
