package ua.sumdu.j2se.mikhailov.tasks.task4;


public class Main {


    public static void main(String[] args) {
        Task task1 = new Task("Task1", 10);
        Task task2 = new Task("Task2", 0, 120, 10);
        Task task3 = new Task("Task3", 15);
        Task task4 = new Task("Task4", 10, 20, 2);


        AbstractTaskList arrayTaskList = TaskListFactory.createTaskList(ListTypes.ARRAY);

        arrayTaskList.add(task1);
        arrayTaskList.add(task2);
        arrayTaskList.add(task3);
        arrayTaskList.add(task4);

        arrayTaskList.remove(task1);

        System.out.println(arrayTaskList.getTask(1).getTitle());
        System.out.println(arrayTaskList.incoming(10,20).size());


        AbstractTaskList linkedTaskList = TaskListFactory.createTaskList(ListTypes.LINKED);

        linkedTaskList.add(task1);
        linkedTaskList.add(task2);
        linkedTaskList.add(task3);
        linkedTaskList.add(task4);

        System.out.println(linkedTaskList.getTask(3).getTitle());
        linkedTaskList.remove(task2);

        System.out.println(arrayTaskList.incoming(10,20).size());

    }
}
