package ua.sumdu.j2se.mikhailov.tasks.task3;

public class Main {


    public static void main(String[] args) {
        Task task1 = new Task("Task1", 10);
        Task task2 = new Task("Task2", 0, 120, 10);
        Task task3 = new Task("Task3", 15);
        Task task4 = new Task("Task4", 10, 20, 2);


        LinkedTaskList linkedTaskList = new LinkedTaskList();

        linkedTaskList.add(task1);
        linkedTaskList.add(task2);
        linkedTaskList.add(task3);
        linkedTaskList.add(task4);

        System.out.println(linkedTaskList.size());

        linkedTaskList.remove(task2);

        System.out.println(linkedTaskList.size());

        System.out.println(linkedTaskList.incoming(10,20).getTask(0).getTitle());

        System.out.println(linkedTaskList.incoming(10,20).getTask(1).getTitle());


//
//        for (int i = 0; i < arrayTaskList.size(); i++) {
//            System.out.println(arrayTaskList.getTask(i).getTitle());
//        }
//        System.out.println(arrayTaskList.size());

//        ArrayTaskList arrayTaskList1 = arrayTaskList.incoming(12,15);
//        for (int i = 0; i < arrayTaskList1.size(); i++){
//            System.out.println(arrayTaskList1.getTask(i).getTitle());
//        }
    }
}
