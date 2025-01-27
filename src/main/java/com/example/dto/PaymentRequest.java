package com.example.dto;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class PaymentRequest {

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.1", message = "Amount must be greater than 0")
    private Double amount;

    @NotNull(message = "User Name is required")
    private String username;

    private LocalDateTime timestamp; // Optional: Only if required by your use case.

    // Default Constructor
    public PaymentRequest() {}

    // Getters and Setters
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
