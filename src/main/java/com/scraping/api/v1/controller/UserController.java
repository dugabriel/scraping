package com.scraping.api.v1.controller;

import com.scraping.api.v1.dto.SignupRequestDTO;
import com.scraping.api.v1.mapper.Mapper;
import com.scraping.domain.User;
import com.scraping.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("UserControllerV1")
@RequestMapping(RestPath.BASE_PATH + "/user")
public class UserController {

    @Autowired
    private Mapper mapper;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody SignupRequestDTO signupRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.create(mapper.map(signupRequest, User.class)));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findAll());
    }
}
