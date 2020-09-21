package duke;

import duke.exception.InvalidFileException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final int TASK_TYPE_POS = 0;
    private final int TASK_STATUS_POS = 2;
    private final int TASK_INFO_START_POS = 4;
    private String dirPath;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.dirPath = filePath.substring(0, filePath.lastIndexOf("/"));
    }

    public void saveFile(TaskList taskList) throws IOException {
        new File(dirPath).mkdir();
        new File(filePath).createNewFile();
        FileWriter fileWriter = new FileWriter(filePath);
        String text = "";
        for (Task task: taskList.tasks) {
            if (task instanceof ToDo) {
                text += ((task.isDone) ? "T|1|" : "T|0|") + task.description + System.lineSeparator();
            } else if (task instanceof Deadline) {
                text += ((task.isDone) ? "D|1|" : "D|0|") + task.description + "|" + ((Deadline) task).by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm")) + System.lineSeparator();
            } else {
                text += ((task.isDone) ? "E|1|" : "E|0|") + task.description + "|" + ((Event) task).at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm")) + System.lineSeparator();
            }
        }
        fileWriter.write(text);
        fileWriter.close();
    }

    public TaskList loadFile() throws InvalidFileException {
        TaskList savedTaskList = new TaskList(new ArrayList<Task>());
        File file = new File(filePath);
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String taskInfo = fileScanner.nextLine();
                char taskType = taskInfo.charAt(TASK_TYPE_POS);
                boolean taskIsDone = taskInfo.charAt(TASK_STATUS_POS) == '1';
                taskInfo = taskInfo.substring(TASK_INFO_START_POS);
                Task task;
                switch (taskType) {
                case 'T':
                    task = new ToDo(taskInfo);
                    break;
                case 'D':
                    task = new Deadline(taskInfo.substring(0, taskInfo.indexOf('|')), LocalDateTime.parse(taskInfo.substring(taskInfo.indexOf('|') + 1), DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm")));
                    break;
                case 'E':
                    task = new Event(taskInfo.substring(0, taskInfo.indexOf('|')), LocalDateTime.parse(taskInfo.substring(taskInfo.indexOf('|') + 1), DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm")));
                    break;
                default:
                    throw new InvalidFileException();
                }
                task.isDone = taskIsDone;
                savedTaskList.add(task);
            }
            return savedTaskList;
        } catch (FileNotFoundException e) {
            return new TaskList(new ArrayList<Task>());
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new InvalidFileException();
        }
    }
}
