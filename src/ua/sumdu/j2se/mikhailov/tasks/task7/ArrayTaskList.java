package ua.sumdu.j2se.mikhailov.tasks.task7;

import java.util.*;
import java.util.stream.Stream;

public class ArrayTaskList extends AbstractTaskList {

    private Task[] tasks = new Task[]{};

    @Override
    public void add(Task task) {
        if (Objects.equals(task, null)) {
            throw new NullPointerException("The task was empty(null)!");
        }
        size = tasks.length;
        tasks = Arrays.copyOf(tasks, size + 1);
        tasks[tasks.length - 1] = task;
    }


    @Override
    public boolean remove(Task task) {
        for (int index = 0; index < size; index++)
            if (task == tasks[index]) {
                fastRemove(index);
                return true;
            }
        return false;

    }

    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(tasks, index + 1, tasks, index,
                    numMoved);
        tasks[--size] = null; // clear to let GC do its work
    }

    @Override
    public Task getTask(int index) {
        rangeCheck(index);

        return tasks[index];
    }

    private void rangeCheck(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
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
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();

            Task[] data = ArrayTaskList.this.tasks;
            if (i >= tasks.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return data[lastRet = i];
        }
    }

    @Override
    public Stream<Task> getStream() {
        return Arrays.stream(tasks);
    }

    @Override
    public ArrayTaskList clone() {
        try {
            ArrayTaskList tmp = (ArrayTaskList) super.clone();
            tmp.tasks = Arrays.copyOf(tasks, size);
            for (int i = 0; i < size; i++)
                tmp.tasks[i] = getTask(i).clone();
            return tmp;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}
