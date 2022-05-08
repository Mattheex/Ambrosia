package com.example.ambrosia.Forums;

import java.util.Date;

public class ChatMessage {

    private String messageText;
    private String messageUser;
    private String messageTime;
    private String photo = null;

    public ChatMessage(String messageText, String messageUser,String photo) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.photo=photo;
        messageTime = String.valueOf(new Date().getTime());
    }


    public ChatMessage(String messageText, String messageUser) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        messageTime =String.valueOf(new Date().getTime());
    }

    public ChatMessage(){

    }

    public String getPhoto() {
        return photo;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public String getMessageTime() {
        return String.valueOf(messageTime);
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    @Override
    public String toString() {
        return messageText;
    }
}