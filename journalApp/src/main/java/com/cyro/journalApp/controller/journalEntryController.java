package com.cyro.journalApp.controller;

import com.cyro.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class journalEntryController {
    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

//    @GetMapping("/abc")
    @GetMapping
    public List<JournalEntry> getAll(){
        return  new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public  boolean creteEntry(@RequestBody JournalEntry entry){
        journalEntries.put(entry.getId(), entry);
        return true;
    }

    @GetMapping("id/{postID}")
    public  JournalEntry getJournalEntryById(@PathVariable Long postID){
        return  journalEntries.get(postID);
    }
}
