package com.example.Job.Portal.System.service;

import com.example.Job.Portal.System.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String email);

    User save(User user);

    boolean existsByEmail(String email);
}
