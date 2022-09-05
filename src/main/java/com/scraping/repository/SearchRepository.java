package com.scraping.repository;

import com.scraping.domain.Search;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends MongoRepository<Search, String> {
    List<Search> findByUserId(String userId);
}
