package duke;

import duke.command.*;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidTaskIndexException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
            String byText = taskInfo.substring(dividePoint + "/by".length()).trim();
            if (description.equals("") || byText.equals("")) throw new InvalidCommandException(2);
            LocalDateTime byTime = LocalDateTime.parse(byText, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return new Deadline(description, byTime);
        } catch(IndexOutOfBoundsException | DateTimeParseException e) {
            throw new InvalidCommandException(2);
        }
    }

    public Event getEvent(String taskInfo) throws InvalidCommandException {
        int dividePoint = taskInfo.indexOf("/at");
        try {
            String description = taskInfo.substring(0, dividePoint).trim();
            String atText = taskInfo.substring(dividePoint + "/at".length()).trim();
            if (description.equals("") || atText.equals("")) throw new InvalidCommandException(3);
            LocalDateTime atTime = LocalDateTime.parse(atText, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return new Event(description, atTime);
        } catch(IndexOutOfBoundsException | DateTimeParseException e) {
            throw new InvalidCommandException(3);
        }
    }

    public LocalDate getDate(String dateInfo) throws InvalidCommandException {
        LocalDate expectedDate;
        try {
            expectedDate = LocalDate.parse(dateInfo.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException(6);
        }
        return expectedDate;
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
        case "date":
            return new FilterByDateCommand(getDate(userInput.substring("date".length())));
        case "find":
            return new FindCommand(userInput.substring("find".length()).trim());
        default:
            throw new InvalidCommandException(0);
        }
    }
}
