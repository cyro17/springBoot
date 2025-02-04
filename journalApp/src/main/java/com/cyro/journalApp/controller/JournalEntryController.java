package com.cyro.journalApp.controller;

import com.cyro.journalApp.entity.JournalEntry;
import com.cyro.journalApp.entity.User;
import com.cyro.journalApp.service.JournalEntryService;
import com.cyro.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

//    @GetMapping("/abc")
    @GetMapping("{userName}")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        List<JournalEntry> all =  journalEntryService.getAll();
        if(all != null && !all.isEmpty()){
            return  new ResponseEntity<>(all, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public  ResponseEntity<JournalEntry> creteEntry(@RequestBody JournalEntry entry, @PathVariable String userName){
        try {
            journalEntryService.saveEntry(entry, userName);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        } catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable String id){
        Optional<JournalEntry> entry  =  journalEntryService.findById(new ObjectId(id));
        if(entry.isPresent()) return  new ResponseEntity<>(entry.get(), HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    public JournalEntry updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){
        JournalEntry oldEntry = journalEntryService.findById(id).orElse(null);
        if(oldEntry != null){
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
        }
        journalEntryService.saveEntry(oldEntry);
        return oldEntry;
    }

    @DeleteMapping("id/{userName}/{id}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId id, @PathVariable String userName){
        boolean res = journalEntryService.deleteById(id, userName);
        if(res) return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
