package com.example.aisleclone.models;

import com.google.gson.annotations.SerializedName;

public class AisleResponseModel {


    @SerializedName("status")
    private String status;

    @SerializedName("token")
    private String token;

    public String getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }
}
