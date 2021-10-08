package ua.sumdu.j2se.mikhailov.tasks.task8;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TaskIO {
    private static void write(AbstractTaskList tasks, OutputStream out) throws IOException {
        DataOutputStream outputStream = new DataOutputStream(out);
        outputStream.writeInt(tasks.size());
        for (Task task : tasks) {
            outputStream.writeInt(task.getTitle().length());
            outputStream.writeUTF(task.getTitle());
            outputStream.writeBoolean(task.isActive());
            outputStream.writeLong(task.getRepeatInterval());
            if (task.isRepeated()) {
                outputStream.writeLong(ZonedDateTime.of(task.getStartTime(), ZoneId.systemDefault()).toEpochSecond());
                outputStream.writeLong(ZonedDateTime.of(task.getEndTime(), ZoneId.systemDefault()).toEpochSecond());

            } else {
                outputStream.writeLong(ZonedDateTime.of(task.getTime(), ZoneId.systemDefault()).toEpochSecond());
            }
        }
        outputStream.close();
    }


    private static void read(AbstractTaskList tasks, InputStream in) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(in)) {
            int count = dataInputStream.readInt();
            for (int i = 0; i < count; i++) {
                int titleLength = dataInputStream.readInt();
                String title = dataInputStream.readUTF();
                boolean active = dataInputStream.readBoolean();
                long interval = dataInputStream.readLong();
                Task task;
                if (interval > 0) {
                    LocalDateTime startTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(dataInputStream.readLong()), ZoneId.systemDefault());
                    LocalDateTime endTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(dataInputStream.readLong()), ZoneId.systemDefault());
                    task = new Task(title, startTime, endTime, interval);
                } else {
                    LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochSecond(dataInputStream.readLong()), ZoneId.systemDefault());
                    task = new Task(title, time);
                }
                task.setActive(active);
                tasks.add(task);
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

    private static void write(AbstractTaskList tasks, Writer out) throws IOException {
        Gson gson = new Gson();
        JsonWriter jsonWriter = new JsonWriter(out);
        jsonWriter.setIndent(" ");
        jsonWriter.beginArray();
        for (Task task : tasks) {
            gson.toJson(task, Task.class, jsonWriter);
        }
        jsonWriter.endArray();
        jsonWriter.close();
    }


    private static void read(AbstractTaskList tasks, Reader in) throws IOException {
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(in);
        jsonReader.beginArray();
        while (jsonReader.hasNext()){
            Task task = gson.fromJson(jsonReader,Task.class);
            tasks.add(task);
        }
        jsonReader.endArray();
        jsonReader.close();
    }

    public static void writerText(AbstractTaskList tasks, File file) throws IOException {
        try (FileWriter fileWriter = new FileWriter(file)) {
            write(tasks, fileWriter);
        }
    }

    public static void readText(AbstractTaskList tasks, File file) throws IOException {
        try (FileReader fileReader = new FileReader(file)) {
            read(tasks, fileReader);
        }
    }
}
