package com.tao.digital.contoller;

import com.tao.digital.model.entity.Task;
import com.tao.digital.exception.TaskNotFoundException;
import com.tao.digital.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/users")
public class UserController {

    UserService userService;

    @Operation(summary = "Get User's Assigned Tasks")
    @GetMapping("/{userId}/tasks")
    public ResponseEntity<List<Task>> getUserAssignedTasks(@PathVariable Long userId) {
        List<Task> assignedTasks = userService.getUserAssignedTasks(userId);
        return ResponseEntity.ok(assignedTasks);
    }



}
