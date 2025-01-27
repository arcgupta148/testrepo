package com.example.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.Optional;
import com.example.util.JwtUtil;
import com.example.model.User;
import com.example.model.Payment;
import com.example.model.Transaction;
import  com.example.repository.UserRepository;
import com.example.repository.PaymentRepository;
import com.example.repository.TransactionRepository;
import com.example.exception.InvalidCredentialsException;
import com.example.dto.PaymentRequest;
import java.time.*;
@Service
public class PaymentService{
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PaymentRepository paymentRepository;

    @Autowired
    private final TransactionRepository transactionRepository;
    String status="";
        // Constructor injection (preferred)
    public PaymentService(UserRepository userRepository, PaymentRepository paymentRepository,TransactionRepository transactionRepositor) {
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
        this.transactionRepository=transactionRepositor;
    }

    //private final TransactionRepository transactionRepository;
    // @Transactional
    public void processPayment(PaymentRequest paymentRequest){
        System.out.println("Payment request username is "+paymentRequest.getUsername() +" and the amount is "+paymentRequest.getAmount());
        if (paymentRequest == null || paymentRequest.getUsername() == null || paymentRequest.getAmount() == null) {
            throw new RuntimeException("Invalid payment request");
        }
        System.out.println("Payment user id is " +paymentRequest.getUsername());
        User user = userRepository.findByUsername(paymentRequest.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("User in payment is "+user);
        Payment payments = paymentRepository.findBalanceByUsername(paymentRequest.getUsername()).orElseThrow(() -> new RuntimeException("Balance not found"));
        double balance = payments.getAmount();
        System.out.println("Balance for the user is " +balance);
        if (balance<paymentRequest.getAmount()){
            status="Failed";
            throw new RuntimeException("Insufficient balance");
        }
        //Payment payment = new Payment();
        status = "Success";
        payments.setAmount(balance-paymentRequest.getAmount());
        payments.setStatus(status);
        paymentRepository.save(payments);

        Transaction transaction = new Transaction();
        transaction.setUsername(paymentRequest.getUsername());
        transaction.setAmount(paymentRequest.getAmount());
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

}