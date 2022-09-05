package com.scraping.service;

import com.scraping.domain.Search;
import com.scraping.exception.UserNotFoundException;
import com.scraping.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private SearchRepository searchRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public Search create(Search search) {
        checkValidUser(search.getUserId());
        return searchRepository.insert(search);
    }

    @Transactional
    public List<Search> findResearches(String userId) {
        return searchRepository.findByUserId(userId);
    }

    private void checkValidUser(String userId) {
        if (userService.findById(userId).isEmpty()) {
            throw new UserNotFoundException("user not found");
        }
    }
}
