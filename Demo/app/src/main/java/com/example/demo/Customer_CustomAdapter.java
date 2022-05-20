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

public class Customer_CustomAdapter extends RecyclerView.Adapter<Customer_CustomAdapter.MyViewHolder>{

    private Context context;
    private Activity activity;
    private ArrayList customer_cusid, customer_cusname, customer_cusadd,customer_phone;

    Customer_CustomAdapter(Activity activity, Context context,
                           ArrayList customer_cusid,
                           ArrayList customer_cusname,
                           ArrayList customer_cusadd,
                           ArrayList customer_phone
    ){
        this.activity = activity;
        this.context = context;
        this.customer_cusid = customer_cusid;
        this.customer_cusname = customer_cusname;
        this.customer_cusadd = customer_cusadd;
        this.customer_phone = customer_phone;
    }

    @NonNull
    @Override
    public Customer_CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.customer_row, parent, false);
        return new Customer_CustomAdapter.MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final Customer_CustomAdapter.MyViewHolder holder, final int position) {
//        holder.customer_row_cusid.setText(String.valueOf(customer_cusid.get(position)));
        holder.customer_row_cusname.setText("Tên khách hàng : " + String.valueOf(customer_cusname.get(position)));
        holder.customer_row_cusadd.setText("Địa chỉ : " + String.valueOf(customer_cusadd.get(position)));
        holder.customer_row_phone.setText("Số điện thoại : " + String.valueOf(customer_phone.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Customer_UpdateActivity.class);
                intent.putExtra("cusid", String.valueOf(customer_cusid.get(position)));
                intent.putExtra("cusname", String.valueOf(customer_cusname.get(position)));
                intent.putExtra("cusadd", String.valueOf(customer_cusadd.get(position)));
                intent.putExtra("phone", String.valueOf(customer_phone.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return customer_cusid.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mainLayout;
        TextView customer_row_cusid;
        TextView customer_row_cusname;
        TextView customer_row_cusadd;
        TextView customer_row_phone;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            customer_row_cusid = itemView.findViewById(R.id.customer_row_cusid);
            customer_row_cusname = itemView.findViewById(R.id.customer_row_cusname);
            customer_row_cusadd = itemView.findViewById(R.id.customer_row_cusadd);
            customer_row_phone = itemView.findViewById(R.id.customer_row_phone);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }
}
