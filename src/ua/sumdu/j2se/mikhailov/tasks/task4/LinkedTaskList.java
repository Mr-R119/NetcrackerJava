package ua.sumdu.j2se.mikhailov.tasks.task4;

public class LinkedTaskList extends AbstractTaskList {

    private Task task;
    private LinkedTaskList head;
    private LinkedTaskList tail;
    private LinkedTaskList next;

    @Override
    public void add(Task task){
        if(head == null){
            head = tail = new LinkedTaskList();
            head.task = task;
            head.next = tail;
            tail = head;
        }
        else {
            tail.next = new LinkedTaskList();
            tail = tail.next;
            tail.task = task;
        }
        size++;
    }

    @Override
    public boolean remove(Task task){
        LinkedTaskList tmp = head;
        LinkedTaskList temp = null;

        if(head.task == task) {
            head = head.next;
            size--;
            return true;
        } else if(tail.task == task){
            tail.next = null;
            size--;
            return true;
        }

        while(tmp != null && !(tmp.task == task)){
            temp = tmp;
            tmp = tmp.next;
        }

        if(tmp == null) return false;

        temp.next = tmp.next;
        size--;

        return true;
    }

    @Override
    public Task getTask(int index){
        rangeCheck(index);

        LinkedTaskList list = head;

        for(int i = 0; i < index; i++)
            list = list.next;
        return list.task;
    }

    private void rangeCheck(int index){
        if(index >= size){
            throw new IndexOutOfBoundsException("Index: " + index + "Size: " + size);
        }
    }

//    @Override
//    public int size(){
//        return size;
//    }
}
