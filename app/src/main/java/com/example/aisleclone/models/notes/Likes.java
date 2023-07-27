package com.example.aisleclone.models.notes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Likes {
    @SerializedName("profiles")
    List<Profiles> profiles;

    public List<Profiles> getProfiles() {
        return profiles;
    }
}
