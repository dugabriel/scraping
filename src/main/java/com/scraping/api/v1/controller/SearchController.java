package com.scraping.api.v1.controller;

import com.scraping.api.v1.dto.SearchDTO;
import com.scraping.api.v1.mapper.Mapper;
import com.scraping.domain.Search;
import com.scraping.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("SearchControllerV1")
@RequestMapping(RestPath.BASE_PATH + "/search")
public class SearchController {

    @Autowired
    private Mapper mapper;

    @Autowired
    private SearchService searchService;

    @PostMapping
    public ResponseEntity<Search> createSearch(@Valid @RequestBody SearchDTO searchDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(searchService.create(mapper.map(searchDTO, Search.class)));
    }
}
