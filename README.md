# NetcrackerJava
Laboratory_Works
Practical No.1 Project Structure
TASK 1.  CREATING A BASE CLASS
Create a Task class in the ua.sumdu.j2se.studentName.tasks package (replace studentName with your name) using the following public methods:
●	The Task (String title, int time) constructor constructs an inactive task to run at a specified time without repeating with a given name. 
●	The Task (String title, int start, int end, int interval) constructor constructs an inactive task to run within the specified time range (including the start and the end time) with the set repetition interval and with a given name. 
●	Methods for reading and setting the task name: String getTitle(), void setTitle(String title).
●	Methods for reading and setting the task status: boolean isActive(), void setActive(boolean active).
●	Methods for reading and changing execution time for non-repetitive tasks: 
o	int getTime() – if the task is a repetitive one, the method must return the start time of the repetition;
o	 void setTime(int time) – if the task was a repetitive one, it should become non-repetitive.
●	Methods for reading and changing execution time for repetitive tasks: 
o	int getStartTime() – if the task is a non-repetitive one, the method must return the start time of the execution;
o	int getEndTime() – if the task is a non-repetitive one, the method must return the end time of the execution;
o	int getRepeatInterval() – if the task is a non-repetitive one, the method must return 0;
o	void setTime(int start, int end, int interval) – if the task is a non-repetitive one, it should become repetitive.
●	To check the task for repeatability use the boolean isRepeated() method.
TASK 2. CHECKING THE NEXT TASK EXECUTION
You should add the int nextTimeAfter (int current) method to the Task class that returns the next start time of the task execution after the current time. If after the specified time the task is not executed anymore, the method must return -1.

Practical No.2 Arrays and Links
TASK 1. CREATING A LIST BASED ON AN ARRAY
Create an ArrayTaskList class in the ua.sumdu.j2se.studentName.tasks package (replace studentName with your name) using the following public methods:
●	void add (Task task) is a method that adds the specified task to the list.
●	boolean remove (Task task)is a method that removes a task from the list and returns true, if such a task was in the list. If the list contains several tasks of the same type, any of them should be removed.
●	int size() is a method that returns several tasks from the list.
●	Task getTask(int index) is a method that returns a task which takes the specified place in the list; the index of the first task is 0.
Tasks in the list should be stored using an array. The list can contain any number of tasks that can be in the array, but should not occupy much more space than it is required at any particular moment. This means that, for example, if the list contains 5 tasks, then the array for their storage should not occupy space for 100 tasks.
TASK 2. SELECTION OF TASKS
Besides, the application should know which tasks from the list are scheduled at least once in a certain interval, for example, which tasks are scheduled for the next week. To implement this, create the ArrayTaskList incoming(int from, int to) method in the ArrayTaskList class. This method returns a subset of tasks that are scheduled for execution at least once after the "from" time, and not later than the "to" time.