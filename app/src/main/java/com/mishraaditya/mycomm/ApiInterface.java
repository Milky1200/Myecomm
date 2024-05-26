package com.mishraaditya.mycomm;

import com.mishraaditya.mycomm.ModelResponse.LoginResponse;
import com.mishraaditya.mycomm.ModelResponse.RegisterResponse;
import com.mishraaditya.mycomm.ModelResponse.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface
{
    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
     );

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );
}
