package com.example.mm.componentappliction

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter

@Route(path = "/main/module", group = "main")
class MainActivity : AppCompatActivity() {

    private var mTvInfo: TextView? = null
    private var mTvShop: TextView? = null
    private var tvInfodetail: TextView? = null
    private var tvShopdetail: TextView? = null
    fun initViews() {
        mTvInfo = findViewById<TextView>(R.id.tv_info)
        mTvShop = findViewById<TextView>(R.id.tv_shop)
        tvInfodetail = findViewById<TextView>(R.id.tv_infodetail)
        tvShopdetail = findViewById<TextView>(R.id.tv_shopdetail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initViews()
        // Example of a call to a native method
//        tv_info.text = stringFromJNI()

        mTvInfo?.setOnClickListener {


            //            Toast.makeText(this, "aaaaaaaaaaaa", Toast.LENGTH_LONG).show()

            ARouter.getInstance().build("/info/main", "info").navigation()
        }

        mTvShop?.setOnClickListener {

            //            Toast.makeText(this, "bbbbbbbbbbbbb", Toast.LENGTH_LONG).show()
            ARouter.getInstance().build("/shop/main", "shop").navigation()
        }

        tvInfodetail?.setOnClickListener {

            ARouter.getInstance().build("/info/infodetail", "info")
                    .withString("id", "111111111")
                    .withString("name", "hello world")
                    .navigation()

        }

        tvShopdetail!!.setOnClickListener {
            ARouter.getInstance()
                    .build("/info/infodetail", "shop")
                    .navigation()
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    external fun stringFromJNI(): String
//
//    companion object {
//
//        // Used to load the 'native-lib' library on application startup.
//        init {
//            System.loadLibrary("native-lib")
//        }
//    }
}
