package com.example.mylibrary.network.listener;


import com.google.gson.JsonParseException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


public class MyBaseObserver<T> implements Observer<T> {


    private OnSuccessNetImp<T> onSuccessNetImp;

    private OnFailedNetImp onFailedNetImp;

    private OnCompleteImp onCompleteImp;

    private OnStartResponseImp onStartResponseImp;

    public MyBaseObserver(OnStartResponseImp onStartResponseImp, OnSuccessNetImp<T> onSuccessNetImp, OnFailedNetImp onFailedNetImp, OnCompleteImp onCompleteImp) {

        this.onStartResponseImp = onStartResponseImp;
        this.onSuccessNetImp = onSuccessNetImp;
        this.onFailedNetImp = onFailedNetImp;
        this.onCompleteImp = onCompleteImp;
    }


    /**
     * 开始，成功，失败
     *
     * @param onSuccessNetImp
     * @param onFailedNetImp
     */
    public MyBaseObserver(OnStartResponseImp onStartResponseImp, OnSuccessNetImp<T> onSuccessNetImp, OnFailedNetImp onFailedNetImp) {

        this(onStartResponseImp, onSuccessNetImp, onFailedNetImp, null);
    }


    /**
     * 开始，失败，完成
     *
     * @param onStartResponseImp
     * @param onFailedNetImp
     * @param onCompleteImp
     */
    public MyBaseObserver(OnStartResponseImp onStartResponseImp, OnFailedNetImp onFailedNetImp, OnCompleteImp onCompleteImp) {
        this(onStartResponseImp, null, onFailedNetImp, onCompleteImp);
    }

    /**
     * 失败，完成
     *
     * @param onFailedNetImp
     * @param onCompleteImp
     */
    public MyBaseObserver(OnFailedNetImp onFailedNetImp, OnCompleteImp onCompleteImp) {
        this(null, null, onFailedNetImp, onCompleteImp);
    }


    /**
     * 成功
     *
     * @param onSuccessNetImp
     */
    public MyBaseObserver(OnSuccessNetImp<T> onSuccessNetImp) {
        this(null, onSuccessNetImp, null, null);
    }


    @Override
    public void onSubscribe(Disposable d) {
        if (onStartResponseImp != null) {
            onStartResponseImp.startResponse();
        }
    }

    @Override
    public void onNext(T t) {
        if (onSuccessNetImp != null) {
            onSuccessNetImp.successResponse(t);
        }
    }

    @Override
    public void onError(Throwable e) {

        String msg;
        if (onFailedNetImp != null) {
            if (e instanceof HttpException) {
                HttpException httpException = (HttpException) e;
                msg = httpException.message();
            } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
                msg = "解析错误";
            } else if (e instanceof ConnectException) {
                msg = "连接失败";
            } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
                msg = "证书验证失败";
            } else if (e instanceof ConnectTimeoutException) {
                msg = "连接超时";
            } else if (e instanceof java.net.SocketTimeoutException) {
                msg = "连接超时";
            } else {
                msg = "未知错误";
            }
            onFailedNetImp.onFailResponse(msg);
        }
    }

    @Override
    public void onComplete() {
        if (onCompleteImp != null) {
            onCompleteImp.onCompleteResponse();
        }
    }
}
