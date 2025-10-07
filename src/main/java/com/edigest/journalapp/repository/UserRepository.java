package com.edigest.journalapp.repository;
// import com.edigest.journalapp.entity.User;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.edigest.journalapp.entity.User; // ✅ must match User.java package

public interface UserRepository extends MongoRepository<User, String> {
    User findByUserName(String username);
}
