package com.example.fahadshahid.lostandfound;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fahadshahid.lostandfound.Service.ServiceGenerator;
import com.example.fahadshahid.lostandfound.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


        private static final String TAG = "MTAG";
        private static final String MYPREFERENCE = "Login";
        private static final String ID_KEY = "Id_KEY";
        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            final EditText email = (EditText) findViewById(R.id.email);
            final EditText password = (EditText) findViewById(R.id.password);
            Button button = (Button) findViewById(R.id.login);
            final SharedPreferences sharedpreferences = getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String mail = email.getText().toString().trim();
                    String pass = password.getText().toString().trim();

                    UserDetail service = new ServiceGenerator().createService(UserDetail.class);
                    Call<User> user = service.login(mail, pass);
                    user.enqueue(new Callback<User>() {

                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");

                            int id = response.body().getUserId();
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putInt(ID_KEY, id);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            editor.apply();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                            Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            });
        }
    }

