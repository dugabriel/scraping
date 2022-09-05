package com.scraping.repository;

import com.scraping.domain.Search;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends MongoRepository<Search, String> {
}
