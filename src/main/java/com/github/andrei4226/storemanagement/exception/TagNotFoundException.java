package com.github.andrei4226.storemanagement.exception;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException(String tag) {
        super("Tag not found: " + tag);
    }
}
