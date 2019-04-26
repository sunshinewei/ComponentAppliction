package com.example.mylibrary.common;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<VM extends BaseViewModel<? extends BaseView, ? extends BaseModel>, M extends BaseModel<? extends BaseViewModel>>
        extends AppCompatActivity implements BaseView, LifecycleOwner {


    protected VM mViewModel;
    protected ViewDataBinding mViewDataBinding;
    protected Context mContext;

    private FragmentTransaction mFragmentTransaction;

    public String TAG;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewDataBinding = DataBindingUtil.setContentView(this, getResLayoutId());


        mContext = this;
    }


}
