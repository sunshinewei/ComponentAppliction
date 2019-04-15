package com.example.infoapplication;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.infoapplication.databinding.ActivityInfoMainBinding;
import com.example.infoapplication.entity.UserInfoEntity;
import com.example.infoapplication.module.ViewMoulde_Main;
import com.example.mylibrary.loadingdialog.view.LoadingDialog;

@Route(path = "/info/main", group = "info")
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_info_main);

        ActivityInfoMainBinding activityInfoMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_info_main);


        activityInfoMainBinding.setLifecycleOwner(this);

        MutableLiveData<UserInfoEntity> mainInfo = ViewModelProviders.of(this).get(ViewMoulde_Main.class).getMainInfo();

//        activityInfoMainBinding.setInfoMain(mainInfo.getValue());




        mainInfo.observe(this, new Observer<UserInfoEntity>() {
            @Override
            public void onChanged(@Nullable UserInfoEntity userInfoEntity) {

                activityInfoMainBinding.setInfoMain(mainInfo.getValue());
            }
        });


    }
}
