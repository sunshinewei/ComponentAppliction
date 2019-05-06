package com.example.infoapplication.module;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;


import com.example.infoapplication.entity.UserInfoEntity;
import com.example.mylibrary.loadingdialog.view.LoadingDialog;
import com.example.mylibrary.network.NetWorkImp;
import com.example.mylibrary.network.listener.MyBaseObserver;


public class ViewMoulde_Main extends ViewModel {

    MutableLiveData mutableLiveData = new MutableLiveData();

    public MutableLiveData<UserInfoEntity> getMainInfo(Context context) {

        return getUserRxJava2Info(context);
    }

    //pos zb vsys
    LoadingDialog loadingDialog;

    public MutableLiveData<UserInfoEntity> getUserRxJava2Info(Context context) {
        NetWorkImp.listRepo("octocat")
                .subscribe(new MyBaseObserver<>(
                                () -> {
                                    loadingDialog = new LoadingDialog(context)
                                            .setLoadingText("加载中");
                                    loadingDialog.show();
                                },
                                baseData -> {
                                    UserInfoEntity userInfoEntity = new UserInfoEntity();
                                    userInfoEntity.setSex(baseData.size() + "");
                                    userInfoEntity.setUserName(baseData.toString());
                                    mutableLiveData.setValue(userInfoEntity);
                                    loadingDialog
                                            .loadSuccess();
                                },
                                msg -> {
                                    Log.e("错误信息", msg);
                                    loadingDialog.setLoadingText(msg)
                                            .loadFailed();
                                }
                        )
                );
        return mutableLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

}
