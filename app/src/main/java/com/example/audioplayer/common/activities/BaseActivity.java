package com.example.audioplayer.common.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initViews(savedInstanceState);
        initData();
        initAction();
    }

    public abstract int getLayoutId();

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void initData();

    protected abstract void initAction();
}
