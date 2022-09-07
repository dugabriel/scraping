package com.scraping.exception;

public class FreemarkerTemplateErrorException extends RuntimeException {
    public FreemarkerTemplateErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
