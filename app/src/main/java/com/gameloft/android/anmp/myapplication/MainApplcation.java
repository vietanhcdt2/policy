package com.gameloft.android.anmp.myapplication;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;

public class  MainApplcation extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        android.util.Log.e("va-test", "MainApplcation creating");

    }
}
