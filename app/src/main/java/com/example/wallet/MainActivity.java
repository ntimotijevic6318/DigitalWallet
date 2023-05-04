package com.example.wallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static SharedPreferences sharedPreferences;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        init();
    }

    private void init() {
        initView();
    }

    private void initView() {

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


      sharedPreferences = getSharedPreferences(getPackageName() , MODE_PRIVATE);


        if(sharedPreferences.getAll().isEmpty()){
            intent = new Intent(this , LoginActivity.class);
            startActivity(intent);
        }
        else
        {
          intent = new Intent(this , BottomNavigationActivity.class);
          startActivity(intent);
        }
    }


}