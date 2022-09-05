package com.scraping.api.v1.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private final ModelMapper modelMapper;

    public Mapper() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public <T> T map(Object object, Class<T> clazz) {
        return modelMapper.map(object, clazz);
    }
}
