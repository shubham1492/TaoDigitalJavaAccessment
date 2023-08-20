package com.tao.digital.repository;

import com.tao.digital.enums.TaskStatus;
import com.tao.digital.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{

    List<Task> findByDueDateBeforeAndStatus(LocalDate currentDate, TaskStatus status);

    List<Task> findByStatus(String status);

    List<Task> findByStatusAndCompletedDateBetween(TaskStatus status, LocalDate startDate, LocalDate endDate);

    long countByStatus(TaskStatus status);
}
