package com.kmj.sunrinton19;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    String myName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getPreferences();
        if (myName.equals("")){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);
        }

        // 값 저장하기
//        private void savePreferences(){
//            SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
//            SharedPreferences.Editor editor = pref.edit();
//            editor.putString("hi", "인사잘하네");
//            editor.commit();
//        }


    }
    private void getPreferences(){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        myName=pref.getString("myName", "");
    }
}
