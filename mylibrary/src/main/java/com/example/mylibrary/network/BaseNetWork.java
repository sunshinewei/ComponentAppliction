package com.example.mylibrary.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseNetWork {


    public static NetWorkServices newInstance() {


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new MyCommonInterceptor())
//                .addNetworkInterceptor()
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
//                .callFactory(new UserCustomFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://api.github.com")
                .build();


        NetWorkServices netWorkServices = retrofit.create(NetWorkServices.class);
        return netWorkServices;
    }

}
