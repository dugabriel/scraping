package com.scraping.domain;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
public abstract class BaseEntity {

    @CreatedDate
    protected LocalDateTime createAt;

    @LastModifiedDate
    protected LocalDateTime updatedAt;
}
