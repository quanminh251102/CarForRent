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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Customer_CustomAdapter extends RecyclerView.Adapter<Customer_CustomAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private Activity activity;
    private ArrayList customer_cusid, customer_cusname, customer_cusadd,customer_phone;
    private ArrayList customer_cusidOld, customer_cusnameOld, customer_cusaddOld,customer_phoneOld;

    private List<Customer> mListCustomers;
    private List<Customer> mListCustomersOld;

    public Customer_CustomAdapter(Activity activity, Context context,
//                           ArrayList customer_cusid,
//                           ArrayList customer_cusname,
//                           ArrayList customer_cusadd,
//                           ArrayList customer_phone,
                           List<Customer> mListCustomers
    ){
        this.activity = activity;
        this.context = context;
//        this.customer_cusid = customer_cusid;
//        this.customer_cusname = customer_cusname;
//        this.customer_cusadd = customer_cusadd;
//        this.customer_phone = customer_phone;
//        this.customer_cusidOld = customer_cusid;
//        this.customer_cusnameOld = customer_cusname;
//        this.customer_cusaddOld = customer_cusadd;
//        this.customer_phoneOld = customer_phone;

        this.mListCustomers = mListCustomers;
        this.mListCustomersOld = mListCustomers;
    }

    public Customer_CustomAdapter(List<Customer> mListCustomers) {
        this.mListCustomers = mListCustomers;
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

        Customer customer = mListCustomers.get(position);
        if (customer == null){
            return;
        }
//        holder.customer_row_cusid.setText(String.valueOf(customer_cusid.get(position)));
        holder.customer_row_cusname.setText("Tên khách hàng : " + customer.getCusname());
        holder.customer_row_cusadd.setText("Địa chỉ : " + customer.getCusadd());
        holder.customer_row_phone.setText("Số điện thoại : " + customer.getPhone());
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Customer_UpdateActivity.class);
                intent.putExtra("cusid", String.valueOf(customer.getCusid()));
                intent.putExtra("cusname", String.valueOf(customer.getCusname()));
                intent.putExtra("cusadd", String.valueOf(customer.getCusadd()));
                intent.putExtra("phone", String.valueOf(customer.getPhone()));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mListCustomers.size();
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
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.equals(""))
                {
//                    customer_cusid = customer_cusidOld;
//                    customer_cusname = customer_cusnameOld;
//                    customer_cusadd = customer_cusaddOld;
//                    customer_phone = customer_phoneOld;
                    mListCustomers = mListCustomersOld;
                }
                else
                {
//                    List<String> id_list = new ArrayList<String>();
//                    List<String> name_list = new ArrayList<String>();
//                    List<String> add_list = new ArrayList<String>();
//                    List<String> phone_list = new ArrayList<String>();
//                    for(int i = 0;i < customer_cusid.size();i++)
//                    {
//                        String temp = String.valueOf(customer_cusname.indexOf(i));
//                        if(temp.toLowerCase().contains(strSearch.toLowerCase())){
//
//                            id_list.add(String.valueOf(customer_cusid.indexOf(i)));
//                            name_list.add(String.valueOf(customer_cusname.indexOf(i)));
//                            add_list.add(String.valueOf(customer_cusadd.indexOf(i)));
//                            phone_list.add(String.valueOf(customer_phone.indexOf(i)));
//                        }
//                    }
//
//                    customer_cusid = (ArrayList) id_list;
//                    customer_cusname = (ArrayList) name_list;
//                    customer_cusadd = (ArrayList)add_list;
//                    customer_phone = (ArrayList)phone_list;
                      List<Customer> list = new ArrayList<>();
                      for (Customer customer : mListCustomersOld){
                          if(customer.getCusname().toLowerCase().contains(strSearch.toLowerCase())){
                              list.add(customer);
                          }
                      }
                      mListCustomers = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mListCustomers;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                customer_cusname = (ArrayList) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
