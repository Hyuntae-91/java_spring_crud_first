package com.crud.first.demo.service;

import com.crud.first.demo.dto.user.UserRequestDTO;
import com.crud.first.demo.dto.user.UserResponseDTO;
import com.crud.first.demo.entity.User;
import com.crud.first.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        User user = new User(requestDTO.getName(), requestDTO.getEmail());
        User savedUser = userRepository.save(user);
        return new UserResponseDTO(savedUser);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findByStatusNot((byte) -1)
                .stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<UserResponseDTO> getUserById(Long id) {
        return userRepository.findByIdAndStatusNot(id, (byte) -1)
                .map(UserResponseDTO::new);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO) {
        Optional<User> optionalUser = userRepository.findByIdAndStatusNot(id, (byte) -1);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(requestDTO.getName());
            user.setEmail(requestDTO.getEmail());
            User updated = userRepository.save(user);
            return new UserResponseDTO(updated);
        } else {
            return null;
        }
    }

    public void deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findByIdAndStatusNot(id, (byte) -1);
        optionalUser.ifPresent(user -> {
            user.setStatus((byte) -1);
            userRepository.save(user);
        });
    }
}
