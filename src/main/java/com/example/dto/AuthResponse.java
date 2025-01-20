package com.example.dto;

public class AuthResponse{
    private String jwt;

    public AuthResponse(String jwt){
        this.jwt = jwt;
    }

    public String getJWT(){
        return jwt;
    }

    public void setJWT(String jwt){
        this.jwt = jwt;
    }
}