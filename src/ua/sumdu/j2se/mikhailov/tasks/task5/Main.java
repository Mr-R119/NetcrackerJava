package ua.sumdu.j2se.mikhailov.tasks.task5;


public class Main {


    public static void main(String[] args) {
        Task task1 = new Task("Task1", 10);
        Task task2 = new Task("Task2", 0, 120, 10);
        Task task3 = new Task("Task3", 15);
        Task task4 = new Task("Task4", 0, 120, 10);


        ArrayTaskList arrayTaskList = new ArrayTaskList();

//      Добавляем в список задачи
        arrayTaskList.add(task1);
        arrayTaskList.add(task2);
        arrayTaskList.add(task3);
        arrayTaskList.add(task4);

        for (int i = 0; i < arrayTaskList.size(); i++) {
            System.out.println(arrayTaskList.getTask(i));
        }

        System.out.println("\n\nКлонирование:");

//      Клонирование
        ArrayTaskList arrayTaskList1 = (ArrayTaskList) arrayTaskList.clone();
        arrayTaskList1.getTask(1).setTitle("1111");
        arrayTaskList1.getTask(1).setTime(15);
        System.out.println(arrayTaskList);
        System.out.println("Копия: " + arrayTaskList1);


        for (int i = 0; i < arrayTaskList.size(); i++) {
            System.out.println(arrayTaskList.getTask(i));
            System.out.println("Копия: " + arrayTaskList1.getTask(i));
        }


//        arrayTaskList1.add(task1);
//        arrayTaskList1.add(task2);
//        arrayTaskList1.add(task3);
//        arrayTaskList1.add(task4);


        AbstractTaskList linkedTaskList = TaskListFactory.createTaskList(ListTypes.LINKED);


        linkedTaskList.add(task1);
        linkedTaskList.add(task2);
        linkedTaskList.add(task3);
        linkedTaskList.add(task4);


        linkedTaskList.remove(task1);

//        Проверка метода equals()
//        System.out.println(arrayTaskList.equals(arrayTaskList1));
//        System.out.println(arrayTaskList.equals(linkedTaskList));




    }
}
