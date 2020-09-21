package duke;

import duke.command.*;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidTaskIndexException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class Parser {

    /**
     * Parses a string to an index of tasks.
     *
     * @param taskInfo a string containing the information of a task index.
     * @return index parsed from the string
     * @throws InvalidTaskIndexException if the string can not be parsed to an index.
     */
    public int getTaskIndex(String taskInfo) throws InvalidTaskIndexException {
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

    /**
     * Parses a string to a to-do task.
     *
     * @param taskInfo a string containing the information of a to-do task.
     * @return to-do task parsed from the string.
     * @throws InvalidCommandException if the string can not be parsed to a to-do task.
     */
    private ToDo getToDo(String taskInfo) throws InvalidCommandException {
        taskInfo = taskInfo.trim();
        if (taskInfo.equals("")) throw new InvalidCommandException(1);
        return new ToDo(taskInfo);
    }

    /**
     * Parses a string to a deadline task.
     *
     * @param taskInfo a string containing the information of a deadline task.
     * @return deadline task parsed from the string.
     * @throws InvalidCommandException if the string can not be parsed to a deadline task.
     */
    public Deadline getDeadline(String taskInfo) throws InvalidCommandException {
        int dividePoint = taskInfo.indexOf("/by");
        try {
            String description = taskInfo.substring(0, dividePoint).trim();
            String by = taskInfo.substring(dividePoint + "/by".length()).trim();
            if (description.equals("") || by.equals("")) throw new InvalidCommandException(2);
            return new Deadline(description, by);
        } catch(IndexOutOfBoundsException e) {
            throw new InvalidCommandException(2);
        }
    }

    /**
     * Parses a string to a event task.
     *
     * @param taskInfo a string containing the information of event task.
     * @return event task parsed from the string.
     * @throws InvalidCommandException if the string can not be parsed to a event task.
     */
    public Event getEvent(String taskInfo) throws InvalidCommandException {
        int dividePoint = taskInfo.indexOf("/at");
        try {
            String description = taskInfo.substring(0, dividePoint).trim();
            String at = taskInfo.substring(dividePoint + "/at".length()).trim();
            if (description.equals("") || at.equals("")) throw new InvalidCommandException(3);
            return new Event(description, at);
        } catch(IndexOutOfBoundsException e) {
            throw new InvalidCommandException(3);
        }
    }

    /**
     * Parses the user input from raw text to a command.
     * Returns the command to be executed.
     *
     * @param userInput user's input in raw text.
     * @return command to be executed.
     * @throws InvalidCommandException if the command doesn't follow the format and can not be executed.
     */
    public Command parseCommand(String userInput) throws InvalidCommandException {
        String command = (!userInput.contains(" ")) ? userInput : userInput.substring(0, userInput.indexOf(" "));
        switch (command) {
        case "list":
            return new ListCommand();
        case "todo":
            return new AddCommand(getToDo(userInput.substring("todo".length())));
        case "deadline":
            return new AddCommand(getDeadline(userInput.substring("deadline".length())));
        case "event":
            return new AddCommand(getEvent(userInput.substring("event".length())));
        case "done":
            try {
                return new MarkCommand(getTaskIndex(userInput.substring("done".length())));
            } catch (InvalidTaskIndexException e) {
                throw new InvalidCommandException(4);
            }
        case "delete":
            try {
                return new DeleteCommand(getTaskIndex(userInput.substring("delete".length())));
            } catch (InvalidTaskIndexException e) {
                throw new InvalidCommandException(5);
            }
        default:
            throw new InvalidCommandException(0);
        }
    }
}
