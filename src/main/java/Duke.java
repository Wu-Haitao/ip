public class Duke {
    public static void main(String[] args) {
        showLogo();
        drawHorizontalLine(40);
        greet();
        drawHorizontalLine(40);
        exit();
        drawHorizontalLine(40);
    }

    private static void showLogo(){
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
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
