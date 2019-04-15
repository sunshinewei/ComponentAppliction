package com.example.mylibrary.network.listener;

import com.example.mylibrary.MyLibraryAppliction;
import com.example.mylibrary.loadingdialog.view.LoadingDialog;

public abstract class OnNetListenerImp<T> implements OnNetListener<T> {

    @Override
    public void onComplete() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onStart() {

//        new LoadingDialog(MyLibraryAppliction.getAppliction)
//                .setLoadingText("aaaaaa")
//                .show();
    }
}
