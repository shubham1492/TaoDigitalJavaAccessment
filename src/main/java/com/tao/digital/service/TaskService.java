package com.tao.digital.service;

import com.tao.digital.enums.TaskStatus;
import com.tao.digital.model.entity.Task;
import com.tao.digital.model.entity.User;
import com.tao.digital.model.request.TaskRequest;
import com.tao.digital.model.request.TaskStatistics;
import com.tao.digital.repository.TaskRepository;
import com.tao.digital.repository.UserRepository;
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
    UserRepository userRepository;

    @Transactional
    public Task createTask(TaskRequest taskRequest){
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setDueDate(taskRequest.getDueDate());
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(Long taskId, TaskRequest updatedTask) {

       Task task =  taskRepository.findById(taskId).orElseThrow( () ->
               new TaskNotFoundException("Task not found with id: " + taskId));

        if (updatedTask.getTitle() != null) {
            task.setTitle(updatedTask.getTitle());
        }
        if (updatedTask.getDescription() != null) {
            task.setDescription(updatedTask.getDescription());
        }
       /* if (updatedTask.getStartDate()!= null) {
            task.setStartDate(updatedTask.getStartDate());
        }*/
        if (updatedTask.getDueDate() != null) {
            task.setDueDate(updatedTask.getDueDate());
        }
        if (updatedTask.getStatus().equals(TaskStatus.COMPLETED.name())) {
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
    public void assignTask(Long taskId, Long userId) {
        User user;
        Task task = taskRepository.findById(taskId)
                .orElseThrow( ()-> new TaskNotFoundException("Task with ID " + taskId + " not found"));

        try {
            user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
        }catch (UserNotFoundException e){
            user = new User();
            user.setUserId(userId);
        }
        task.setAssignedTo(user);

        taskRepository.save(task);
    }

    @Transactional
    public Task setTaskProgress(Long taskId, int progressPercentage) {
        Task task = taskRepository.findById(taskId).orElse(null);

        if (task != null) {
            if (progressPercentage >= 0 && progressPercentage <= 100) {
                task.setProgressPercentage(progressPercentage);
                return taskRepository.save(task);
            }
        }
        return null;
    }


    @Transactional
    public List<Task> getOverdueTasks() {
        LocalDate currentDate = LocalDate.now();
        return taskRepository.findByDueDateBeforeAndStatus(currentDate, TaskStatus.INCOMPLETE);
    }

    @Transactional
    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findByStatus(status);
    }

    @Transactional
    public List<Task> getCompletedTasksByDateRange(LocalDate startDate, LocalDate endDate) {
        return taskRepository.findByStatusAndCompletedDateBetween(TaskStatus.COMPLETED, startDate, endDate);
    }

    @Transactional
    public TaskStatistics getTaskStatistics() {
        long totalTasks = taskRepository.count();
        long completedTasks = taskRepository.countByStatus(TaskStatus.COMPLETED);
        double completionPercentage = (completedTasks * 100.0) / totalTasks;

        TaskStatistics statistics = new TaskStatistics();
        statistics.setTotalTasks(totalTasks);
        statistics.setCompletedTasks(completedTasks);
        statistics.setCompletionPercentage(completionPercentage);

        return statistics;
    }
}
