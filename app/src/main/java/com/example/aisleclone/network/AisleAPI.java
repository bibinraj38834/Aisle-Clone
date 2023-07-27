package com.example.aisleclone.network;

import com.example.aisleclone.models.AisleRequestBody;
import com.example.aisleclone.models.AisleResponseModel;
import com.example.aisleclone.models.notes.NotesResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AisleAPI {
    @POST("users/phone_number_login")
    Call<AisleResponseModel> getPhoneNumberResponse(@Body AisleRequestBody aisleRequestBody);

    @POST("users/verify_otp")
    Call<AisleResponseModel> getOTPResponse(@Body AisleRequestBody aisleRequestBody);

    @GET("users/test_profile_list")
    Call<NotesResponseModel> getNotesResponse();
}
