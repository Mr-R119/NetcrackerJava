package ua.sumdu.j2se.mikhailov.tasks.task9;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class Controller {

    Logger logger = Logger.getLogger(Controller.class);

    AbstractTaskList tasks = TaskListFactory.createTaskList(ListTypes.LINKED);
    Scanner scanner = new Scanner(System.in);

    final File file = new File("Data.txt");

    public void startApplication() throws IOException {
        System.out.println("Start application!");
        logger.info("Logged in to the system");
        TaskIO.readText(tasks, file);
        if (tasks.size() > 0) {
            viewNotifications();
        }
        menu();
    }

    private void menu() {
        System.out.println("Main menu. \nSelect number an action");
        System.out.println("1. Create new task");
        System.out.println("2. Delete task");
        System.out.println("3. Change parameters of the task");
        System.out.println("4. View tasks");
        System.out.println("5. View calendar");
        System.out.println("0. Exit");
        String num = scanner.nextLine();
        switch (num) {
            case "1":
                createTask();
                menu();
                break;
            case "2":
                deleteTask(taskByIndex());
                menu();
                break;
            case "3":
                editTask(taskByIndex());
                menu();
                break;
            case "4":
                System.out.println(tasks + "\n");
                menu();
                break;
            case "5":
                viewCalendar(tasks);
                menu();
                break;
            case "0":
                exit();
                break;
            default:
                System.out.println("Enter the correct data of execution according to the instructions");
                menu();
                break;
        }
    }

    private void createTask() {
        System.out.println("Is the task repetitive? 0 or 1.");
        System.out.println("0. No, the task is not repeated");
        System.out.println("1. Yes, the task is repeated");
        String num = scanner.nextLine();
        if (num.equals("0")) {
            tasks.add(createTaskNoRepeated());
        } else if (num.equals("1")) {
            tasks.add(createTaskRepeated());
        } else {
            System.out.println("Wrong answer. You need to answer 0 or 1");
            createTask();
        }

        logger.info("Task created");
        menu();
    }

    private Task createTaskNoRepeated() {
        try {
            System.out.println("Enter the name of the task");
            String title = scanner.nextLine();
            System.out.println("Time");
            LocalDateTime time = time();
            return new Task(title, time);
        } catch (Exception e) {
            logger.error(e);
            createTaskRepeated();
        }
        return null;
    }

    private Task createTaskRepeated() {
        System.out.println("Enter the name of the task");
        String title = scanner.nextLine();
        System.out.println("Start time");
        LocalDateTime start = time();
        System.out.println("End time");
        LocalDateTime end = time();
        System.out.println("Enter the interval in hours");
        long interval = Integer.parseInt(scanner.nextLine());
        if(start.isBefore(end)) {
            return new Task(title, start, end, interval);
        }
        else {
            System.out.println("The start date and time must be earlier than the end");
            createTaskRepeated();
        }
        return null;
}

//    private LocalDateTime time() {
//        try {
//            int year, month, day,hour, minute;
//            System.out.println("Enter the year.");
//            int num = Integer.parseInt(scanner.nextLine());
//            if(num >= LocalDateTime.MIN.getYear() && num <= LocalDateTime.MAX.getYear())
//            {
//                year = num;
//            }
//            System.out.println("Enter the number month. From 1 to 12");
//            int month = Integer.parseInt(scanner.nextLine());
//            System.out.println("Enter the number day from 1 to 28/31");
//            int day = Integer.parseInt(scanner.nextLine());
//            System.out.println("Enter an hour from 0 to 23");
//            int hour = Integer.parseInt(scanner.nextLine());
//            System.out.println("Enter minutes from 0 to 59");
//            int minute = Integer.parseInt(scanner.nextLine());
//            return LocalDateTime.of(year, month, day, hour, minute);
//        } catch (Exception e) {
//            System.out.println("Enter the correct data of execution according to the instructions");
//            logger.error(e);
//            time();
//        }
//        return time();
//    }

//    private LocalDate date(String line) {
//        String[] lines = line.split("/");
//        if (lines.length == 3) {
//            int year = Integer.parseInt(lines[0]);
//            int month = Integer.parseInt(lines[1]);
//            int day = Integer.parseInt(lines[2]);
//            return LocalDate.of(year, month, day);
//        } else {
//            System.out.println("Enter the correct data of execution according to the instructions");
//            dateTime();
//        }
//        return null;
//    }
//
//    private LocalTime time(String line) {
//        String[] lines = line.split(":");
//        if (lines.length == 2) {
//            int hour = Integer.parseInt(lines[0]);
//            int minute = Integer.parseInt(lines[1]);
//            return LocalTime.of(hour, minute);
//        } else {
//            System.out.println("Enter the correct data of execution according to the instructions");
//        }
//        return null
//    }

    private LocalDateTime time() {
        try {
            System.out.println("Enter date and time. Example: 10/10/2021 10:00");
            String datetime = scanner.nextLine();
            return LocalDateTime.parse(datetime, Task.FORMATTER);
        } catch (Exception e) {
            System.out.println("Enter the correct data of execution according to the instructions");
            logger.error(e);
        }
        return time();
    }

    private void deleteTask(Task task) {
        tasks.remove(task);

        logger.info(task.getTitle() + " deleted");
    }

    private void editTask(Task task) {
        System.out.println("Select number of the parameter to change");
        System.out.println("1. Change the title");
        System.out.println("2. Change the time");
        System.out.println("3. Change activity");
        System.out.println("0. Back to menu");
        switch (scanner.nextLine()) {
            case "1":
                System.out.println("Enter a new task title");
                task.setTitle(scanner.nextLine());

                logger.info("The title has been changed in the task");
                editTask(task);
                break;
            case "2":
                setTimeOfTask(task);
                editTask(task);
                break;
            case "3":
                System.out.println("Enter true or false");
                setActiveOfTask(task);
                editTask(task);
                break;
            case "0":
                menu();
                break;
            default:
                System.out.println("Enter the correct data of execution according to the instructions");
                editTask(task);
        }

    }

    private void setActiveOfTask(Task task) {
        String bool = scanner.nextLine();
        if (bool.equalsIgnoreCase("true") || bool.equalsIgnoreCase("false")) {
            task.setActive(Boolean.parseBoolean(bool));
            logger.info("The time has been changed in the task " + task.getTitle());
        } else {
            System.out.println("Enter the correct data of execution according to the instructions");
            logger.error("Incorrect data entered " + task.getTitle());
            setActiveOfTask(task);
        }
    }

    private void setTimeOfTask(Task task) {
        System.out.println("Will the task be repeated?");
        System.out.println("1. Yes, the task will be repeated");
        System.out.println("2. Yes, the task will not be repeated");
        switch (scanner.nextLine()) {
            case "1":
                setTimeOfRepeatedTask(task);
                break;
            case "2":
                setTimeOfNoRepeatedTask(task);
                break;
            default:
                System.out.println("Enter the correct data of execution according to the instructions");
        }
    }

    private void setTimeOfNoRepeatedTask(Task task) {
        task.setTime(time());

        logger.info("The time has been changed in the task " + task.getTitle());
    }

    private void setTimeOfRepeatedTask(Task task) {
        System.out.println("Start time");
        LocalDateTime start = time();
        System.out.println("End time");
        LocalDateTime end = time();
        if(start.isBefore(end)) {
            System.out.println("Enter the interval in hours");
            long interval = scanner.nextInt();
            task.setTime(start, end, interval);
        }
        else {
            System.out.println("The start date and time must be earlier than the end");
            createTaskRepeated();
        }
        logger.info("The time has been changed in the task " + task.getTitle());
    }


    private Task taskByIndex() {
        System.out.println("Select a task by number from the list. \nEnter a number that is in the range from 1 to " + tasks.size());
        int index = scanner.nextInt();
        if (index > 0 && index <= tasks.size()) {
            return tasks.getTask(index - 1);
        } else {
            System.out.println("Enter a number that is in the range from 1 to " + tasks.size());
            logger.error("Couldn't find a list item");

            taskByIndex();
        }
        return null;
    }


    private void viewCalendar(AbstractTaskList taskList) {
        System.out.println("Enter beginning of the interval");
        LocalDateTime start = time();
        System.out.println("Enter end of the interval");
        LocalDateTime end = time();

        SortedMap<LocalDateTime, Set<Task>> map = Tasks.calendar(taskList, start, end);

        for (Map.Entry<LocalDateTime, Set<Task>> entry : map.entrySet()) {
            System.out.println("Time: " + entry.getKey().format(Task.FORMATTER) + " - Task: " + entry.getValue());
        }
    }

    private void exit() {
        try {
            TaskIO.writerText(tasks, file);

            logger.info("The data is saved. Log out of the system");
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    private void viewNotifications() {
        Map<LocalDateTime, String> dateTimeStringMap = new TreeMap<>();
        for (Task task : tasks) {
            LocalDateTime dateTime = task.nextTimeAfter(LocalDateTime.now());
            if (dateTime != null) {
                dateTimeStringMap.put(dateTime, task.getTitle());
            }

            for (Map.Entry<LocalDateTime, String> entry : dateTimeStringMap.entrySet()) {
                System.out.println("Next: " + entry.getValue() + " - execution: " + entry.getKey().format(Task.FORMATTER));
            }
        }
    }
}
