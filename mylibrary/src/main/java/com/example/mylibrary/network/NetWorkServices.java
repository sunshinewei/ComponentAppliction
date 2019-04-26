package com.example.mylibrary.network;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetWorkServices {

    @GET("/users/{user}/repos")
    Call<List<BaseData>> listRepos(@Path("user") String user);

    @GET("/users/{user}/repos")
    Observable<List<BaseData>> listRepo(@Path("user") String user);

    @GET("/users/{user}/repos")
    Observable listRep(@Path("user") String user);
}
