package com.example.demo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

public class Rent_UpdateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

//    Spinner rent_update_activity_regno_spinner;
//    Spinner rent_update_activity_cusid_spinner;

    EditText rent_update_activity_rentaldate;
    EditText rent_update_activity_returndate;
    EditText rent_update_activity_fees;
    EditText rent_update_activity_regno;
    EditText rent_update_activity_cusid;

    Button rent_update_activity_rentaldate_button;
    Button rent_update_activity_returndate_button;
    Button rent_update_activity_update_button;
    Button rent_update_activity_delete_button;
    Button rent_update_activity_fees_button;

    String rent_id;
    String regno;
    String cusid;
    String rentaldate;
    String returndate;
    String fees;

    List<String> list_regno , list_cusid;
    DatePickerDialog.OnDateSetListener setListener;
    DatePickerDialog.OnDateSetListener setListener_return;
    Boolean regno_check = true;
    Boolean rental_check = true;
    Boolean return_check = true;
    Boolean cusid_check = true;
    DBHelper db = new DBHelper(Rent_UpdateActivity.this);
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_update);

//        rent_update_activity_regno_spinner = findViewById(R.id.rent_update_activity_regno_spinner);
//        rent_update_activity_cusid_spinner = findViewById(R.id.rent_update_activity_cusid_spinner);
        rent_update_activity_rentaldate = findViewById(R.id.rent_update_activity_rentaldate);
        rent_update_activity_returndate = findViewById(R.id.rent_update_activity_returndate);
        rent_update_activity_fees = findViewById(R.id.rent_update_activity_fees);
        rent_update_activity_rentaldate_button = findViewById(R.id.rent_update_activity_rentaldate_button);
        rent_update_activity_returndate_button = findViewById(R.id.rent_update_activity_returndate_button);
        rent_update_activity_update_button = findViewById(R.id.rent_update_activity_update_button);
        rent_update_activity_delete_button = findViewById(R.id.rent_update_activity_delete_button);
        rent_update_activity_fees_button = findViewById(R.id.rent_update_activity_fees_button);
        rent_update_activity_regno = findViewById(R.id.rent_update_activity_regno);
        rent_update_activity_cusid = findViewById(R.id.rent_update_activity_cusid);

//        rent_update_activity_regno_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                // your code here
//                // On selecting a spinner item
//                String label = parentView.getItemAtPosition(position).toString();
//
//                // Showing selected spinner item
//                Toast.makeText(parentView.getContext(), list_regno.get(position),
//                        Toast.LENGTH_LONG).show();
//
////                regno = list_regno.get(position);
//
//                regno_check = true;
////                caculator_fees();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//                // your code here
//            }
//
//        });
//        rent_update_activity_cusid_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                // your code here
//                // On selecting a spinner item
//                String label = parentView.getItemAtPosition(position).toString();
//
//                // Showing selected spinner item
//                Toast.makeText(parentView.getContext(), list_cusid.get(position),
//                        Toast.LENGTH_LONG).show();
//                cusid_check = true;
//                cusid = list_cusid.get(position);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//                // your code here
//            }
//
//        });
//        load_car_spinner_data();
//        load_customer_spinner_data();

        Calendar c = Calendar.getInstance();
        final int year_1 = c.get(Calendar.YEAR);
        final int month_1 = c.get(Calendar.MONTH);
        final int day_1 = c.get(Calendar.DAY_OF_MONTH);
        rent_update_activity_rentaldate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Rent_UpdateActivity.this, android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,
                        setListener,year_1,month_1,day_1);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/"+month+"/"+year;

                rent_update_activity_rentaldate.setText(date);
                rental_check = true;
//                caculator_fees();
            }
        };

        rent_update_activity_returndate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Rent_UpdateActivity.this, android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,
                        setListener_return,year_1,month_1,day_1);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        setListener_return = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/"+month+"/"+year;

                rent_update_activity_returndate.setText(date);
                return_check = true;
//                caculator_fees();
            }
        };
        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("PhiếuThuêXe("+rent_id+")");
        }

        rent_update_activity_fees_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caculator_fees();
            }
        });

        rent_update_activity_update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                DBHelper myDB = new DBHelper(Rent_UpdateActivity.this);

                rentaldate = rent_update_activity_rentaldate.getText().toString().trim();
                returndate = rent_update_activity_returndate.getText().toString().trim();
                fees = rent_update_activity_fees.getText().toString().trim();

                myDB.updateData_rent(
                        rent_id,
                        regno,
                        Integer.valueOf(cusid),
                        rentaldate,
                        returndate,
                        Integer.valueOf(fees)
                );
            }
        });
        rent_update_activity_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("rent_id") &&
                getIntent().hasExtra("regno") &&
                getIntent().hasExtra("cusid") &&
                getIntent().hasExtra("rentaldate") &&
                getIntent().hasExtra("returndate") &&
                getIntent().hasExtra("fees")

//                intent.putExtra("regno", String.valueOf(rent_regno.get(position)));
//                intent.putExtra("cusid", String.valueOf(rent_cusid.get(position)));
//                intent.putExtra("rentaldate", String.valueOf(rent_rentaldate.get(position)));
//                intent.putExtra("returndate", String.valueOf(rent_returndate.get(position)));
//                intent.putExtra("fees", String.valueOf(rent_fees.get(position)));
        ){
            //Getting Data from Intent
            rent_id = getIntent().getStringExtra("rent_id");
            regno = getIntent().getStringExtra("regno");
            cusid = getIntent().getStringExtra("cusid");
            rentaldate = getIntent().getStringExtra("rentaldate");
            returndate = getIntent().getStringExtra("returndate");
            fees = getIntent().getStringExtra("fees");

//            int index_regno = getIndex_regno(rent_update_activity_regno_spinner, regno);
//            rent_update_activity_regno_spinner.setSelection(index_regno);
//
//            int index_cusid = getIndex_cusid(rent_update_activity_cusid_spinner, cusid);
//            rent_update_activity_cusid_spinner.setSelection(index_cusid);


            //Setting Intent Data
            rent_update_activity_regno.setText(regno);
            rent_update_activity_cusid.setText(cusid);
            rent_update_activity_rentaldate.setText(rentaldate);
            rent_update_activity_returndate.setText(returndate);
            rent_update_activity_fees.setText(fees);

            Log.d("stev", regno);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    private int getIndex_regno(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            String row[] = spinner.getItemAtPosition(i).toString().split("_");
            if (row[0].equals(myString)){
                index = i;
            }
        }
        return index;
    }

    private int getIndex_cusid(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            String row[] = spinner.getItemAtPosition(i).toString().split("_");
            if (row[1].equals(myString)){
                index = i;
            }
        }
        return index;
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Phiếu thuê " +rent_id + " ?");
        builder.setMessage("Bạn muốn xác nhận thanh toán phiếu thuê " + rent_id + " ?");
        builder.setPositiveButton("Đúng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper myDB = new DBHelper(Rent_UpdateActivity.this);
//                myDB.delete_one_rent(rent_id,regno);
                myDB.updateData_rent_paid(rent_id,regno,"1");
                finish();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void caculator_fees(){

        if (regno_check == true && return_check == true && rental_check == true && cusid_check == true) {


            String rental_string = rent_update_activity_rentaldate.getText().toString().trim();
            String return_string = rent_update_activity_returndate.getText().toString().trim();

            String rental_date[] = rental_string.split("/");
            String return_date[] = return_string.split("/");


            Cursor cursor = db.read_car_fees_with_regno(regno);

            int basic_fees = 0;

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    basic_fees = Integer.valueOf(cursor.getString(4));
                }
            }

            String rental_day = rental_date[0];
            String rental_month = rental_date[1];
            String rental_year = rental_date[2];

            String return_day = return_date[0];
            String return_month = return_date[1];
            String return_year = return_date[2];

            if (rental_day.length() == 1) rental_day = "0" + rental_day;
            if (rental_month.length() == 1) rental_month = "0" + rental_month;

            if (return_day.length() == 1) return_day = "0" + return_day;
            if (return_month.length() == 1) return_month = "0" + return_month;

            DateTimeFormatter myFormat =  DateTimeFormatter.ofPattern("dd MM yyyy");

            String inputString1 = rental_day + " " + rental_month + " " + rental_year;
            String inputString2 = return_day + " " + return_month + " " + return_year;
            LocalDate date1 = LocalDate.parse(inputString1,myFormat);
            LocalDate date2 = LocalDate.parse(inputString2,myFormat);

            long diff = ChronoUnit.DAYS.between(date1,date2);


            int res = (int)diff * basic_fees ;

            if (res < 0) res = 0;

            rent_update_activity_fees.setText(String.valueOf(res));
//            rent_update_activity_fees.setText(rental_date[0] + "+" + return_date[0]);
        }
    }
//    private void load_car_spinner_data() {
//        List<String> labels = db.get_all_car_spinner();
//
//        list_regno = db.get_all_car_regno_spinner();
//
//        // Creating adapter for spinner
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);
//
//        // Drop down layout style - list view with radio button
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // attaching data adapter to spinner
//        rent_update_activity_regno_spinner.setAdapter(dataAdapter);
//    }

//    private void load_customer_spinner_data() {
//        List<String> labels = db.get_all_customer_spinner();
//
//        list_cusid = db.get_all_customer_id_spinner();
//
//        // Creating adapter for spinner
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);
//
//        // Drop down layout style - list view with radio button
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // attaching data adapter to spinner
//        rent_update_activity_cusid_spinner.setAdapter(dataAdapter);
//    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}