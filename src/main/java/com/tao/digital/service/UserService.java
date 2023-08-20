package com.tao.digital.service;

import com.tao.digital.exception.UserNotFoundException;
import com.tao.digital.model.entity.Task;
import com.tao.digital.model.entity.User;
import com.tao.digital.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public List<Task> getUserAssignedTasks(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get().getTasks();
        } else {
            throw new UserNotFoundException("User with ID " + userId + " not found.");
        }
    }
}
