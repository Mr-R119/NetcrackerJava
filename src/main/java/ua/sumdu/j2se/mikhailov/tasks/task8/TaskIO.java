package ua.sumdu.j2se.mikhailov.tasks.task8;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TaskIO {
    public static void write(AbstractTaskList tasks, OutputStream out) throws IOException {
        DataOutputStream outputStream = new DataOutputStream(out);
        int size = tasks.size();
        try {
            outputStream.write((new Integer(size)).toString().getBytes(StandardCharsets.UTF_8));
            for (Task task : tasks) {
                outputStream.write("\n".getBytes(StandardCharsets.UTF_8));
                outputStream.write(task.getTitle().getBytes(StandardCharsets.UTF_8));
                outputStream.write("\n".getBytes(StandardCharsets.UTF_8));
                outputStream.write(Boolean.toString(task.isActive()).getBytes(StandardCharsets.UTF_8));
                outputStream.write("\n".getBytes(StandardCharsets.UTF_8));
                outputStream.write(Long.toString(task.getRepeatInterval()).getBytes(StandardCharsets.UTF_8));
                outputStream.write("\n".getBytes(StandardCharsets.UTF_8));
                outputStream.write(Long.toString(ZonedDateTime.of(task.getStartTime(), ZoneId.systemDefault()).toEpochSecond()).getBytes(StandardCharsets.UTF_8));
                if (task.isRepeated()) {
//                    outputStream.write("\n".getBytes(StandardCharsets.UTF_8));
//                    outputStream.write(task.getStartTime().toString().getBytes(StandardCharsets.UTF_8));
                    outputStream.write("\n".getBytes(StandardCharsets.UTF_8));
                    outputStream.write(Long.toString(ZonedDateTime.of(task.getEndTime(), ZoneId.systemDefault()).toEpochSecond()).getBytes(StandardCharsets.UTF_8));
//                    outputStream.write("\n".getBytes(StandardCharsets.UTF_8));
                }
//                else {
//                    outputStream.write(Long.toString(ZonedDateTime.of(task.getTime(), ZoneId.systemDefault()).toEpochSecond()).getBytes(StandardCharsets.UTF_8));
//                    outputStream.write("\n".getBytes(StandardCharsets.UTF_8));
//                }
            }
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(in)) {
            int count = dataInputStream.readInt();
            for (int i = 0; i < count; i++) {
                String title = dataInputStream.readUTF();
                boolean active = dataInputStream.readBoolean();
                long interval = dataInputStream.readLong();
                LocalDateTime startTime = Instant.ofEpochSecond(dataInputStream.readLong()).atZone(ZoneId.systemDefault()).toLocalDateTime();
                Task task;
                if (interval > 0) {
                    LocalDateTime endTime = Instant.ofEpochSecond(dataInputStream.readLong()).atZone(ZoneId.systemDefault()).toLocalDateTime();
                    task = new Task(title, startTime, endTime, interval);
                } else {
                    task = new Task(title, startTime);
                }
                task.setActive(active);
                tasks.add(task);
                i++;
            }
        } catch (EOFException e) {
            e.printStackTrace();
        }
    }


    public static void writeBinary(AbstractTaskList tasks, File file) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            write(tasks, outputStream);
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            read(tasks, inputStream);
        }
    }
}
