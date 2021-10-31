package com.witi.templatemethod.util;

public class SqlTemplateException extends RuntimeException{

    public SqlTemplateException(String message) {
        super(message);
    }

    public SqlTemplateException(String message, Throwable cause) {
        super(message, cause);
    }
}
