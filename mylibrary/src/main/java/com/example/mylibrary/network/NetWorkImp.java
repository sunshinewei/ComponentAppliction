package com.example.mylibrary.network;

import android.annotation.SuppressLint;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NetWorkImp {


    @SuppressLint("CheckResult")
    public static Observable<List<BaseData>> listRepo(String user) {

        return BaseNetWork.newInstance().listRepo(user)
                .compose(new BaseObservableTransformer<>());

    }
}


class BaseObservableTransformer<T> implements ObservableTransformer<T, T> {

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
