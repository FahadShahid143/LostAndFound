package com.example.fahadshahid.lostandfound.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fahadshahid.lostandfound.LostDetails;
import com.example.fahadshahid.lostandfound.R;
import com.example.fahadshahid.lostandfound.UpdateActivity;
import com.example.fahadshahid.lostandfound.models.Lost;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fahad Shahid on 11/14/2017.
 */

public class LostAdapter extends RecyclerView.Adapter<LostAdapter.MyViewHolder> {

private ArrayList<Lost> lostList;
        RecyclerView recyclerView;
        Activity context;
        Gson gson;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tvItemName;
    TextView tvItemDescription;
    TextView tvDate;
    public ImageButton btn;
    public MyViewHolder(View view) {
        super(view);
        tvItemName = (TextView) view.findViewById((R.id.tvItemName));
        tvItemDescription = (TextView) view.findViewById(R.id.tvItemDescription);
        tvDate = (TextView) view.findViewById(R.id.tvItemLostDate);
        btn = (ImageButton) view.findViewById(R.id.btn);
    }
}

    public LostAdapter(Activity context, ArrayList<Lost> lostList) {
        this.context = context;
        this.lostList = lostList;
    }
    public void changeset(ArrayList<Lost> lostList){
        this.lostList = lostList;
        this.notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lost_item_list,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Lost lost = lostList.get(position);
        holder.tvItemName.setText(lost.getItem_name());
        holder.tvItemDescription.setText(lost.getItem_description());
        holder.tvDate.setText(lost.getLost_date());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lostList.remove(position);
                Toast.makeText(view.getContext(),"Item is deleted",Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gson = new Gson();
                String str = gson.toJson(lostList.get(position));
                Intent intent = new Intent(context, LostDetails.class);
                //Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("Details",str);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        /*if (lostList==null){
            return 0;
        }
        else {
            return lostList.size();
        }*/
        return lostList.size();
    }


}

