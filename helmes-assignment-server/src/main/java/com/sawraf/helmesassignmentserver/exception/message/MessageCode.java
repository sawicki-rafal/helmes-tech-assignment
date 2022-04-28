package com.sawraf.helmesassignmentserver.exception.message;

/**
 * Message codes used in response messages for Exceptions
 */
public enum MessageCode {
    ERROR_ENTITY_NOT_FOUND("helmes.assignment.error.entity.notfound"),
    ERROR_VALIDATION("helmes.assignment.error.validation"),
    CANNOT_COMMIT_TRANSACTION("helmes.assignment.error.cannot.commit.transaction");

    private final String key;

    MessageCode(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
