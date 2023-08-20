package com.tao.digital.contoller;

import com.tao.digital.model.entity.Task;
import com.tao.digital.exception.TaskNotFoundException;
import com.tao.digital.exception.UserNotFoundException;
import com.tao.digital.model.request.TaskRequest;
import com.tao.digital.model.request.TaskStatistics;
import com.tao.digital.model.response.TaskResponse;
import com.tao.digital.service.TaskService;
import com.tao.digital.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/tasks")
@Tag(name = "Task Management")
@Validated
public class TaskController{

    private static final Logger logger = LogManager.getLogger(TaskController.class);

    TaskService taskService;
    UserService userService;



    @Operation(summary = "Create a new task")
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest){
         Task task = taskService.createTask(taskRequest);
         return ResponseEntity.ok(TaskResponse.builder().taskId(task.getTaskId()).build());
    }

    @Operation(summary = "update a task")
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long taskId, @RequestBody TaskRequest taskRequest)  {
          Task task = taskService.updateTask(taskId,taskRequest);
          return ResponseEntity.ok(TaskResponse.builder().taskId(task.getTaskId()).build());
    }

    @Operation(summary = "Delete a task")
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
        return new ResponseEntity<>("Deleted task Successfully", HttpStatus.OK);
    }

    @Operation(summary = "Get All The Tasks")
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @Operation(summary = "Assign The Task to user")
    @PostMapping("/{taskId}/assign")
    public ResponseEntity<Void> assignTask(@PathVariable Long taskId, @RequestParam Long userId) {
        taskService.assignTask(taskId, userId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Set Task Progress")
    @PostMapping("/{taskId}/progress")
    public ResponseEntity<Void> setTaskProgress(@PathVariable Long taskId, @RequestBody int progressPercentage) {
        Task task = taskService.setTaskProgress(taskId, progressPercentage);
        if(task == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get OverDue Task")
    @PostMapping("/tasks/overdue")
    public ResponseEntity<List<Task>> getOverDueTask() {
        List<Task> tasks = taskService.getOverdueTasks();
        return ResponseEntity.ok(tasks);
    }

    @Operation(summary = "Get Tasks By Status")
    @PostMapping("/status/{status}")
    public ResponseEntity<List<Task>> getTaskByStatus(@PathVariable String status) {
        List<Task> tasks = taskService.getTasksByStatus(status);
        return ResponseEntity.ok(tasks);
    }


    @Operation(summary = "Get Completed Task By Date Range")
    @PostMapping("/tasks/completed")
    public ResponseEntity<List<Task>> getCompletedTasksByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<Task> completedTasks  = taskService.getCompletedTasksByDateRange(startDate, endDate);
        return ResponseEntity.ok(completedTasks);
    }


    @Operation(summary = "Get Task Statistics")
    @PostMapping("/tasks/statistics")
    public ResponseEntity<TaskStatistics> getTasksStatistics() {
        TaskStatistics statistics = taskService.getTaskStatistics();
        return ResponseEntity.ok(statistics);
    }







}
