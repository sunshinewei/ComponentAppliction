package com.example.mylibrary.network.listener;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class MyBaseObserver<T> implements Observer<T> {

    private OnNetListenerImp mOnNetListener;

    public MyBaseObserver(OnNetListenerImp mOnNetListener) {
        this.mOnNetListener = mOnNetListener;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        if (mOnNetListener != null) {
            mOnNetListener.onSuccess(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (mOnNetListener != null) {
            mOnNetListener.onError();
        }
    }

    @Override
    public void onComplete() {

    }
}
