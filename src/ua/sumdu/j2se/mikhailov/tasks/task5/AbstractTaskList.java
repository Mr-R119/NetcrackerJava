package ua.sumdu.j2se.mikhailov.tasks.task5;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable {

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
            if (task.nextTimeAfter(from) != -1 && task.nextTimeAfter(from) <= to) {
                incomingTasks.add(task);
            }
        }
        return incomingTasks;
    }

    public Iterator<Task> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Task> {
        int cursor = 0;
        int lastRet = -1;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public Task next() {
            try {
                int i = cursor;
                Task next = getTask(i);
                lastRet = i;
                cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }
    }


    @Override
    public boolean equals(Object obj) {

        boolean result = true;

        if (obj == this)
            return true;

        if (!(obj instanceof AbstractTaskList))
            return false;

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
        StringBuilder write = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            write.append(getTask(i).toString()).append("\n");
        }
        return write.toString();
    }
}
