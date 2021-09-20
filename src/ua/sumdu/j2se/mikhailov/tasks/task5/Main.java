package ua.sumdu.j2se.mikhailov.tasks.task5;


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

//      Клонирование
        ArrayTaskList arrayTaskList1 = arrayTaskList.clone();
        arrayTaskList1.getTask(1).setTitle("1111");
        System.out.println(arrayTaskList);
        System.out.println("Копия:\n" + arrayTaskList1);


        System.out.println("LinkedTaskList");

        LinkedTaskList linkedTaskList = new LinkedTaskList();

//      Добавляем в список задачи
        linkedTaskList.add(task1);
        linkedTaskList.add(task2);
        linkedTaskList.add(task3);
        linkedTaskList.add(task4);

//      Клонирование
        LinkedTaskList linkedTaskList1 = linkedTaskList.clone();
        linkedTaskList1.getTask(1).setTitle("1111");
        System.out.println(linkedTaskList);
        System.out.println("Копия:\n" + linkedTaskList1);

//        Проверка метода equals()
//        System.out.println(arrayTaskList.equals(arrayTaskList1));
//        System.out.println(arrayTaskList.equals(linkedTaskList));




    }
}
