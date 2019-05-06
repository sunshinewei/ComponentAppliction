package com.example.shopcomponent;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.annotationlib.TestSelfAnnotation;
import com.example.mylibrary.HelpAnnotation;

import java.lang.annotation.Documented;

@Route(path = "/shop/main", group = "shop")
public class MainActivity extends AppCompatActivity {


//    @TestSelfAnnotation(R.id.tv_button)
//    TextView tv_button;
//
//
//    @TestSelfAnnotation(R.id.bt_ok)
//    Button bt_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_main);
        HelpAnnotation.inject(this);

    }
}

