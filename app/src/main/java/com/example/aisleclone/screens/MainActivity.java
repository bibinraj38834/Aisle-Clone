package com.example.aisleclone.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aisleclone.R;
import com.example.aisleclone.models.AisleRequestBody;
import com.example.aisleclone.models.AisleResponseModel;
import com.example.aisleclone.network.NetworkAPI;
import com.google.android.material.button.MaterialButton;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private MaterialButton phoneNumberVerifyButton;
    private MaterialProgressBar materialProgressBar;
    private EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetworkAPI.init(this);

        setContentView(R.layout.activity_main);
        materialProgressBar = findViewById(R.id.loading);
        phoneNumberVerifyButton = findViewById(R.id.phone_number_verify);
        phoneNumber = findViewById(R.id.phone_number);

        phoneNumberVerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!phoneNumber.getText().toString().isEmpty()) {
                    if (phoneNumber.getText().toString().length() == 10) {
                        sendPhoneNumber("+91" + phoneNumber.getText().toString());
                        materialProgressBar.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(MainActivity.this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void sendPhoneNumber(String phoneNumber) {
        AisleRequestBody aisleRequestBody = new AisleRequestBody(phoneNumber);
        NetworkAPI.getPhoneNumberResponse(aisleRequestBody, new Callback<AisleResponseModel>() {
            @Override
            public void onResponse(Call<AisleResponseModel> call, retrofit2.Response<AisleResponseModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getStatus().equals("true")) {
                        Intent intent = new Intent(MainActivity.this, RequestOTPActivity.class);
                        intent.putExtra("PHONE_NUMBER", phoneNumber);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<AisleResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}