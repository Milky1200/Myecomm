package com.mishraaditya.mycomm.ServerClients;

import retrofit2.Call;
import retrofit2.http.GET;
public interface ApiProduct {
    @GET("products")
    Call<ProductResponse> getProds();

}
