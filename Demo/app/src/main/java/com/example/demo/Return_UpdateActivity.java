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

public class Return_UpdateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText rent_update_activity_rentaldate;
    EditText rent_update_activity_returndate;
    EditText rent_update_activity_fees;
    EditText rent_update_activity_regno;
    EditText rent_update_activity_cusid;

    Button rent_update_activity_delete_button;

    String rent_id;
    String regno;
    String cusid;
    String rentaldate;
    String returndate;
    String fees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_update);

        rent_update_activity_rentaldate = findViewById(R.id.rent_update_activity_rentaldate);
        rent_update_activity_returndate = findViewById(R.id.rent_update_activity_returndate);
        rent_update_activity_fees = findViewById(R.id.rent_update_activity_fees);
        rent_update_activity_delete_button = findViewById(R.id.rent_update_activity_delete_button);
        rent_update_activity_regno = findViewById(R.id.rent_update_activity_regno);
        rent_update_activity_cusid = findViewById(R.id.rent_update_activity_cusid);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Chi tiết phiếu thuê xe ("+rent_id+")");
        }

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

        ){
            //Getting Data from Intent
            rent_id = getIntent().getStringExtra("rent_id");
            regno = getIntent().getStringExtra("regno");
            cusid = getIntent().getStringExtra("cusid");
            rentaldate = getIntent().getStringExtra("rentaldate");
            returndate = getIntent().getStringExtra("returndate");
            fees = getIntent().getStringExtra("fees");

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

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Phiếu thuê " +rent_id + " ?");
        builder.setMessage("Bạn muốn xóa thanh toán phiếu thuê " + rent_id + " ?");
        builder.setPositiveButton("Đúng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper myDB = new DBHelper(Return_UpdateActivity.this);
                myDB.delete_one_rent(rent_id,regno);
//                myDB.updateData_rent_paid(rent_id,regno,"1");
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