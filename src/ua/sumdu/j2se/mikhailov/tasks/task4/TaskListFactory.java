package ua.sumdu.j2se.mikhailov.tasks.task4;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes type) throws IllegalStateException {

        AbstractTaskList taskList;

        switch (type){
            case ARRAY: {
                taskList = new ArrayTaskList();
                break;
            }
            case LINKED:{
                taskList = new LinkedTaskList();
                break;
            }

            default: throw new IllegalStateException("Unexpected value: " + type);
        }

        return taskList;
    }
}
