package ua.sumdu.j2se.mikhailov.tasks.task8;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) throws IOException {
        Task task1 = new Task("Lunch with a beautiful girl", LocalDateTime.of(2021, 8, 24, 16, 0));
        Task task2 = new Task("Morning run", LocalDateTime.of(2021, 3, 1, 8, 15), LocalDateTime.of(2021, 9, 1, 8, 15), 24);
        Task task3 = new Task("Taking medication", LocalDateTime.of(2021, 8, 20, 8, 15), LocalDateTime.of(2021, 8, 28, 8, 15), 12);
        Task task4 = new Task("Meeting with friends", LocalDateTime.of(2021, 9, 1, 18, 0));

        AbstractTaskList abstractTaskList = new ArrayTaskList();
        abstractTaskList.add(task1);
        abstractTaskList.add(task2);
        abstractTaskList.add(task3);
        abstractTaskList.add(task4);

        AbstractTaskList list = new ArrayTaskList();

        //Reading and writing a byte stream
//        TaskIO.writeBinary(abstractTaskList, new File("out.txt"));
//        TaskIO.readBinary(list,new File("out.txt"));


        //Reading and writing a character stream
        TaskIO.writerText(abstractTaskList, new File("out2.json"));
        TaskIO.readText(list, new File("out2.json"));

        System.out.println(list);
    }
}
