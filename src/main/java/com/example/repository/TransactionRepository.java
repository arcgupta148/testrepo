package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.model.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
