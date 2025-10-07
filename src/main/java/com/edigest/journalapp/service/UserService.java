package com.edigest.journalapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edigest.journalapp.entity.User;
import com.edigest.journalapp.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Save or update a user
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Find a user by username
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    // Delete user by ID
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
