package com.twitter.model;

public class ErrorPageModel {

    private String errorMessage;

    private String infoMessage;

    public ErrorPageModel(String errorMessage, String infoMessage) {
        this.errorMessage = errorMessage;
        this.infoMessage = infoMessage;
    }

    @Override
    public String toString() {
        return "ErrorPageModel{" +
                "errorMessage='" + errorMessage + '\'' +
                ", infoMessage='" + infoMessage + '\'' +
                '}';
    }
}
