package com.example.mylibrary.network;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

public class NetWorkImp implements NetWorkServices {


    @Override
    public Call<List<BaseData>> listRepos(String user) {
        return null;
    }

    @Override
    public Observable<List<BaseData>> listRepo(String user) {

        return this.listRepo("octocat")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
