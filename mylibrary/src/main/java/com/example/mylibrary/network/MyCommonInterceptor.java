package com.example.mylibrary.network;

import android.util.Log;

import com.example.mylibrary.Constans;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class MyCommonInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Request.Builder builder = request.newBuilder();

        Request build = builder
                .addHeader("aaa","ddd")
                .build();

        Response proceed = chain.proceed(build);

        Log.i(Constans.LOG_I_NET, "-----------------------------------------------------------");
        Log.i(Constans.LOG_I_NET, "request");
        Log.i(Constans.LOG_I_NET, request.url().toString());
        Log.i(Constans.LOG_I_NET, request.method());
        Log.i(Constans.LOG_I_NET, request.headers().toString());

        Log.i(Constans.LOG_I_NET, "reponse");

        Log.i(Constans.LOG_I_NET, proceed.protocol().toString());
        Log.i(Constans.LOG_I_NET, proceed.message());
        Log.i(Constans.LOG_I_NET, "-----------------------------------------------------------");
        return proceed;
    }
}
