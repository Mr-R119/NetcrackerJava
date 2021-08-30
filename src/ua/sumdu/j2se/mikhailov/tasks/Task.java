package ua.sumdu.j2se.mikhailov.tasks;

public class Task {

    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean repeated;

    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        this.repeated = false;
    }

    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeated = true;
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
        if (isRepeated()) {
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
            return 0;
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
        if (isActive()) {
            if (!isRepeated()) {
                if (current > time) return -1;
                return time;
            } else {
                if (current > end || current < start) {
                    return -1;
                } else if (current == end || current == start) {
                    return current;
                } else {
                    if (current <= interval) {
                        return start + interval;
                    } else {
                        int next = current / interval + 1;
                        return start + next * interval;
                    }
                }
            }
        } else return -1;
    }
}
