package com.example.fahadshahid.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fahadshahid.lostandfound.models.Lost;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final EditText item_name = (EditText) this.findViewById(R.id.etLost_item_name);
        final EditText item_description = (EditText) this.findViewById(R.id.etLost_item_desription);
        final EditText lost_date = (EditText) this.findViewById(R.id.etLost_date);
        final EditText person_Name = (EditText) this.findViewById(R.id.etPerson_Name);
        final EditText person_PhoneNo = (EditText) this.findViewById(R.id.etPerson_PhoneNo);
        final EditText person_Address = (EditText) this.findViewById(R.id.etPerson_Address);
        final Button btn = (Button) findViewById(R.id.submit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = item_name.getText().toString().trim();
                String description = item_description.getText().toString().trim();
                String date = lost_date.getText().toString().trim();
                String personName = person_Name.getText().toString().trim();
                String personPhoneNo = person_PhoneNo.getText().toString().trim();
                String personAddress = person_Address.getText().toString().trim();

                Toast.makeText(PostActivity.this,"Record Added",Toast.LENGTH_SHORT).show();


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.43.42:8000/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                LostDetail service1 = retrofit.create(LostDetail.class);

                Call<Lost> LostList = service1.savelost(name, description, date, personName, personPhoneNo, personAddress);
                LostList.enqueue(new Callback<Lost>() {
                    @Override


                    public void onResponse(Call<Lost> call, Response<Lost> response) {
                        Log.d("Post", "onResponse() called with: call = [" + call + "], response = [" + response + "]");

                        Intent intent = new Intent(PostActivity.this, MainActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<Lost> call, Throwable t) {
                        Log.d("Post", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                        Toast.makeText(PostActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }
}
