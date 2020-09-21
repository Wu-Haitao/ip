# User Guide

## 1. Features 

### 1.1. Three Types of Tasks

* To Do - *To do task with description.*
* Deadline - *Deadline task with description and its due time.*
* Event - *Event task with description and its occurring time.*

### 1.2. Modification of Task List

* Add - *Add any type of tasks into the task list.*
* Delete - *Remove a task from the task list.*
* Mark As Done - *Mark a finished task as done.*

### 1.3. Search Functions

* Search by Date - *Search tasks occurring on a specific date.*
* Search by Keyword - *Search tasks containing a keyword.*

## 2. Usage

##### Command Format

* Words in `UPPER_CASE` are the parameters to be supplied by the user e.g. in `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter which can be used as `todo task1`.  
* Some parameters need to follow certain patterns which would be explained in `(...)` e.g. for the command `deadline /by TASK_TIME(yyyy-MM-dd HH:mm)` the user should supply time in the format `yyyy-MM-dd HH:mm`.

### 2.1. `todo` - Adding To Do Task

Add a to do task with its description into the task list.  
The to do task is by default set to undone.

##### Format:

`todo TASK_DESCRIPTION`

##### Example of usage:

`todo task1`

##### Expected outcome:

`Got it. I've added this task to your list:`  
`[T][X] task1`

### 2.2. `deadline` - Adding Deadline Task

Add a deadline task with its description and due time into the task list.  
The deadline task is by default set to undone.

##### Format:

`deadline TASK_DESCRIPTION /by TASK_TIME(yyyy-MM-dd HH:mm)`

##### Example of usage:

`deadline task2 /by 2020-10-01 12:00`

##### Expected outcome:

`Got it. I've added this task to your list:`  
`[D][X] task2 (by: Oct 01 2020 12:00)`

### 2.3. `event` - Adding Event Task

Add an event task with its description and occurring time into the task list.  
The event task is by default set to undone.

##### Format:

`event TASK_DESCRIPTION /at TASK_TIME(yyyy-MM-dd HH:mm)`

##### Example of usage:

`event task3 /at 2020-10-01 12:00`

##### Expected outcome:

`Got it. I've added this task to your list:`  
`[E][X] task3 (at: Oct 01 2020 12:00)`

### 2.4. `list` - Printing Task List

Print the task list in sequence.

##### Format:

`list`

##### Example of usage:

`list`

##### Expected outcome:

`Here are the task(s) in your list:`  
`1.[T][X] task1`  
`2.[D][X] task2 (by: Oct 01 2020 12:00)`  
`3.[E][X] task3 (at: Oct 01 2020 12:00)`

### 2.5. `done` - Marking Task as Done

Mark a task as done, referenced by the index.  
Command will be ignored if the index given is out of bound.

##### Format:

`done TASK_INDEX`

##### Example of usage:

`done 1`

##### Expected outcome:

`OK! I've marked this task as done:`  
`[T][V] task1`

### 2.6. `delete` - Deleting Task

Delete a task, referenced by the index.  
Command will be ignored if the index given is out of bound.

##### Format:

`delete TASK_INDEX`

##### Example of usage:

`delete 3`

##### Expected outcome:

`OK! I've deleted the task.`  
`You have 2 tasks in the list now.`  

### 2.7. `date` - Searching by Date

Search for tasks occurring on a specific date.
Command will be ignored if the input date does not follow the defined pattern.

##### Format:

`date EXPECTED_DATE`

##### Example of usage:

`date 2020-10-01`

##### Expected outcome:

`Here are the task(s) occurring on this date:`  
`2.[D][X] task2 (by: Oct 01 12:00)`  

### 2.8. `find` - Searching by Keyword

Search for tasks with descriptions containing the keyword.

##### Format:

`find KEYWORD`

##### Example of usage:

`find task1`

##### Expected outcome:

`Here are the matching task(s) in your list:`  
`1.[T][V] task1`  

### 2.9. `bye` - Exiting the Program

Say goodbye to duke!

##### Format:
`bye`

##### Example of usage:

`bye`

##### Expected outcome:

`Bye. Hope to see you again soon!`

## 3. Command Summary

* Add:  
`todo TASK_DESCRIPTION`  
`deadline TASK_DESCRIPTION /by TASK_TIME(yyyy-MM-dd HH:mm)`  
`event TASK_DESCRIPTION /at TASK_TIME(yyyy-MM-dd HH:mm)`

* List:  
`list`

* Mark as done:  
`done TASK_INDEX`

* Delete:  
`delete TASK_INDEX`

* Search:  
`date EXPECTED_DATE(yyyy-MM-dd)`  
`find KEYWORD`

* Exit:  
`bye`