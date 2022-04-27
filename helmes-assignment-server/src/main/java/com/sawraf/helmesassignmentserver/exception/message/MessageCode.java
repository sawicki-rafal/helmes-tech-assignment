package com.sawraf.helmesassignmentserver.exception.message;

/**
 * Message codes used in response messages for Exceptions
 */
public enum MessageCode {
    ERROR_ENTITY_NOT_FOUND("citylistapp.error.entity.notfound"),
    ERROR_VALIDATION("citylistapp.error.validation");

    private final String key;

    MessageCode(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
