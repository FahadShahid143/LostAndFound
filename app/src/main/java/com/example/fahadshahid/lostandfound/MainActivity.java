package com.example.fahadshahid.lostandfound;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fahadshahid.lostandfound.adapters.LostAdapter;
import com.example.fahadshahid.lostandfound.models.Lost;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MTAG";

    private RecyclerView recyclerView;
    private LostAdapter mAdapter;
    Button btn;
    Button logout;
    Gson gson;

    ArrayList<Lost> lostDetailList1 = new ArrayList();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void test(LostEvent customEvent) {
        Log.i("check","Hello");
        lostDetailList1 = customEvent.getMessage();
        gson = new Gson();
        String str = gson.toJson(customEvent.getMessage());

        Log.i("check",""+lostDetailList1);

        mAdapter.changeset(lostDetailList1);

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedpreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
        String login = sharedpreferences.getString("Id_KEY", "");
        if (login.equals("")) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {


            EventBus.getDefault().register(this);
            btn = (Button) findViewById(R.id.btnAdd);
            logout = (Button) findViewById(R.id.btnLogout);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(MainActivity.this, PostActivity.class);
                    startActivity(i);
                }
            });

            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences sharedpreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("Id_KEY", "");
                    editor.apply();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            });

            recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
            mAdapter = new LostAdapter(MainActivity.this, lostDetailList1);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(dividerItemDecoration);
            recyclerView.setAdapter(mAdapter);




            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.43.42:8000/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            LostDetail service = retrofit.create(LostDetail.class);

            Call<ArrayList<Lost>> LostList = service.getLostList();

            LostList.enqueue(new Callback<ArrayList<Lost>>() {
                @Override
                public void onResponse(Call<ArrayList<Lost>> call, Response<ArrayList<Lost>> response) {
                    Log.i("response_check", "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    ArrayList<Lost> LostDetailList = response.body();
                    LostEvent lostEvent = new LostEvent(LostDetailList);
                    EventBus.getDefault().post(lostEvent);
                }

                @Override
                public void onFailure(Call<ArrayList<Lost>> call, Throwable t) {
                    Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");

                }
            });
        /*LostList.enqueue(new Callback<ArrayList<Lost>>() {
            @Override
            public void onResponse(Call<ArrayList<Lost>> call, Response<ArrayList<Lost>> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");

                ArrayList<Lost> LostDetailList = response.body();
                LostEvent lostEvent = new LostEvent(LostDetailList);
                EventBus.getDefault().post(lostEvent);
            }

            @Override
            public void onFailure(Call<ArrayList<Lost>> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");

            }
        });*/


            Log.d(TAG, "end of oncreate method: ");
        }





    /*RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Lost> arrayList = new ArrayList<Lost>();
        for (int i = 0; i < 1000; i++) {
            arrayList.add(new Lost(i+1 , "Book ", "Lost outside library" , "20 november,2017","Adam","12345","Street 87, New York"));

        }
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);

        LostAdapter lostAdapter = new LostAdapter(this,arrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(lostAdapter);
    }*/
    }

    private boolean exit = false;

    @Override
    public void onBackPressed() {
        if (exit) {
            finish();// finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

                    Intent a = new Intent(Intent.ACTION_MAIN);
                    a.addCategory(Intent.CATEGORY_HOME);
                    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(a);
                }
            }, 0);
        }

    }
}
