package com.example.mylibrary.network;

import com.example.mylibrary.network.listener.MyBaseObserver;
import com.example.mylibrary.network.listener.OnNetListenerImp;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

public class NetWorkImp {


    public Call<List<BaseData>> listRepos(String user) {
        return null;
    }


    public static Observable<List<BaseData>> listRepo(String user) {


        BaseNetWork.newInstance().listRepo(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return null;

    }
}
