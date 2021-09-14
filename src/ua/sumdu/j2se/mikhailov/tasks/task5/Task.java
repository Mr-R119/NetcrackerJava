package ua.sumdu.j2se.mikhailov.tasks.task5;

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

    public boolean isRepeated() {
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

    @Override
    public boolean equals(Object obj)
    {

        if (this == obj)
        return true;

        if (obj == null || getClass() != obj.getClass())
            return false;


        Task other = (Task) obj;
        if(time != other.time)
            return false;

        if (!title.equals(other.title) || start != other.start || end != other.end)
            return false;

        if (active != other.active ||  repeated != other.repeated || interval != other.interval)
            return false;

        return true;
    }


    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + (active ? 1231 : 1237);
        result = prime * result + (repeated ? 1231 : 1237);
        result = prime * result + time;
        result = prime * result + start;
        result = prime * result + end;
        result = prime * result + interval;

        return result;
    }


    @Override
    public String toString(){
        String write;
        if(isRepeated())
            write = ", Start: " + this.start + ", End: " + this.end + ", Interval: " + this.interval;
        else
            write = ", Time: " + this.time;

        return "Title: " + this.title + write;
    }

    @Override
    public Task clone() {
        try {
            Task tmp = (Task) super.clone();
            tmp.title = this.title;
            if (this.isRepeated()) {
                tmp.start = this.start;
                tmp.end = this.end;
                tmp.interval = this.interval;
            } else
                tmp.time = this.time;
            return tmp;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}