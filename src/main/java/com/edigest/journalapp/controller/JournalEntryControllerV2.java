package com.edigest.journalapp.controller;

import com.edigest.journalapp.JournalApplication;
import com.edigest.journalapp.entity.JournalEntry;
import com.edigest.journalapp.service.JournalEntryService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    private final JournalApplication journalApplication;

    // In-memory storage (temporary, acts like a fake DB)

    @Autowired
    private JournalEntryService journalEntryService;

    JournalEntryControllerV2(JournalApplication journalApplication) {
        this.journalApplication = journalApplication;
    }

    // GET all journal entries
    @GetMapping
    public List<JournalEntry> getAll() {
        return journalEntryService.getAll();
    }

    // POST a new journal entry
    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now()); // ✅ correct

        return journalEntryService.saveEntry(myEntry);
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryByID(@PathVariable ObjectId myId) {
        return journalEntryService.findById(myId);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteJournalEntryByID(@PathVariable ObjectId myId) {
        journalEntryService.deleteById(myId);
        return true;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateJournalEntryByID(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        // ✅ correct
        JournalEntry old = journalEntryService.findById(id);
        if (old != null) {
            if (newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
                old.setTitle(newEntry.getTitle());
            }
            if (newEntry.getContent() != null && !newEntry.getContent().isEmpty()) {
                old.setContent(newEntry.getContent());
            }
            old.setDate(LocalDateTime.now());
            return journalEntryService.saveEntry(old);
        }
        return null;
    }

}
