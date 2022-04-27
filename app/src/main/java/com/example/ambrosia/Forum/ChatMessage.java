package com.example.ambrosia.Forum;

import java.util.Date;

public class ChatMessage {

    private String messageText;
    private String pseudo;
    private long messageTime;

    public ChatMessage(String messageText, String messageUser) {
        this.messageText = messageText;
        this.pseudo = messageUser;

        // Initialize to current time
        messageTime = new Date().getTime();
    }

    public ChatMessage(){

    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return pseudo;
    }

    public void setMessageUser(String messageUser) {
        this.pseudo = messageUser;
    }

    public String getMessageTime() {
        return String.valueOf(messageTime);
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}