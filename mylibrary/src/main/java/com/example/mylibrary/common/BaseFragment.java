package com.example.mylibrary.common;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseFragment<VM extends BaseViewModel<? extends BaseView, ? extends BaseModel>, M extends BaseModel<? extends BaseViewModel>>
        extends Fragment implements BaseView {
}
