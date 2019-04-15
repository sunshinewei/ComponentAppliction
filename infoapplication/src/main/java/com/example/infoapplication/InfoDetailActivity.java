package com.example.infoapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.infoapplication.view.EnergyLayout;
import com.example.infoapplication.view.EnergyShowView;


@Route(path = "/info/infodetail", group = "info")
public class InfoDetailActivity extends AppCompatActivity {

    @Autowired
    public String id;
    @Autowired
    public String name;


    private TextView tvInfoDetail;
    private EnergyLayout lay;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info_infodetail);

        ARouter.getInstance().inject(this);

        initViews();  //11*310

        // 6  47


    }


    public void initViews() {
        tvInfoDetail = (TextView) findViewById(R.id.tv_info_detail);

        tvInfoDetail.setText("获取到的数据为：" + id + "        " + name);

        lay = (EnergyLayout) findViewById(R.id.lay);

        lay.setAddEnergyListView(3);

    }

}
