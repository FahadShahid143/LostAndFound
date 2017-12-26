package com.example.fahadshahid.lostandfound;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    private static int SPLAST_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedpreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
                String login = sharedpreferences.getString("Id_KEY","");

                if (login.equals("")){
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                /*Log.i("Login", "run: " + login);
                Toast.makeText(SplashActivity.this, login, Toast.LENGTH_SHORT).show();
*/




            }
        },SPLAST_TIME_OUT);
    }
}
