package com.cyb.chat.model;

import com.cyb.chat.constant.MessageType;

/**
 * @author Cyb
 */
public class Result {
    private int code;
    private MessageType messageType;
    private String message;

    public Result(int code, MessageType messageType, String message) {
        this.code = code;
        this.messageType = messageType;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
