# TaoDigitalJavaAccessment

Task Management API Documentation
This document provides an overview of the endpoints and database tables for the Task Management API.

Table of Contents
Endpoints
1. Create Task
2. Update Task
3. Delete Task
4. Get All Tasks
5. Assign Task
6. Get User's Assigned Tasks
7. Get Overdue Tasks
8. Get Tasks by Status
9. Get Completed Tasks by Date Range
10. Get Task Statistics
11. Set Task Progress
    
Database Tables
1. Task Table
2. User Table
Endpoints
1. Create Task
Endpoint: POST /api/tasks
Description: Create a new task with the provided title, description, and due date.
2. Update Task
Endpoint: PUT /api/tasks/{taskId}
Description: Update an existing task with the provided details, including the status. If the status is changed to "Completed," set the completed date.
3. Delete Task
Endpoint: DELETE /api/tasks/{taskId}
Description: Delete a task.
4. Get All Tasks
Endpoint: GET /api/tasks
Description: Get a list of all tasks.
5. Assign Task
Endpoint: POST /api/tasks/{taskId}/assign
Description: Assign a task to a specific user.
6. Get User's Assigned Tasks
Endpoint: GET /api/users/{userId}/tasks
Description: Get a list of tasks assigned to a specific user.
7. Get Overdue Tasks
Endpoint: GET /api/tasks/overdue
Description: Get a list of tasks that are overdue based on the current date and the task's due date.
8. Get Tasks by Status
Endpoint: GET /api/tasks/status/{status}
Description: Get a list of tasks with a specific status.
9. Get Completed Tasks by Date Range
Endpoint: GET /api/tasks/completed
Parameters: startDate, endDate
Description: Get a list of completed tasks within the specified date range.
10. Get Task Statistics
Endpoint: GET /api/tasks/statistics
Description: Get statistics on the total number of tasks, the number of completed tasks, and the percentage of completed tasks.
11. Set Task Progress
Endpoint: GET /api/tasks//{taskId}/progress
Description: Set the progress percentage of a task.
Database Tables
1. Task Table
The Task table stores information about tasks in the system.

Columns:
  - `task_id`: Unique identifier for the task (Primary Key).
  - `title`: Title of the task.
  - `description`: Description of the task.
  - `start_date`: Start date for the task.
  - `due_date`: Due date for the task.
  - `complete_date`: Date when the task was marked as "Completed."
  - `status`: Current status of the task (e.g., "Not Started," "In Progress," "Completed").
  - `assigned_to`: User to whom the task is assigned (Foreign Key).
  - `progress_percentage`: Percentage of task completion.

Columns:
  - `user_id`: Unique identifier for the user (Primary Key).
  - `username`: User's username.
