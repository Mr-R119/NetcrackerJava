package ua.sumdu.j2se.mikhailov.tasks.task4;

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

    public void add(Task task){
        final Node<Task> l = tail;
        final Node<Task> newNode = new Node<>(l, task, null);
        tail = newNode;
        if (l == null)
            head = newNode;
        else
            l.next = newNode;
        size++;

    }


    public boolean remove(Task task){
        if (task == null) {
            for (Node<Task> x = head; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<Task> x = head; x != null; x = x.next) {
                if (task.equals(x.item)) {
                    unlink(x);
                    return true;
                }
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

    public Task getTask(int index){
        if(!rangeCheck(index))
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);

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

    private boolean rangeCheck(int index){
        return index >= 0 && index < size;
    }

    public int size(){
        return size;
    }


    public LinkedTaskList incoming(int from, int to) {
        if (to < 0)
            throw new IllegalArgumentException("Time must be greater than 0");
        LinkedTaskList incomingTasks = new LinkedTaskList();
        for (int i = 0; i < size; i++) {
            if (getTask(i).nextTimeAfter(from) != -1 && getTask(i).nextTimeAfter(from) <= to)
                incomingTasks.add(getTask(i));
        }
        return incomingTasks;
    }
}
