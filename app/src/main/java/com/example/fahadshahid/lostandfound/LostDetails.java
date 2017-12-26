package com.example.fahadshahid.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fahadshahid.lostandfound.models.Lost;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LostDetails extends AppCompatActivity {

    TextView id;
    TextView name;
    TextView description;
    TextView date;
    TextView personName;
    TextView personPhoneNo;
    TextView personAddress;
    Button btn;

    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_details);

        id = (TextView) this.findViewById(R.id.tvLostItemId);
        name = (TextView) this.findViewById(R.id.tvLostItemName);
        description = (TextView) this.findViewById(R.id.tvLostItemDescription);
        date = (TextView) this.findViewById(R.id.tvLostDate);
        personName = (TextView) this.findViewById(R.id.tvPersonName);
        personPhoneNo = (TextView) this.findViewById(R.id.tvPersonPhoneNo);
        personAddress = (TextView) this.findViewById(R.id.tvPersonAddress);
        btn = (Button) this.findViewById(R.id.btnDelete);

        gson = new Gson();
        String target = getIntent().getStringExtra("Details");
        Lost lost = gson.fromJson(target, Lost.class);


        final String Id = lost.getId().toString();
        String Name = "Name = " + lost.getItem_name().toString();
        String Description = "Description = " + lost.getItem_description().toString();
        String Date = "Date = " + lost.getLost_date().toString();
        String PersonName = "Founder Name = " + lost.getPerson_name().toString();
        String PersonPhoneNo = "Founder Contact = " + lost.getPerson_phoneNo().toString();
        String PersonAddress = "Founder Address = " + lost.getPerson_address().toString();

        id.setText("Id = "+Id);
        name.setText(Name);
        description.setText(Description);
        date.setText(Date);
        personName.setText(PersonName);
        personPhoneNo.setText(PersonPhoneNo);
        personAddress.setText(PersonAddress);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.43.42:8000/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                LostDetail service1 = retrofit.create(LostDetail.class);

                Call<String> LostList = service1.deletelost(Id);
                LostList.enqueue(new Callback<String>() {
                    @Override


                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(LostDetails.this, "Record is Deleted", Toast.LENGTH_SHORT).show();
                        Log.i("delete_response_check", "onResponse() called with: call = [" + call + "], response = [" + response + "]");

                        Intent intent = new Intent(LostDetails.this, MainActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("delete", "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                        Toast.makeText(LostDetails.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        //LostDetailsEvent lostDetailsEvent = new LostDetailsEvent(target);

        //EventBus.getDefault().post(lostDetailsEvent);
    }

   /* @Subscribe
    public void onEvent(LostDetailsEvent event) {
        String str = event.getMessage();
        Lost lost = gson.fromJson(str, Lost.class);


        String Id = "id = " + lost.getId().toString();
        String Name = "Name = " + lost.getItem_name().toString();
        String Description = "Description = " + lost.getItem_description().toString();
        String Date = "Date = " + lost.getLost_date().toString();
        String PersonName = "Founder Name = " + lost.getPerson_name().toString();
        String PersonPhoneNo = "Founder Contact = " + lost.getPerson_phoneNo().toString();
        String PersonAddress = "Founder Address = " + lost.getPerson_address().toString();

        id.setText(Id);
        name.setText(Name);
        description.setText(Description);
        date.setText(Date);
        personName.setText(PersonName);
        personPhoneNo.setText(PersonPhoneNo);
        personAddress.setText(PersonAddress);

    }*/
}
