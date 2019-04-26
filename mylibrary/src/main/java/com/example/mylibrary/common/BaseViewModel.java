package com.example.mylibrary.common;

import android.content.Context;

import android.databinding.BaseObservable;

import java.lang.ref.WeakReference;

public class BaseViewModel<V, M> extends BaseObservable {

    private WeakReference<V> mView;

    private M mModel;

    private Context mContext;


    public V getmView() {
        return mView != null ? mView.get() : null;
    }


    public void attachView(V view){
        mView = new WeakReference<V>(view);
    }

    public M getModel() {
        return mModel;
    }

    public void setModel(M m) {
        mModel = m;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }


    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }
}
