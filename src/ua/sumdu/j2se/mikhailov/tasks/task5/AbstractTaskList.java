package ua.sumdu.j2se.mikhailov.tasks.task5;

import java.util.Iterator;

public abstract class AbstractTaskList implements Iterable<Task> {

    protected int size;

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract Task getTask(int index);

    public int size() {
        return size;
    }

    public static Iterable<Task> incoming(Iterable<Task> tasks, int from, int to) {
        if (to < 0)
            throw new IllegalArgumentException("Time must be greater than 0");
        AbstractTaskList incomingTasks = new ArrayTaskList();
        for (Task task : tasks) {
            if (!(task.getStartTime() < from) && !(task.getEndTime() > to)) {
                incomingTasks.add(task);
            }
        }
        return incomingTasks;
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {

            int tmp = 0;

            @Override
            public boolean hasNext() {
                return size() > tmp && getTask(tmp) != null;
            }

            @Override
            public Task next() {
                return getTask(tmp++);
            }

            @Override
            public void remove() {
                AbstractTaskList.this.remove(getTask(tmp));
            }
        };
    }


    @Override
    public boolean equals(Object obj) {

        boolean result = true;

        if (obj == this)
            return true;

        AbstractTaskList other = (AbstractTaskList) obj;
        if (size() != other.size()) {
            return false;
        }

        for (int i = 0; i < other.size(); i++) {
            if (!getTask(i).equals(other.getTask(i)))
                result = false;
        }

        return result;
    }


    @Override
    public int hashCode() {
        int result = 1;
        for (int i = 0; i < size(); i++) {
            result = 31 * result + getTask(i).hashCode();
        }

        return result;
    }


    @Override
    public String toString() {
        String write = "";
        for (int i = 0; i < size(); i++) {
            write += getTask(i).toString() + "\n";
        }
        return write;
    }


}
