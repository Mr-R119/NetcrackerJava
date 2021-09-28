package ua.sumdu.j2se.mikhailov.tasks.task7;


import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class Main {

    public static void main(String[] args) {
        Task task1 = new Task("Lunch with a beautiful girl", LocalDateTime.of(2021,8,24,16,0));
        Task task2 = new Task("Morning run", LocalDateTime.of(2021,3,1,8,15),LocalDateTime.of(2021,9,1,8,15),24);
        Task task3 = new Task("Taking medication", LocalDateTime.of(2021,8,20,8,15),LocalDateTime.of(2021,8,28,8,15),12);
        Task task4 = new Task("Meeting with friends", LocalDateTime.of(2021,9,1,18,0));

        AbstractTaskList abstractTaskList = new LinkedTaskList();
        abstractTaskList.add(task1);
        abstractTaskList.add(task2);
        abstractTaskList.add(task3);
        abstractTaskList.add(task4);

        SortedMap<LocalDateTime, Set<Task>> test =  Tasks.calendar(abstractTaskList,LocalDateTime.of(2021,8,25,8,0),LocalDateTime.of(2021,8,26,8,0));

        Set<LocalDateTime> set = test.keySet();

        for(LocalDateTime time : set){
            System.out.println(test.get(time));
        }
    }
}
