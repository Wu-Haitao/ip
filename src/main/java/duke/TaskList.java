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

    /**
     * Adds a task to the list.
     *
     * @param task task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task in the list.
     *
     * @param index index of task to be deleted
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return size of the task list.
     */
    public int getTaskListSize() {
        return tasks.size();
    }

    /**
     * Returns a task corresponding to a certain index.
     *
     * @param index index of the task.
     * @return task with the index.
     */
    public Task getTaskByIndex(int index) {
        return tasks.get(index);
    }
}
