package com.restful.restfulwebservices.helloworld;

public class HelloWorldBean {
    private String message;
    public HelloWorldBean(String message) {
        this.message=message;
    }

    // if we dont write the getter then we would have get the internal server error
    // and message like this No converter found for return type
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
