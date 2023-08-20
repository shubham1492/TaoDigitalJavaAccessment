package com.tao.digital.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {

    @Id
    private Long userId;
    private String userName;

    @OneToMany(mappedBy = "assignedTo")
    private List<Task> tasks;

}
