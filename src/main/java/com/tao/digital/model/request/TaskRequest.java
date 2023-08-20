package com.tao.digital.model.request;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {

    private String title;
    private String description;
    private LocalDate dueDate;
    private String status;
}
