package com.tao.digital.model.request;

import lombok.Data;

@Data
public class TaskStatistics {

    private long totalTasks;
    private long completedTasks;
    private double completionPercentage;

}
