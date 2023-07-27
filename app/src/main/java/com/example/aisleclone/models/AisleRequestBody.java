package com.example.aisleclone.models;

public class AisleRequestBody {

    private String number;

    private String otp;

    public AisleRequestBody(String number) {
        this.number = number;
    }

    public AisleRequestBody(String number, String otp) {
        this.number = number;
        this.otp = otp;
    }
}
