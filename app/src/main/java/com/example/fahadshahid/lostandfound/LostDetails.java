package com.example.fahadshahid.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fahadshahid.lostandfound.models.Lost;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class LostDetails extends AppCompatActivity {

    TextView id;
    TextView name;
    TextView description;
    TextView date;
    TextView personName;
    TextView personPhoneNo;
    TextView personAddress;

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

        gson = new Gson();
        String target = getIntent().getStringExtra("Details");
        Lost lost = gson.fromJson(target, Lost.class);


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
