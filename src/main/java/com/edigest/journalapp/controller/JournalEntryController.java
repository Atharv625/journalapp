package com.edigest.journalapp.controller;

import com.edigest.journalapp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    // In-memory storage (temporary, acts like a fake DB)
    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    // GET all journal entries
    @GetMapping
    public List<JournalEntry> getAll() {
        return new ArrayList<>(journalEntries.values());
    }

    // POST a new journal entry
    @PostMapping
    public String createEntry(@RequestBody JournalEntry myEntry) {
        journalEntries.put(myEntry.getId(), myEntry);
        return "Journal entry created with ID: " + myEntry.getId();
    }
    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryByID(@PathVariable Long myId){
        journalEntries.get(myId);
    }
    @DeleteMapping("id/{myId}")
    public JournalEntry deleteJournalEntryByID(@PathVariable Long myId){
        journalEntries.remove(myId);
    }
    @PutMapping("/id/{id}")
    public JournalEntry updateJournalEntryByID(@PathVariable Long Id,@RequestBody myEntry){
        journalEntries.put({Id,myEntry});
    }
}
