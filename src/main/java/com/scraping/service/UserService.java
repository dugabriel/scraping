package com.scraping.service;

import com.scraping.domain.User;
import com.scraping.exception.UserAlreadyExistsException;
import com.scraping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    public User create(User user) {
        if (findByEmail(user.getEmail()).isEmpty()) {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setUsername(user.getEmail());
            return userRepository.insert(user);
        } else {
            throw new UserAlreadyExistsException("User already exists");
        }
    }

    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Transactional
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
