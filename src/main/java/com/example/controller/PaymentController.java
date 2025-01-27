package com.example.controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.example.dto.*;
import com.example.dto.AuthResponse;

import com.example.model.User;
import com.example.service.PaymentService;
import com.example.util.*;

@RestController
@RequestMapping("/api")

public class PaymentController{

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payments")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest paymentRequest){
        try{
            //System.out.println("Raw PaymentRequest: " + paymentRequest);
            paymentService.processPayment(paymentRequest);
            return ResponseEntity.ok("Payment processed successfully");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment failed : "+e.getMessage());
        }
    }


}