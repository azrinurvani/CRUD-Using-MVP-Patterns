package com.mobile.azrinurvani.crudusingmvppatterns.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

//    private static final String BASE_URL="http://192.168.0.132:8080/mvp_crud/";

    private static final String BASE_URL="http://192.168.60.152:8080/mvp_crud/";

    private static Retrofit retrofit;

    public static Retrofit getApiClient(){

        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
