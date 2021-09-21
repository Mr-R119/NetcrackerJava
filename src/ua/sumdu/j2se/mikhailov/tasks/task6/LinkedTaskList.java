package ua.sumdu.j2se.mikhailov.tasks.task6;

import java.util.Arrays;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList {

    private Node<Task> head;
    private Node<Task> tail;
    private int size = 0;


    private static class Node<Task> {
        Task item;
        Node<Task> next;
        Node<Task> prev;

        Node(Node<Task> prev, Task element, Node<Task> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public void add(Task task) {
        link(task);
    }


    public boolean remove(Task task) {
        for (Node<Task> x = head; x != null; x = x.next) {
            if (x.item.equals(task)) {
                unlink(x);
                return true;
            }
        }
        return false;
    }

    void link(Task task) {
        final Node<Task> l = tail;
        final Node<Task> newNode = new Node<>(l, task, null);
        tail = newNode;
        if (l == null)
            head = newNode;
        else
            l.next = newNode;
        size++;
    }

    Task unlink(Node<Task> x) {

        final Task element = x.item;
        final Node<Task> next = x.next;
        final Node<Task> prev = x.prev;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    public Task getTask(int index) {
        if (!rangeCheck(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        if (index < (size >> 1)) {
            Node<Task> x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x.item;
        } else {
            Node<Task> x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x.item;
        }
    }

    private boolean rangeCheck(int index) {
        return index >= 0 && index < size;
    }

    public int size() {
        return size;
    }


    public ListIterator<Task> listItr() {
        return new ListItr();
    }

    private class ListItr implements ListIterator<Task> {
        private Node<Task> lastReturned;
        private Node<Task> next;
        private int nextIndex;


        @Override
        public boolean hasNext() {
            return nextIndex != size;
        }

        @Override
        public Task next() {

            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public Task previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();

            lastReturned = next = (next == null) ? tail : next.prev;
            nextIndex--;
            return lastReturned.item;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();

            Node<Task> last = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned)
                next = last;
            else
                nextIndex--;
            lastReturned = null;
        }

        @Override
        public void set(Task task) {
            if (lastReturned == null)
                throw new IllegalStateException();
            lastReturned.item = task;
        }

        @Override
        public void add(Task task) {
            lastReturned = null;
            link(task);
            nextIndex++;

        }
    }

    @Override
    public Stream<Task> getStream() {
        return Arrays.stream(toArray());
    }

    public Task[] toArray() {
        Task[] tasks = new Task[size];
        int i = 0;
        for (Node<Task> x = head; x != null; x = x.next){
            tasks[i++] = x.item;
        }
        return tasks;
    }

    @Override
    public LinkedTaskList clone() {
        try {
            LinkedTaskList clone = (LinkedTaskList) super.clone();
            clone.head = clone.tail = null;
            clone.size = 0;
            for (Node<Task> x = head; x != null; x = x.next)
                clone.add(x.item.clone());
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}

