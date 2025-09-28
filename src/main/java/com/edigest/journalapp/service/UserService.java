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
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public JournalEntry saveEntry(User user) {
        return userRepository.save(user);

    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public JournalEntry findById(ObjectId id) {
        Optional<User> optionalEntry = userRepository.findById(id);
        return optionalEntry.orElse(null); // return null if not found
    }

    public void deleteById(ObjectId id) {
       userRepository.deleteById(id);
    }
}