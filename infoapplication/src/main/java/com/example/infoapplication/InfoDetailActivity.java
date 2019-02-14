package com.example.infoapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;


@Route(path = "/info/infodetail",group = "info")
public class InfoDetailActivity extends AppCompatActivity {
    private TextView tvInfoDetail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info_main);

        initViews();
    }



    public void initViews() {
        tvInfoDetail = (TextView) findViewById(R.id.tv_info_detail);


    }

}
