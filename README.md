# NetcrackerJava
Laboratory_Works
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
