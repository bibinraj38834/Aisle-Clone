package com.example.aisleclone.models.notes;

import com.google.gson.annotations.SerializedName;

public class Photos {
    @SerializedName("photo")
    String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }
}
