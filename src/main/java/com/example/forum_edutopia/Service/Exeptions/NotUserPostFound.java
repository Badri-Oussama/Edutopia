package com.example.forum_edutopia.Service.Exeptions;

public class NotUserPostFound extends RuntimeException {
    public NotUserPostFound(String msg){
        super(msg);
    }
}
