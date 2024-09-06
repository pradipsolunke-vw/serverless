package com.example.mybookingapp.dto;

public class Request {

    public String username;

    public String auth;

    public Object header;

    public Object body;

    public String getUsername() {
        return username;
    }

    public String getAuth() {
        return auth;
    }

    public Object getHeader() {
        return header;
    }

    public Object getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Request{" +
                "auth='" + auth + '\'' +
                ", header=" + header +
                ", body=" + body +
                '}';
    }
}
