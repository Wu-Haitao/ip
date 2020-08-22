import java.util.Scanner;

class Duke {
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
            System.out.println("Invalid command.");
            break;
        }
        drawHorizontalLine(40);
    }

    private static void list() {
        System.out.println("List");
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
