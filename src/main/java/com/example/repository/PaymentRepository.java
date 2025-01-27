package com.example.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
import com.example.model.Payment;
import org.springframework.data.repository.query.Param;
public interface PaymentRepository  extends MongoRepository<Payment, String> {
    Optional<Payment> findBalanceByUsername(String username);
}
