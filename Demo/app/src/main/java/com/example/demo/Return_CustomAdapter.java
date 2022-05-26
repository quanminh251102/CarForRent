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

public class Return_CustomAdapter extends RecyclerView.Adapter<Return_CustomAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private Activity activity;
    private ArrayList rent_rent_id;
    private ArrayList rent_regno;
    private ArrayList rent_cusid;
    private ArrayList rent_rentaldate;
    private ArrayList rent_returndate;
    private ArrayList rent_fees;

    private List<Rent> mListRents;
    private List<Rent> mListRentsOld;

    public Return_CustomAdapter(Activity activity, Context context,
//                       ArrayList rent_rent_id,
//                       ArrayList rent_regno,
//                       ArrayList rent_cusid,
//                       ArrayList rent_rentaldate,
//                       ArrayList rent_returndate,
//                       ArrayList rent_fees,
                                List<Rent> mListRents

    ){
        this.activity = activity;
        this.context = context;
//        this.rent_rent_id = rent_rent_id;
//        this.rent_regno = rent_regno;
//        this.rent_cusid = rent_cusid;
//        this.rent_rentaldate = rent_rentaldate;
//        this.rent_returndate = rent_returndate;
//        this.rent_fees = rent_fees;

        this.mListRents = mListRents;
        this.mListRentsOld = mListRents;
    }

    public Return_CustomAdapter(List<Rent> mListRents) {
        this.mListRents = mListRents;
        this.mListRentsOld = mListRents;
    }
    @NonNull
    @Override
    public Return_CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rent_row, parent, false);
        return new Return_CustomAdapter.MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final Return_CustomAdapter.MyViewHolder holder, final int position) {
        Rent rent = mListRents.get(position);
        if (rent == null){
            return;
        }

        DBHelper db = new DBHelper(context);
        String _cusid_name = db.getData_customer_name_with_id(String.valueOf(rent.getRent_cusid()));

        holder.rent_row_regno.setText("Biển số xe : " + String.valueOf(rent.getCarreg()) );
        holder.rent_row_cusid.setText("Khách hàng : " + _cusid_name );

        String rentaldate[] = String.valueOf(rent.getRentaldate()).split("-");
        String result_rentaldate = rentaldate[2]+"/"+rentaldate[1]+"/"+rentaldate[0] ;
        holder.rent_row_rentaldate.setText("Từ ngày : " + result_rentaldate+" - ");

        String returndate[] = String.valueOf(rent.getReturndate()).split("-");
        String result_returndate = returndate[2]+"/"+returndate[1]+"/"+returndate[0];
        holder.rent_row_returndate.setText(result_returndate);

        holder.rent_row_fees.setText("Chi phí : " + String.valueOf(rent.getFees()) + " VNĐ");

        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Return_UpdateActivity.class);
                intent.putExtra("rent_id", String.valueOf(rent.getRentid()));
                intent.putExtra("regno", String.valueOf(rent.getCarreg()));
                intent.putExtra("cusid", String.valueOf(rent.getRent_cusid()));

                intent.putExtra("rentaldate", result_rentaldate);
                intent.putExtra("returndate", result_returndate);

                intent.putExtra("fees", String.valueOf(rent.getFees()));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListRents.size();
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.equals(""))
                {
                    mListRents = mListRentsOld;
                }
                else
                {
                    List<Rent> list = new ArrayList<>();
                    for (Rent Rent : mListRentsOld){
                        if(Rent.getCarreg().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(Rent);
                        }
                    }
                    mListRents = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mListRents;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mListRents = (ArrayList) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
