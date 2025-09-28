package com.edigest.journalapp.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "journalEntries")
public class JournalEntry {

    @Id
    private ObjectId id;

    private String title;
    private String content;
    private LocalDateTime date;

    // Optional: reference to user
    private ObjectId userId;
}
