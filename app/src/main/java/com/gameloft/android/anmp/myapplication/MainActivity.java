package com.gameloft.android.anmp.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import com.gameloft.android.hpk.china.policy.PrivacyActivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    private final static int POLICY_REQUEST_CODE = 1114;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.util.Log.e("va-test", "MainActivity onCreate");
        //getWindow().setWindowAnimations(0);
        //Class privacyActivityClass  = Class.forName("com.gameloft.android.hpk.china.policy.PrivacyActivity");
        Intent intent = new Intent(MainActivity.this, PrivacyActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivityForResult(intent, POLICY_REQUEST_CODE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        android.util.Log.e("va-test", "MainActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        android.util.Log.e("va-test", "MainActivity onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.util.Log.e("va-test", "MainActivity onDestroy");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        android.util.Log.e("va-test", "MainActivity onActivityResult");
        if (requestCode == POLICY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                boolean agree = data.getBooleanExtra("result", false);
                android.util.Log.e("va-test", "MainActivity onActivityResult agree=" +  agree);
                if(!agree) {
                    finish();
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }

            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}