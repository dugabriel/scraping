package com.scraping.service;

import com.scraping.domain.Search;
import com.scraping.domain.User;
import com.scraping.repository.SearchRepository;
import com.scraping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SearchService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SearchRepository searchRepository;

    @Transactional
    public Search create(Search search) {
        return searchRepository.insert(search);
    }
}
