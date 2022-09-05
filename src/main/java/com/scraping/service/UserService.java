package com.scraping.service;

import com.scraping.domain.User;
import com.scraping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User create(User user) {
        return userRepository.insert(user);
    }

    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
