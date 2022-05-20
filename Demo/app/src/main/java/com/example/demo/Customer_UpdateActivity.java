package com.example.demo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Customer_UpdateActivity extends AppCompatActivity {

    EditText customer_update_activity_cusname;
    EditText customer_update_activity_cusadd;
    EditText customer_update_activity_phone;
    Button customer_update_activity_update_button;
    Button customer_update_activity_delete_button;

    String cusid, cusname, cusadd, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_update);

        customer_update_activity_cusname = findViewById(R.id.customer_update_activity_cusname);
        customer_update_activity_cusadd = findViewById(R.id.customer_update_activity_cusadd);
        customer_update_activity_phone = findViewById(R.id.customer_update_activity_phone);
        customer_update_activity_update_button = findViewById(R.id.customer_update_activity_update_button);
        customer_update_activity_delete_button = findViewById(R.id.customer_update_activity_delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Kháchhàng(" + cusname + ")");
        }

        customer_update_activity_update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                DBHelper myDB = new DBHelper(Customer_UpdateActivity.this);
                cusname = customer_update_activity_cusname.getText().toString().trim();
                cusadd = customer_update_activity_cusadd.getText().toString().trim();
                phone = customer_update_activity_phone.getText().toString().trim();
                myDB.updateData_customer(cusid, cusname, cusadd, phone);
            }
        });
        customer_update_activity_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("cusid") &&
                getIntent().hasExtra("cusname") &&
                getIntent().hasExtra("cusadd") &&
                getIntent().hasExtra("phone")
        ){
            //Getting Data from Intent
            cusid = getIntent().getStringExtra("cusid");
            cusname = getIntent().getStringExtra("cusname");
            cusadd = getIntent().getStringExtra("cusadd");
            phone = getIntent().getStringExtra("phone");

            //Setting Intent Data
            customer_update_activity_cusname.setText(cusname);
            customer_update_activity_cusadd.setText(cusadd);
            customer_update_activity_phone.setText(phone);

            Log.d("stev", cusname+" "+cusadd+" "+phone);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa khách hàng " + cusname + " ?");
        builder.setMessage("Bạn chắc chắn muốn xóa khách hàng " + cusname + " ?");
        builder.setPositiveButton("Đúng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper myDB = new DBHelper(Customer_UpdateActivity.this);
                myDB.delete_one_customer(cusid);
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
}