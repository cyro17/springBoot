package com.cyro.journalApp.repository;

import com.cyro.journalApp.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JounralEntryRepository extends MongoRepository<JournalEntry, String> {

}
