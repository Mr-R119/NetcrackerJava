package ua.sumdu.j2se.mikhailov.tasks.task9;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Tasks {

    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime from, LocalDateTime to) {
        if (to.isBefore(LocalDateTime.MIN)) {
            throw new IllegalArgumentException("Time must be greater than 0");
        }

        return ((AbstractTaskList) tasks).getStream().filter(task -> task.nextTimeAfter(from) != null).
                filter(task -> (!task.nextTimeAfter(from).isAfter(to))).
                collect(Collectors.toList());
    }


    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        TreeMap<LocalDateTime, Set<Task>> calendar = new TreeMap<>();
        Iterable<Task> inc = incoming(tasks, start, end);
        for (Task task : inc) {
            LocalDateTime localDateTime = task.nextTimeAfter(start);
            while (localDateTime != null && localDateTime.isBefore(end)) {
                if (calendar.containsKey(localDateTime)) {
                    calendar.get(localDateTime).add(task);
                } else {
                    Set<Task> taskSet = new HashSet<>();
                    taskSet.add(task);
                    calendar.put(localDateTime, taskSet);
                }
                if(task.isRepeated()) {
                    localDateTime = task.nextTimeAfter(localDateTime);
                } else break;
            }
        }
        return calendar;
    }
}
