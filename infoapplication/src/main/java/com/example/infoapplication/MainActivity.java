package com.example.infoapplication;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/info/main", group = "info")
public class MainActivity extends AppCompatActivity {
    private ImageView imgView1;
    private ImageView imgView2;
    private ImageView imgView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_main);


        imgView1 = (ImageView) findViewById(R.id.img_view1);
        imgView2 = (ImageView) findViewById(R.id.img_view2);
        imgView3 = (ImageView) findViewById(R.id.img_view3);


        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.path1_animation);

        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.path2_animation);

        Animation animation3 = AnimationUtils.loadAnimation(this, R.anim.path3_animation);
        imgView1.startAnimation(animation1);
        imgView2.startAnimation(animation2);
        imgView3.startAnimation(animation3);

        //
    }
}
