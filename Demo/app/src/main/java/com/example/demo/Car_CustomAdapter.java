package com.example.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Car_CustomAdapter extends RecyclerView.Adapter<Car_CustomAdapter.MyViewHolder>{
    private Context context;
    private Activity activity;
    private ArrayList car_car_id;
    private ArrayList car_regno;
    private ArrayList car_brand;
    private ArrayList car_model;
    private ArrayList car_price;
    private ArrayList car_available;

    Car_CustomAdapter(Activity activity, Context context,
                      ArrayList car_car_id,
                      ArrayList car_regno,
                      ArrayList car_brand,
                      ArrayList car_model,
                      ArrayList car_price,
                      ArrayList car_available
    ){
        this.activity = activity;
        this.context = context;
        this.car_car_id = car_car_id;
        this.car_regno = car_regno;
        this.car_brand = car_brand;
        this.car_model = car_model;
        this.car_price = car_price;
        this.car_available = car_available;
    }

    @NonNull
    @Override
    public Car_CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.car_row, parent, false);
        return new Car_CustomAdapter.MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final Car_CustomAdapter.MyViewHolder holder, final int position) {
//        holder.car_row_carid.setText(String.valueOf(car_car_id.get(position)));
        holder.car_row_regno.setText("Biển số xe : "+String.valueOf(car_regno.get(position)) );
        holder.car_row_brand.setText("Hãng xe : "+String.valueOf(car_brand.get(position)));
        holder.car_row_model.setText("Kiểu xe : " +String.valueOf(car_model.get(position)) );
        holder.car_row_price.setText(String.valueOf(car_price.get(position)) + " VNĐ/Ngày");

        if (String.valueOf(car_available.get(position)).equals("1")){
            holder.car_row_available.setText("Còn trống");
            holder.car_row_available.setTextColor(Color.GREEN);
        }
        else {
            holder.car_row_available.setText("Đã cho thuê");
            holder.car_row_available.setTextColor(Color.RED);
        }

        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Car_UpdateActivity.class);
                intent.putExtra("car_id", String.valueOf(car_car_id.get(position)));
                intent.putExtra("regno", String.valueOf(car_regno.get(position)));
                intent.putExtra("brand", String.valueOf(car_brand.get(position)));
                intent.putExtra("model", String.valueOf(car_model.get(position)));
                intent.putExtra("price", String.valueOf(car_price.get(position)));
                intent.putExtra("available", String.valueOf(car_available.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return car_car_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mainLayout;
        TextView car_row_carid;
        TextView car_row_regno;
        TextView car_row_brand;
        TextView car_row_model;
        TextView car_row_price;
        TextView car_row_available;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            car_row_carid = itemView.findViewById(R.id.car_row_carid);
            car_row_regno = itemView.findViewById(R.id.car_row_regno);
            car_row_brand = itemView.findViewById(R.id.car_row_brand);
            car_row_model = itemView.findViewById(R.id.car_row_model);
            car_row_price = itemView.findViewById(R.id.car_row_price);
            car_row_available = itemView.findViewById(R.id.car_row_available);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }
}
