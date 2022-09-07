package com.scraping.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class MailConfig {

    @Value("${spring.mail.username}")
    private String from;
}
