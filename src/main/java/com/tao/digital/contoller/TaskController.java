package com.tao.digital.contoller;

import com.tao.digital.database.model.Task;
import com.tao.digital.dto.response.ApiResponse;
import com.tao.digital.exception.TaskNotFoundException;
import com.tao.digital.exception.UserNotFoundException;
import com.tao.digital.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/tasks")
@Tag(name = "Task Management")
@Validated
public class TaskController{

    @Autowired
    TaskService taskService;

    @Operation(summary = "Create a new task")
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
         return ResponseEntity.ok(taskService.createTask(task));
    }

    @Operation(summary = "update a task")
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody @Valid Task task) throws TaskNotFoundException {
          Task updatedTask = taskService.updateTask(taskId,task);
          return ResponseEntity.ok(updatedTask);
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
    public ResponseEntity<Void> assignTask(@RequestParam Long taskId, @RequestParam Long userId) throws UserNotFoundException, TaskNotFoundException {
        taskService.assignTask(taskId, userId);
        return ResponseEntity.ok().build();
    }




}
