package com.example.aisleclone.network;

import android.content.Context;

import com.example.aisleclone.models.AisleRequestBody;
import com.example.aisleclone.models.AisleResponseModel;
import com.example.aisleclone.models.notes.NotesResponseModel;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class NetworkAPI {

    private static final String TAG = "NetworkAPI";

    private static final int TIMEOUT_SECONDS = 30;
    private static OkHttpClient okHttpClient;
    private static AisleAPI aisleAPI;

    public static void init(Context context) {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = APIClient.getRetrofitInstance(context);
        aisleAPI = retrofit.create(AisleAPI.class);
    }

    public static void getPhoneNumberResponse(AisleRequestBody aisleRequestBody, Callback<AisleResponseModel> callback) {
        Call<AisleResponseModel> call = aisleAPI.getPhoneNumberResponse(aisleRequestBody);
        call.enqueue(callback);
    }

    public static void getOTPResponse(AisleRequestBody aisleRequestBody, Callback<AisleResponseModel> callback) {
        Call<AisleResponseModel> call = aisleAPI.getOTPResponse(aisleRequestBody);
        call.enqueue(callback);
    }

    public static void getNotesResponse(Callback<NotesResponseModel> callback) {
        Call<NotesResponseModel> call = aisleAPI.getNotesResponse();
        call.enqueue(callback);
    }
}
