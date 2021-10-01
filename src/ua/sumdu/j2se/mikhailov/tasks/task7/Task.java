package ua.sumdu.j2se.mikhailov.tasks.task7;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements Cloneable {

    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private long interval;
    private final LocalDateTime ZERO = LocalDateTime.MIN;
    private boolean active;
    private boolean repeated;

    public Task(String title, LocalDateTime time) {
        if (time.isBefore(ZERO))
            throw new IllegalArgumentException("Value must be greater than 0");

        this.title = title;
        this.time = time;
        repeated = false;
        active = true;
    }

    public Task(String title, LocalDateTime start, LocalDateTime end, long interval) {
        if (start.isBefore(ZERO) || end.isBefore(ZERO) || interval < 0)
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

    public LocalDateTime getTime() {
        if (isRepeated()) {
            return start;
        }
        return time;
    }

    public void setTime(LocalDateTime time) {
        if (time.isBefore(ZERO))
            throw new IllegalArgumentException("Time must be greater than 0");

        if (isRepeated()) {
            this.start = ZERO;
            this.interval = 0;
            this.end = ZERO;
            this.repeated = false;
        }
        this.time = time;
    }

    public LocalDateTime getStartTime() {
        if (!isRepeated()) {
            return time;
        }
        return start;
    }

    public LocalDateTime getEndTime() {
        if (!isRepeated()) {
            return time;
        }
        return end;
    }

    public long getRepeatInterval() {
        if (!isRepeated()) {
            return 0;
        }
        return interval;
    }

    public void setTime(LocalDateTime start, LocalDateTime end, long interval) {
        if (start.isBefore(ZERO) || end.isBefore(ZERO) || interval < 0)
            throw new IllegalArgumentException("Value must be greater than 0");

        if (!isRepeated()) {
            this.repeated = true;
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public boolean isRepeated() {
        return repeated;
    }

    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (current.isBefore(ZERO))
            throw new IllegalArgumentException("Current time must be greater than 0");

        if (current.isAfter(getEndTime()) || !isActive()) return null;
        else if (current.isBefore(getStartTime()) || current.isEqual(getEndTime())) return getStartTime();
        else {
            LocalDateTime tmp = getStartTime();
            while (!tmp.isAfter(current)) {
                tmp = tmp.plusHours(interval);
            }
            return tmp;
        }
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;


        Task other = (Task) obj;

        if (interval != other.interval) return false;
        if (active != other.active) return false;
        if (repeated != other.repeated) return false;
        if (!Objects.equals(title, other.title)) return false;
        if (!Objects.equals(time, other.time)) return false;
        if (!Objects.equals(start, other.start)) return false;
        return end != null ? end.equals(other.end) : other.end != null;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + (active ? 1231 : 1237);
        result = prime * result + (repeated ? 1231 : 1237);
        result = prime * result + (time != null ? time.hashCode() : 0);
        result = prime * result + (start != null ? start.hashCode() : 0);
        result = prime * result + (end != null ? end.hashCode() : 0);
        result = prime * result + (int) interval;

        return result;
    }


    @Override
    public String toString() {
        StringBuilder write = new StringBuilder("Title: ");
        write.append(this.title);
        if (isRepeated())
            write.append(", Start: ").append(this.start).append(", End: ").append(this.end).append(", Interval: ").append(this.interval);
        else
            write.append(", Time: ").append(this.time);

        return write.toString();
    }

    @Override
    public Task clone() {
        try {
            Task tmp = (Task) super.clone();
            tmp.title = getTitle();
            if (this.isRepeated()) {
                tmp.start = getStartTime();
                tmp.end = getEndTime();
                tmp.interval = getRepeatInterval();
            } else
                tmp.time = this.getTime();
            return tmp;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}