package com.example.model;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "transaction") // For MongoDB
public class Transaction{
    private String transactionId;
    private String username;
    private Double amount;
    private Double balanceAfterTransaction;
    private LocalDateTime timestamp;

    public  Transaction(){
        this.transactionId = generateTransactionId();
    }

    public String gettransactionId(){
        return transactionId;
    }

    public String generateTransactionId(){
        return UUID.randomUUID().toString();
    }

    public void setUsername(String username){
        this.username = username;
    }
    
    public String getUsername(){
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
    