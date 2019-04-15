package com.example.mylibrary.network.listener;

/**
 * 1.请求成功
 * <p>
 * 2.错误：1.请求成功，解析错误，2.没有响应
 */
public interface OnNetListener<T> {

    void onSuccess(T t);

    void onError();

    void onComplete();


    void onStart();
}
