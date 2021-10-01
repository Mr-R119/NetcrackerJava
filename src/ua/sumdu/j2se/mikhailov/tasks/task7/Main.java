package ua.sumdu.j2se.mikhailov.tasks.task7;


import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class Main {

    private static final LocalDateTime START = LocalDateTime.of(2021, 8, 25, 8, 0);
    private static final LocalDateTime END = LocalDateTime.of(2021, 8, 26, 8, 0);


    public static void main(String[] args) {
        Task task1 = new Task("Lunch with a beautiful girl", LocalDateTime.of(2021, 8, 24, 16, 0));
        Task task2 = new Task("Morning run", LocalDateTime.of(2021, 3, 1, 8, 15), LocalDateTime.of(2021, 9, 1, 8, 15), 24);
        Task task3 = new Task("Taking medication", LocalDateTime.of(2021, 8, 20, 8, 15), LocalDateTime.of(2021, 8, 28, 8, 15), 12);
        Task task4 = new Task("Meeting with friends", LocalDateTime.of(2021, 9, 1, 18, 0));

        AbstractTaskList abstractTaskList = new ArrayTaskList();
        abstractTaskList.add(task1);
        abstractTaskList.add(task2);
        abstractTaskList.add(task3);
        abstractTaskList.add(task4);

        SortedMap<LocalDateTime, Set<Task>> test = Tasks.calendar(abstractTaskList, START, END);

        for (Map.Entry<LocalDateTime, Set<Task>> entry : test.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
