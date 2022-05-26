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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Car_CustomAdapter extends RecyclerView.Adapter<Car_CustomAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private Activity activity;
//    private ArrayList car_car_id;
//    private ArrayList car_regno;
//    private ArrayList car_brand;
//    private ArrayList car_model;
//    private ArrayList car_price;
//    private ArrayList car_available;

    private List<Car> mListCars;
    private List<Car> mListCarsOld;
    
    public Car_CustomAdapter(Activity activity, Context context,
//                      ArrayList car_car_id,
//                      ArrayList car_regno,
//                      ArrayList car_brand,
//                      ArrayList car_model,
//                      ArrayList car_price,
//                      ArrayList car_available,
                      List<Car> mListCars
    ){
        this.activity = activity;
        this.context = context;
//        this.car_car_id = car_car_id;
//        this.car_regno = car_regno;
//        this.car_brand = car_brand;
//        this.car_model = car_model;
//        this.car_price = car_price;
//        this.car_available = car_available;

        this.mListCars = mListCars;
        this.mListCarsOld = mListCars;
    }

    public Car_CustomAdapter(List<Car> mListCars) {
        this.mListCars = mListCars;
        this.mListCarsOld = mListCars;
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

        Car car = mListCars.get(position);
        if (car == null){
            return;
        }
        
//        holder.car_row_carid.setText(String.valueOf(car_car_id.get(position)));
        holder.car_row_regno.setText("Biển số xe : "+String.valueOf(car.getRegno()));
        holder.car_row_brand.setText("Hãng xe : "+String.valueOf(car.getBrand()));
        holder.car_row_model.setText("Kiểu xe : " +String.valueOf(car.getModel()) );
        holder.car_row_price.setText(String.valueOf(car.getPrice()) + " VNĐ/Ngày");

//        holder.car_row_available.setText(String.valueOf(car.getAvailable()));

        if (String.valueOf(car.getAvailable()).equals("1")){
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
                intent.putExtra("car_id", String.valueOf(car.getCarid()));
                intent.putExtra("regno", String.valueOf(car.getRegno()));
                intent.putExtra("brand", String.valueOf(car.getBrand()));
                intent.putExtra("model", String.valueOf(car.getModel()));
                intent.putExtra("price", String.valueOf(car.getPrice()));
                intent.putExtra("available", String.valueOf(car.getAvailable()));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListCars.size();
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.equals(""))
                {
                    mListCars = mListCarsOld;
                }
                else
                {
                    List<Car> list = new ArrayList<>();
                    for (Car Car : mListCarsOld){
                        if(Car.getRegno().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(Car);
                        }
                    }
                    mListCars = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mListCars;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mListCars = (ArrayList) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
