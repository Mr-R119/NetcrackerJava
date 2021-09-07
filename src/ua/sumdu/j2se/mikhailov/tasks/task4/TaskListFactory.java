package ua.sumdu.j2se.mikhailov.tasks.task4;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes type){

        AbstractTaskList taskList;

        switch (type){
            case ARRAY -> taskList = new ArrayTaskList();
            case LINKED -> taskList = new LinkedTaskList();

            default -> throw new IllegalStateException("Unexpected value: " + type);
        }

        return taskList;
    }
}
