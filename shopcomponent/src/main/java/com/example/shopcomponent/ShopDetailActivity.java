package com.example.shopcomponent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.annotationlib.TestSelfAnnotation;

@Route(path = "/shop/shopdetail", group = "shop")
public class ShopDetailActivity extends AppCompatActivity {

//
//    @TestSelfAnnotation(R.id.tv_shop_content)
//    TextView tvShopContent;
//    @TestSelfAnnotation(R.id.bottom)
//    Button bottom;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_shopdetail);
    }
}
