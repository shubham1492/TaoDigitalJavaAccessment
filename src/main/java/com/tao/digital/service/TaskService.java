package com.tao.digital.service;

import com.tao.digital.database.model.Assignment;
import com.tao.digital.database.model.Task;
import com.tao.digital.database.model.User;
import com.tao.digital.database.repository.AssignmentRepository;
import com.tao.digital.database.repository.TaskRepository;
import com.tao.digital.database.repository.UserRepository;
import com.tao.digital.exception.TaskNotFoundException;
import com.tao.digital.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(Long taskId, Task updatedTask) throws TaskNotFoundException {

       Task task =  taskRepository.findById(taskId).orElseThrow( () ->
               new TaskNotFoundException("Task not found with id: " + taskId));

        if (updatedTask.getTitle() != null) {
            task.setTitle(updatedTask.getTitle());
        }
        if (updatedTask.getDescription() != null) {
            task.setDescription(updatedTask.getDescription());
        }
        if (updatedTask.getStartDate()!= null) {
            task.setStartDate(updatedTask.getStartDate());
        }
        if (updatedTask.getDueDate() != null) {
            task.setDueDate(updatedTask.getDueDate());
        }
        if (updatedTask.getStatus().equals("Completed")) {
            task.setStatus(updatedTask.getStatus());
            task.setCompletedDate(LocalDate.now());
        }

        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(Long taskId){
        taskRepository.deleteById(taskId);
    }

    @Transactional
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @Transactional
    public void assignTask(Long taskId, Long userId) throws TaskNotFoundException, UserNotFoundException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow( ()-> new TaskNotFoundException("Task with ID " + taskId + " not found"));

        User user = userRepository.findById(userId)
                .orElseThrow( () -> new UserNotFoundException("User with ID " + userId + " not found"));

        Assignment assignment = new Assignment();
        assignment.setTask(task);
        assignment.setUser(user);
        assignmentRepository.save(assignment);

    }
}
