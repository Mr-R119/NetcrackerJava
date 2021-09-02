package ua.sumdu.j2se.mikhailov.tasks.task2;

import ua.sumdu.j2se.mikhailov.tasks.task1.Task;

public class ArrayTaskList  {

    private Task[] tasks;
    private int index = 0;


    public void add(Task task) {
        if (tasks == null) {
            tasks = new Task[10];
        }

        if (index < tasks.length) {
            tasks[index] = task;
        } else {
            Task[] tasks1 = new Task[index + 10];
            System.arraycopy(tasks, 0, tasks1, 0, index);
            tasks1[index] = task;
            tasks = tasks1;
        }
        index++;
    }


    public boolean remove(Task task) {
        boolean result = false;

        for (int i = 0; i < tasks.length; ++i) {
            if (tasks[i] == task) {
                tasks[i] = null;
                index--;
                result = true;
                break;
            }
        }

        if (result) {
            Task[] tasks1 = new Task[index];
            int pos = 0;
            for (Task task1 : tasks) {
                if (task1 != null) {
                    tasks1[pos] = task1;
                    pos++;
                }
            }
            tasks = tasks1;
        }
        return result;
    }


    public int size() {
        int count = 0;
        for (Task task : tasks)
            if (task != null) ++count;
        return count;
    }


    public Task getTask(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index of task must be greater than 0");
        }
        return tasks[index];
    }


    public ArrayTaskList incoming(int from, int to){
        ArrayTaskList incomingTasks = new ArrayTaskList();
        for(int i = 0; i < index; i++){
            if(tasks[i].nextTimeAfter(from) != -1 && tasks[i].nextTimeAfter(from) <= to)
                incomingTasks.add(tasks[i]);
        }
        return incomingTasks;
    }
}
