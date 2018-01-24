package com.sf.orderfoodserver.model.firebase;

/**
 * Created by mesutgenc on 24.01.2018.
 */

public class Notification {
    private String body;
    private String title;

    public Notification() {
    }

    public Notification(String body, String title) {
        this.body = body;
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}