package com.cyro.journalApp.entity;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
public class JournalEntry {

    private ObjectId id;
    private  String title;
    private  String content;
    private LocalDateTime date;

    public ObjectId getId(){
        return  this.id;
    }

    public void setId(ObjectId id) {
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

    public void setDate(LocalDateTime date){
        this.date = date;
    }

    public LocalDateTime getDate() {
        return this.date;
    }
}
