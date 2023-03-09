package com.example.forum_edutopia.Service.Exeptions;

public class PostDoesNotExistsException extends RuntimeException {
    public PostDoesNotExistsException(String m) {
        super(m);
    }
}
