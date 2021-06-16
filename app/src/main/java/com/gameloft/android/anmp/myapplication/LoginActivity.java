package com.gameloft.android.anmp.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        android.util.Log.e("va-test", "LoginActivity onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.util.Log.e("va-test", "LoginActivity onDestroy");
    }
}