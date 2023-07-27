package com.example.aisleclone.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

public class RequestOTPActivity extends AppCompatActivity {

    private static final long COUNTDOWN_DURATION = 60000;
    private TextView timerTextView;
    private MaterialButton otpVerifyButton;
    private MaterialProgressBar materialProgressBar;
    private EditText otpEditText;
    private TextView phoneNumberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_otpactivity);

        materialProgressBar = findViewById(R.id.loading);
        otpVerifyButton = findViewById(R.id.otp_verify);
        otpEditText = findViewById(R.id.otp);
        phoneNumberTextView = findViewById(R.id.addPhoneNumber);
        timerTextView = findViewById(R.id.timer);

        String phoneNumber = getIntent().getStringExtra("PHONE_NUMBER");

        phoneNumberTextView.setText(phoneNumber);
        startTimer();

        otpVerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (otpEditText.getText().toString() != null && !otpEditText.getText().toString().isEmpty()) {
                    if (otpEditText.getText().toString().length() == 4) {
                        sendOTP(phoneNumber, otpEditText.getText().toString());
                        materialProgressBar.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(RequestOTPActivity.this, "Please enter a valid OTP", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(RequestOTPActivity.this, "Please enter OTP to proceed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendOTP(String phoneNumber, String otp) {
        AisleRequestBody aisleRequestBody = new AisleRequestBody(phoneNumber, otp);
        NetworkAPI.getOTPResponse(aisleRequestBody, new Callback<AisleResponseModel>() {
            @Override
            public void onResponse(Call<AisleResponseModel> call, retrofit2.Response<AisleResponseModel> response) {
                if (response.isSuccessful()) {
                    materialProgressBar.setVisibility(View.GONE);
                    if (response.body() != null && response.body().getToken() != null) {
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("authToken", response.body().getToken());
                        editor.apply();

                        Intent intent = new Intent(RequestOTPActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<AisleResponseModel> call, Throwable t) {
                Toast.makeText(RequestOTPActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startTimer() {
        new CountDownTimer(COUNTDOWN_DURATION, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished / 1000;
                timerTextView.setText("00:" + secondsRemaining);
            }

            @Override
            public void onFinish() {
                timerTextView.setText("Resend");
            }
        }.start();
    }
}