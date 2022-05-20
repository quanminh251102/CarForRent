package com.example.demo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
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

public class Rent_AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner rent_add_activity_regno_spinner;
    Spinner rent_add_activity_cusid_spinner;
    EditText rent_add_activity_rentaldate;
    EditText rent_add_activity_returndate;
    EditText rent_add_activity_fees;
    Button rent_add_activity_rentaldate_button;
    Button rent_add_activity_returndate_button;
    Button rent_add_activity_add_button;
    Button rent_add_activity_fees_button;
    List<String> list_regno , list_cusid;
    String regno = "", cusid;
    DatePickerDialog.OnDateSetListener setListener;
    DatePickerDialog.OnDateSetListener setListener_return;
    Boolean regno_check = false;
    Boolean cusid_check = false;
    Boolean rental_check = false;
    Boolean return_check = false;
    DBHelper db = new DBHelper(Rent_AddActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_add);

        rent_add_activity_regno_spinner = findViewById(R.id.rent_add_activity_regno_spinner);
        rent_add_activity_cusid_spinner = findViewById(R.id.rent_add_activity_cusid_spinner);
        rent_add_activity_rentaldate = findViewById(R.id.rent_add_activity_rentaldate);
        rent_add_activity_returndate = findViewById(R.id.rent_add_activity_returndate);
        rent_add_activity_fees = findViewById(R.id.rent_add_activity_fees);
        rent_add_activity_rentaldate_button = findViewById(R.id.rent_add_activity_rentaldate_button);
        rent_add_activity_returndate_button = findViewById(R.id.rent_add_activity_returndate_button);
        rent_add_activity_fees_button = findViewById(R.id.rent_add_activity_fees_button);
        rent_add_activity_add_button = findViewById(R.id.rent_add_activity_add_button);

        rent_add_activity_regno_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                // On selecting a spinner item
                String label = parentView.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parentView.getContext(), list_regno.get(position),
                        Toast.LENGTH_LONG).show();

                regno = list_regno.get(position);
                regno_check = true;
//                caculator_fees();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        rent_add_activity_cusid_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                // On selecting a spinner item
                String label = parentView.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parentView.getContext(), list_cusid.get(position),
                        Toast.LENGTH_LONG).show();

                cusid = list_cusid.get(position);
                cusid_check = true;
//                caculator_fees();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        load_car_spinner_data();
        load_customer_spinner_data();

        Calendar c = Calendar.getInstance();
        final int year_1 = c.get(Calendar.YEAR);
        final int month_1 = c.get(Calendar.MONTH);
        final int day_1 = c.get(Calendar.DAY_OF_MONTH);
        rent_add_activity_rentaldate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Rent_AddActivity.this, android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,
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

                rent_add_activity_rentaldate.setText(date);
                rental_check = true;
//                caculator_fees();
            }
        };

        rent_add_activity_returndate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Rent_AddActivity.this, android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,
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

                rent_add_activity_returndate.setText(date);
                return_check = true;
//                caculator_fees();
            }
        };

        rent_add_activity_fees_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caculator_fees();
            }
        });

        rent_add_activity_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String carreg;
                Integer rent_cusid;
                String rentaldate;
                String returndate;
                Integer fees;

//                carreg = "BM02";
//                rent_cusid = 2;
//                rentaldate = "11/5/2022";
//                returndate = "11/5/2022";
//                fees = 1000000;
                carreg = regno;
                rent_cusid = Integer.valueOf(cusid);
                rentaldate = rent_add_activity_rentaldate.getText().toString().trim();
                returndate = rent_add_activity_returndate.getText().toString().trim();
                fees = Integer.valueOf(rent_add_activity_fees.getText().toString().trim());

                db.add_rent(
                        carreg,
                        rent_cusid,
                        rentaldate,
                        returndate,
                        fees
                );

                Intent intent = new Intent(Rent_AddActivity.this, Rent_MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void caculator_fees(){

        if (regno_check == true && cusid_check == true && return_check == true && rental_check == true) {


            String rental_string = rent_add_activity_rentaldate.getText().toString().trim();
            String return_string = rent_add_activity_returndate.getText().toString().trim();

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

            rent_add_activity_fees.setText(String.valueOf(res));
//            rent_add_activity_fees.setText(rental_date[0] + "+" + return_date[0]);
        }
    }
    private void load_car_spinner_data() {
        List<String> labels = db.get_all_car_spinner();

        list_regno = db.get_all_car_regno_spinner();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        rent_add_activity_regno_spinner.setAdapter(dataAdapter);
    }

    private void load_customer_spinner_data() {
        List<String> labels = db.get_all_customer_spinner();

        list_cusid = db.get_all_customer_id_spinner();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        rent_add_activity_cusid_spinner.setAdapter(dataAdapter);
    }

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