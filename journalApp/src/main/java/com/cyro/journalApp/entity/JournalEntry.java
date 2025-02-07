package com.cyro.journalApp.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "journal_entries")

@Data
public class JournalEntry {
    private ObjectId id;
    private  String title;
    private  String content;
    private LocalDateTime date;

}
