package com.example.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class Rent_CustomAdapter extends RecyclerView.Adapter<Rent_CustomAdapter.MyViewHolder>{

    private Context context;
    private Activity activity;
    private ArrayList rent_rent_id;
    private ArrayList rent_regno;
    private ArrayList rent_cusid;
    private ArrayList rent_rentaldate;
    private ArrayList rent_returndate;
    private ArrayList rent_fees;

    Rent_CustomAdapter(Activity activity, Context context,
                       ArrayList rent_rent_id,
                       ArrayList rent_regno,
                       ArrayList rent_cusid,
                       ArrayList rent_rentaldate,
                       ArrayList rent_returndate,
                       ArrayList rent_fees

    ){
        this.activity = activity;
        this.context = context;
        this.rent_rent_id = rent_rent_id;
        this.rent_regno = rent_regno;
        this.rent_cusid = rent_cusid;
        this.rent_rentaldate = rent_rentaldate;
        this.rent_returndate = rent_returndate;
        this.rent_fees = rent_fees;
    }

    @NonNull
    @Override
    public Rent_CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rent_row, parent, false);
        return new Rent_CustomAdapter.MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final Rent_CustomAdapter.MyViewHolder holder, final int position) {
//        holder.rent_row_rentid.setText(String.valueOf(rent_rent_id.get(position)));
        holder.rent_row_regno.setText("Biển số xe : " + String.valueOf(rent_regno.get(position)) );
        holder.rent_row_cusid.setText("Khách hàng : " + String.valueOf(rent_cusid.get(position)) );

        String rentaldate[] = String.valueOf(rent_rentaldate.get(position)).split("-");
        String result_rentaldate = rentaldate[2]+"/"+rentaldate[1]+"/"+rentaldate[0] ;
        holder.rent_row_rentaldate.setText("Từ ngày : " + result_rentaldate+" - ");

        String returndate[] = String.valueOf(rent_returndate.get(position)).split("-");
        String result_returndate = returndate[2]+"/"+returndate[1]+"/"+returndate[0];
        holder.rent_row_returndate.setText(result_returndate);

        holder.rent_row_fees.setText("Chi phí : " + String.valueOf(rent_fees.get(position)) + " VNĐ");

        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Rent_UpdateActivity.class);
                intent.putExtra("rent_id", String.valueOf(rent_rent_id.get(position)));
                intent.putExtra("regno", String.valueOf(rent_regno.get(position)));
                intent.putExtra("cusid", String.valueOf(rent_cusid.get(position)));

                intent.putExtra("rentaldate", result_rentaldate);
                intent.putExtra("returndate", result_returndate);

                intent.putExtra("fees", String.valueOf(rent_fees.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rent_rent_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mainLayout;
        //        TextView rent_row_rentid;
        TextView rent_row_regno;
        TextView rent_row_cusid;
        TextView rent_row_rentaldate;
        TextView rent_row_returndate;
        TextView rent_row_fees;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            rent_row_rentid = itemView.findViewById(R.id.rent_row_rentid);
            rent_row_regno = itemView.findViewById(R.id.rent_row_regno);
            rent_row_cusid = itemView.findViewById(R.id.rent_row_cusid);
            rent_row_rentaldate = itemView.findViewById(R.id.rent_row_rentaldate);
            rent_row_returndate = itemView.findViewById(R.id.rent_row_returndate);
            rent_row_fees = itemView.findViewById(R.id.rent_row_fees);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }
}
