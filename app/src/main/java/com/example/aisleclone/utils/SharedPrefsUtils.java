package com.example.aisleclone.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsUtils {
    public static String getAuthToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getString("authToken", "");
    }
}
