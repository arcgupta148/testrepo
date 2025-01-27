package com.example.model;
import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "transaction") // For MongoDB
public class Transaction{
    private String username;
    private Double amount;
    private Double balanceAfterTransaction;
    private LocalDateTime timestamp;

    public  Transaction(){}

    public void setUsername(String username){
        this.username = username;
    }
    
    public String getUsername(String username){
        return username;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(Double balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
    