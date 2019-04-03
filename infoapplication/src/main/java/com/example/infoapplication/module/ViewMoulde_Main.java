package com.example.infoapplication.module;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;


import com.example.infoapplication.entity.UserInfoEntity;
import com.example.mylibrary.network.BaseData;
import com.example.mylibrary.network.BaseNetWork;
import com.example.mylibrary.network.NetWorkServices;
import com.example.mylibrary.network.listener.MyBaseObserver;
import com.example.mylibrary.network.listener.OnNetListenerImp;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewMoulde_Main extends ViewModel {

    MutableLiveData mutableLiveData = new MutableLiveData();

    public MutableLiveData<UserInfoEntity> getMainInfo() {

        return getUserRxJava2Info();
    }

    public MutableLiveData<UserInfoEntity> getUserRxJava2Info() {
        BaseNetWork.newInstance().listRepo("octocat")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyBaseObserver<>(new OnNetListenerImp<List<BaseData>>() {
                    @Override
                    public void onSuccess(List<BaseData> baseData) {
                        UserInfoEntity userInfoEntity = new UserInfoEntity();
                        userInfoEntity.setSex(baseData.size() + "");
                        userInfoEntity.setUserName(baseData.toString());
                        mutableLiveData.setValue(userInfoEntity);
                    }
                }));
        return mutableLiveData;
    }


    public MutableLiveData<UserInfoEntity> getUserInfo() {

        NetWorkServices netWorkServices = BaseNetWork.newInstance();

        netWorkServices.listRepos("octocat").enqueue(new Callback<List<BaseData>>() {
            @Override
            public void onResponse(Call<List<BaseData>> call, Response<List<BaseData>> response) {
                UserInfoEntity userInfoEntity = new UserInfoEntity();
                List<BaseData> body = response.body();
                userInfoEntity.setSex(body.size() + "");
                userInfoEntity.setUserName(body.toString());
                mutableLiveData.setValue(userInfoEntity);
            }

            @Override
            public void onFailure(Call<List<BaseData>> call, Throwable t) {
//                t.fillInStackTrace();
                UserInfoEntity userInfoEntity = new UserInfoEntity();
                userInfoEntity.setSex("sad");
                userInfoEntity.setUserName("as");
                mutableLiveData.postValue(userInfoEntity);
                Log.e("asadasaaaa", "dasdasdas" + t.getMessage());
            }
        });

        return mutableLiveData;

    }
}
