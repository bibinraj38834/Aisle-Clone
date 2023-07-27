package com.example.aisleclone.models.notes;

import com.google.gson.annotations.SerializedName;

public class NotesResponseModel {
    @SerializedName("invites")
    Invites invites;
    @SerializedName("likes")
    Likes likes;

    public Likes getLikes() {
        return likes;
    }

    public Invites getInvites() {
        return invites;
    }
}
