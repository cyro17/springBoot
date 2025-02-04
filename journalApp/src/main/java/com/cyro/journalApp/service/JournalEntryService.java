package com.cyro.journalApp.service;

import com.cyro.journalApp.entity.JournalEntry;
import com.cyro.journalApp.entity.User;
import com.cyro.journalApp.repository.JournalEntryRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e){
            throw new RuntimeException("An Error occurred while saving the Journal Entry!!",  e);
        }
    }

    public  void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getAll(){
        return  journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return  journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try{
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(entry-> entry.getId().equals(id));
            if(removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e){
            throw new RuntimeException("An error occured while deleting journal entry: ", e);
        }
        return  removed;
    }

}
