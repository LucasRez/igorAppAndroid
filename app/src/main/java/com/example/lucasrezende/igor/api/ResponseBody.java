package com.example.lucasrezende.igor.api;

import com.example.lucasrezende.igor.model.User;

/**
 * Created by vzaffalon on 09/09/17.
 */

public class ResponseBody {
    private String status;
    private User data;
    private String errors;

    public ResponseBody(String status, User data, String errors) {
        this.status = status;
        this.data = data;
        this.errors = errors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }


}
