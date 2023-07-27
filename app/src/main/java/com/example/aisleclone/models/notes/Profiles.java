package com.example.aisleclone.models.notes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Profiles {
    @SerializedName("first_name")
    String firstName;
    @SerializedName("avatar")
    String avatarUrl;
    @SerializedName("general_information")
    GeneralInformation generalInformation;

    @SerializedName("photos")
    List<Photos> photos;

    public String getFirstName() {
        return firstName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public GeneralInformation getGeneralInformation() {
        return generalInformation;
    }

    public List<Photos> getPhotos() {
        return photos;
    }
}
