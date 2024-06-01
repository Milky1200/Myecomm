package com.mishraaditya.mycomm.ServerClients;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsClient {

        private static String BASE_URL="https://dummyjson.com/";
        public static ProductsClient productsClient;
        private static Retrofit retrofit2;


        private ProductsClient(){
            retrofit2=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        //Singleton Class to check whether object is created or not.

        public static synchronized ProductsClient getInstance(){
            if(productsClient==null){
                return productsClient=new ProductsClient();
            }
            return productsClient;
        }

        public ApiProduct getProductApi(){
            return retrofit2.create(ApiProduct.class);
        }

}
