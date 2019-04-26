package com.example.mylibrary.common;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.BaseObservable;

public abstract class BaseModel<VM extends BaseViewModel> extends ViewModel {


    private VM mViewModel;

    private Context mContext;


    public VM getmViewModel() {
        return mViewModel;
    }


    public void attachViewModel(VM viewModel) {
        mViewModel = viewModel;
    }

    public void setContext(Context context) {
        mContext = context.getApplicationContext();
    }

    public Context getContext() {
        return mContext;
    }

}
