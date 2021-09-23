package ua.sumdu.j2se.mikhailov.tasks.task6;


import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Task task1 = new Task("Task1", 10);
        Task task2 = new Task("Task2", 0, 120, 10);
        Task task3 = new Task("Task3", 15);
        Task task4 = new Task("Task4", 0, 120, 10);


        System.out.println("ArrayTaskList");
        ArrayTaskList arrayTaskList = new ArrayTaskList();

//      Добавляем в список задачи
        arrayTaskList.add(task1);
        arrayTaskList.add(task2);
        arrayTaskList.add(task3);
        arrayTaskList.add(task4);


        Stream<Task> stream = arrayTaskList.getStream();

        stream.forEach(System.out::println);

        System.out.println("LinkedTaskList");

        LinkedTaskList linkedTaskList = new LinkedTaskList();

//      Добавляем в список задачи
        linkedTaskList.add(task1);
        linkedTaskList.add(task2);
        linkedTaskList.add(task3);
        linkedTaskList.add(task4);


        Stream<Task> stream1 = linkedTaskList.getStream();

        stream1.forEach(System.out::println);

        linkedTaskList.incoming(0,20).forEach(System.out::println);


    }
}
