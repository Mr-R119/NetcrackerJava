package ua.sumdu.j2se.mikhailov.tasks.task3;

public class Task {

    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean repeated;

    public Task(String title, int time) {
        if (time < 0)
            throw new IllegalArgumentException("Value must be greater than 0");

        this.title = title;
        this.time = time;
        repeated = false;
        active = true;
    }

    public Task(String title, int start, int end, int interval) {
        if (start < 0 || end < 0 || interval < 0)
            throw new IllegalArgumentException("Value must be greater than 0");

        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeated = true;
        this.active = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTime() {
        if (isRepeated()) {
            return start;
        }
        return time;
    }

    public void setTime(int time) {
        if (time < 0)
            throw new IllegalArgumentException("Time must be greater than 0");

        if (isRepeated()) {
            this.start = 0;
            this.interval = 0;
            this.end = 0;
            this.repeated = false;
        }
        this.time = time;
    }

    public int getStartTime() {
        if (!isRepeated()) {
            return time;
        }
        return start;
    }

    public int getEndTime() {
        if (!isRepeated()) {
            return time;
        }
        return end;
    }

    public int getRepeatInterval() {
        if (!isRepeated()) {
            return 0;
        }
        return interval;
    }

    public void setTime(int start, int end, int interval) {
        if (start < 0 || end < 0 || interval < 0)
            throw new IllegalArgumentException("Value must be greater than 0");

        if (!isRepeated()) {
            this.repeated = true;
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    private boolean isRepeated() {
        return repeated;
    }

    public int nextTimeAfter(int current) {
        if (current < 0)
            throw new IllegalArgumentException("Current time must be greater than 0");

        if (current >= getEndTime() || !isActive()) return -1;
        else if (current < getStartTime()) return getStartTime();
        else {
            int tmp = getStartTime();
            for (; tmp < getEndTime(); tmp += interval) {
                if (tmp > current)
                    break;
            }
            return tmp;
        }
    }
}