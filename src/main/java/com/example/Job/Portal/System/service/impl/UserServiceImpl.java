package com.example.Job.Portal.System.service.impl;

import com.example.Job.Portal.System.entity.User;
import com.example.Job.Portal.System.repository.UserRepository;
import com.example.Job.Portal.System.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
