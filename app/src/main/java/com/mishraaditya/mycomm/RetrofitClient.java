package com.mishraaditya.mycomm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static String BASE_URL="http://192.168.1.37/userApi/";
    private static RetrofitClient retrofitClient;
    private static Retrofit retrofit;

    private RetrofitClient(){
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //Singleton Class to check whether object is created or not.

    public static synchronized RetrofitClient getInstance(){
        if(retrofitClient==null){
            return retrofitClient=new RetrofitClient();
        }
        return retrofitClient;
    }

    public ApiInterface getApi(){
        return retrofit.create(ApiInterface.class);
    }

}
