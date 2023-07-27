package com.example.aisleclone.models.notes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Invites {
    @SerializedName("profiles")
    List<Profiles> profiles;

    public List<Profiles> getProfiles() {
        return profiles;
    }
}
