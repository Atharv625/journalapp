package com.edigest.journalapp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.edigest.journalapp.entity.JournalEntry;
import com.edigest.journalapp.repository.JournalEntryRepository;;;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public JournalEntry saveEntry(JournalEntry journalEntry) {
        return journalEntryRepository.save(journalEntry);

    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry findById(ObjectId id) {
        Optional<JournalEntry> optionalEntry = journalEntryRepository.findById(id);
        return optionalEntry.orElse(null); // return null if not found
    }

    public void deleteById(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }
}