package com.edigest.journalapp.controller;

import com.edigest.journalapp.JournalApplication;
import com.edigest.journalapp.entity.JournalEntry;
import com.edigest.journalapp.service.JournalEntryService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private final JournalApplication journalApplication;

    // In-memory storage (temporary, acts like a fake DB)

    @Autowired
    private JournalEntryService journalEntryService;

    JournalEntryController(JournalApplication journalApplication) {
        this.journalApplication = journalApplication;
    }

    // GET all journal entries
    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAll() {
        List<JournalEntry> all = journalEntryService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // POST a new journal entry
    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
        try {
            myEntry.setDate(LocalDateTime.now()); // ✅ correct
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryByID(@PathVariable ObjectId myId) {
        // return journalEntryService.findById(myId);
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")

    public ResponseEntity<Void> deleteJournalEntryByID(@PathVariable ObjectId myId) {
        Optional<JournalEntry> entry = journalEntryService.findById(myId);
        if (entry.isPresent()) {
            journalEntryService.deleteById(myId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntry> updateJournalEntryByID(@PathVariable ObjectId id,
            @RequestBody JournalEntry newEntry) {
        Optional<JournalEntry> optionalOld = journalEntryService.findById(id);
        if (optionalOld.isPresent()) {
            JournalEntry old = optionalOld.get();

            if (newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
                old.setTitle(newEntry.getTitle());
            }
            if (newEntry.getContent() != null && !newEntry.getContent().isEmpty()) {
                old.setContent(newEntry.getContent());
            }

            old.setDate(LocalDateTime.now());
            JournalEntry updated = journalEntryService.saveEntry(old);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
