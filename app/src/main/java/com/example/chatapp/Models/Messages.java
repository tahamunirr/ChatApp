package com.example.chatapp.Models;

public class Messages {
    String uId, message , messageId;
    Long time;

    public Messages(String uId, String message, Long time) {
        this.uId = uId;
        this.message = message;
        this.time = time;
    }

    public Messages(String uId, String message) {
        this.uId = uId;
        this.message = message;
    }

    public Messages(){}

    public String getuId() {
        return uId;
    }


    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
