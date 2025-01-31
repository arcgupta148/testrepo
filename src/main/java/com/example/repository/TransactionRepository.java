package com.example.repository;

import java.util.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.model.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByUsername(String username);
}
