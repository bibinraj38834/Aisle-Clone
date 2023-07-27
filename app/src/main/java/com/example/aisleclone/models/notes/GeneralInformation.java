package com.example.aisleclone.models.notes;

import com.google.gson.annotations.SerializedName;

public class GeneralInformation {
    @SerializedName("first_name")
    String firstName;
    @SerializedName("age")
    String age;

    public String getFirstName() {
        return firstName;
    }

    public String getAge() {
        return age;
    }
}
