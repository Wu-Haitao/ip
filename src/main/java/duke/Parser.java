package duke;

import duke.command.*;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidTaskIndexException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class Parser {

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
    private ToDo getToDo(String taskInfo) throws InvalidCommandException {
        taskInfo = taskInfo.trim();
        if (taskInfo.equals("")) throw new InvalidCommandException(1);
        return new ToDo(taskInfo);
    }
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
        case "find":
            return new FindCommand(userInput.substring("find".length()).trim());
        default:
            throw new InvalidCommandException(0);
        }
    }
}
