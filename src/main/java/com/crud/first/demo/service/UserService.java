package com.crud.first.demo.service;

import com.crud.first.demo.entity.User;
import com.crud.first.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findByStatusNot((byte) -1);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findByIdAndStatusNot(id, (byte) -1);
    }

    public User updateUser(Long id, User userInfo) {
        return userRepository.findById(id).map(user -> {
            user.setName(userInfo.getName());
            user.setEmail(userInfo.getEmail());
            return userRepository.save(user);
        }).orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setStatus((byte)-1);
            userRepository.save(user);
        });
    }
}
