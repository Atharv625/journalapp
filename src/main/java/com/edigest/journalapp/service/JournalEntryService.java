package com.edigest.journalapp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edigest.journalapp.entity.JournalEntry;
import com.edigest.journalapp.repository.JournalEntryRepository;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    // Save or update a journal entry
    public JournalEntry saveEntry(JournalEntry journalEntry) {
        return journalEntryRepository.save(journalEntry);
    }

    // Get all journal entries
    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    // Find entry by ID
    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    // Delete entry by ID
    public void deleteById(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }
}
