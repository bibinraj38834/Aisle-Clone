package com.example.aisleclone.network;
import android.content.Context;

import com.example.aisleclone.utils.SharedPrefsUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static final String BASE_URL = "https://app.aisle.co/V1/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(Context context) {
        if (retrofit == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            String authToken = SharedPrefsUtils.getAuthToken(context);

            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            Request originalRequest = chain.request();

                            Request.Builder builder = originalRequest.newBuilder();
                            if (authToken != null && !authToken.isEmpty()) {
                                builder.header("User-Agent", "Aisle Clone/1.0")
                                        .addHeader("Authorization", authToken);
                            } else {
                                builder.header("User-Agent", "Aisle Clone/1.0");
                            }
                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
