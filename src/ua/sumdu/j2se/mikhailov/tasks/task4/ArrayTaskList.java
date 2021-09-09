package ua.sumdu.j2se.mikhailov.tasks.task4;

public class ArrayTaskList extends AbstractTaskList {

    private Task[] tasks;

    @Override
    public void add(Task task) {

        if (tasks == null) {
            tasks = new Task[10];
        }

        if (size < tasks.length) {
            tasks[size] = task;
        } else {
            Task[] tasks1 = new Task[size + 10];
            System.arraycopy(tasks, 0, tasks1, 0, size);
            tasks1[size] = task;
            tasks = tasks1;
        }
        size++;
    }

    @Override
    public boolean remove(Task task) {

        if (task == null) {
            for (int index = 0; index < size; index++)
                if (tasks[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (task == tasks[index]) {
                    fastRemove(index);
                    return true;
                }
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

    private void rangeCheck(int index){
        if(index >= size){
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
        }
    }


//    public ArrayTaskList incoming(int from, int to){
//        if(to < 0)
//            throw new IllegalArgumentException("Time must be greater than 0");
//        ArrayTaskList incomingTasks = new ArrayTaskList();
//        for(int i = 0; i < size; i++){
//            if(tasks[i].nextTimeAfter(from) != -1 && tasks[i].nextTimeAfter(from) <= to)
//                incomingTasks.add(tasks[i]);
//        }
//        return incomingTasks;
//    }
}
