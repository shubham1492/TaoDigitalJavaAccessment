package com.tao.digital.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name= "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name= "start_date")
    private LocalDate startDate;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "complete_date")
    private LocalDate completedDate;
    @Column(name = "status")
    private String status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assigned_to")
    private User assignedTo;
    @Column(name = "progress_percentage")
    private int progressPercentage;

}
