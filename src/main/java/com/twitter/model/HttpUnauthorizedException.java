package com.twitter.model;

public class HttpUnauthorizedException extends Exception {

    public String errorMessage;

    public String infoMessage;

    public HttpUnauthorizedException (String errorMessage, String infoMessage) {
        this.errorMessage = errorMessage;
        this.infoMessage = infoMessage;
    }

    @Override
    public String toString() {
        return "HttpUnauthorizedException{" +
                "errorMessage='" + errorMessage + '\'' +
                ", infoMessage='" + infoMessage + '\'' +
                '}';
    }
}
