package com.example.forum_edutopia.Service.Exeptions;

public class HasForbiddenWordException extends RuntimeException {
    public HasForbiddenWordException(String s) {
        super(s);
    }
}
