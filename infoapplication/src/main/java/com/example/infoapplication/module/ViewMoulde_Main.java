package com.example.infoapplication.module;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


import com.example.infoapplication.entity.UserInfoEntity;
import com.example.mylibrary.loadingdialog.view.LoadingDialog;
import com.example.mylibrary.network.BaseData;
import com.example.mylibrary.network.BaseNetWork;
import com.example.mylibrary.network.NetWorkImp;
import com.example.mylibrary.network.listener.MyBaseObserver;
import com.example.mylibrary.network.listener.OnNetListenerImp;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ViewMoulde_Main extends ViewModel {

    MutableLiveData mutableLiveData = new MutableLiveData();

    public MutableLiveData<UserInfoEntity> getMainInfo() {

        return getUserRxJava2Info();
    }

    public MutableLiveData<UserInfoEntity> getUserRxJava2Info() {


        NetWorkImp.listRepo("octocat")
                .subscribe(new MyBaseObserver<>(new OnNetListenerImp<List<BaseData>>() {

                    @Override
                    public void onSuccess(List<BaseData> baseData) {
                        UserInfoEntity userInfoEntity = new UserInfoEntity();
                        userInfoEntity.setSex(baseData.size() + "");
                        userInfoEntity.setUserName(baseData.toString());
                        mutableLiveData.setValue(userInfoEntity);
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                    }
                }));


//        BaseNetWork.newInstance().listRepo("octocat")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new MyBaseObserver<>(new OnNetListenerImp<List<BaseData>>() {
//                    @Override
//                    public void onSuccess(List<BaseData> baseData) {
//                        UserInfoEntity userInfoEntity = new UserInfoEntity();
//                        userInfoEntity.setSex(baseData.size() + "");
//                        userInfoEntity.setUserName(baseData.toString());
//                        mutableLiveData.setValue(userInfoEntity);
//                    }
//                }));
        return mutableLiveData;
    }
}
