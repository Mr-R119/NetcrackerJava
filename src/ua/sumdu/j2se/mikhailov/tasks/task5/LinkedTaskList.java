package ua.sumdu.j2se.mikhailov.tasks.task5;

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
        final Node<Task> l = tail;
        final Node<Task> newNode = new Node<>(l, task, null);
        tail = newNode;
        if (l == null)
            head = newNode;
        else
            l.next = newNode;
        size++;

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


    private LinkedTaskList superClone() {
        try {
            return (LinkedTaskList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }


    @Override
    public Object clone() {

            LinkedTaskList clone = superClone();

            clone.head = clone.tail = null;
            clone.size = 0;

            for (Node<Task> x = head; x != null; x = x.next)
                clone.add(x.item);

            return clone;
    }
}

