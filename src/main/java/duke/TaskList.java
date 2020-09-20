package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public int getTaskListSize() {
        return tasks.size();
    }

    public Task getTaskByIndex(int index) {
        return tasks.get(index);
    }
}
