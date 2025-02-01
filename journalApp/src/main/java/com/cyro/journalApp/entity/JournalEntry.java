package com.cyro.journalApp.entity;

public class JournalEntry {

    private long id;
    private  String title;
    private  String content;

    public long getId(){
        return  this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
