package ua.sumdu.j2se.mikhailov.tasks.task4;

public abstract class AbstractTaskList {

    protected int size;

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract Task getTask(int index);

    public int size(){
        return size;
    }

    public AbstractTaskList incoming(int from, int to){
        if(to < 0)
            throw new IllegalArgumentException("Time must be greater than 0");

        AbstractTaskList incomingTasks = TaskListFactory.createTaskList(ListTypes.ARRAY);
        for(int i = 0; i < size; i++){
            if(getTask(i).nextTimeAfter(from) != -1 && getTask(i).nextTimeAfter(from) <= to)
                incomingTasks.add(getTask(i));
        }
        return incomingTasks;
    }


}
