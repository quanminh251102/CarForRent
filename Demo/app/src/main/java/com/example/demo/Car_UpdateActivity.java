package com.example.demo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Car_UpdateActivity extends AppCompatActivity {

    EditText car_update_activity_regno;
    EditText car_update_activity_brand;
    EditText car_update_activity_model;
    EditText car_update_activity_price;

//    RadioButton car_update_activity_radio_button_true;
//    RadioButton car_update_activity_radio_button_false;

    Button car_update_activity_update_button;
    Button car_update_activity_delete_button;

    String car_id;
    String old_car_regno;
    String regno;
    String brand;
    String model;
    String price;
    String available;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_update);

        car_update_activity_regno = findViewById(R.id.car_update_activity_regno);
        car_update_activity_brand = findViewById(R.id.car_update_activity_brand);
        car_update_activity_model = findViewById(R.id.car_update_activity_model);
        car_update_activity_price = findViewById(R.id.car_update_activity_price);

//        car_update_activity_radio_button_true = findViewById(R.id.car_activity_update_radio_button_true);
//        car_update_activity_radio_button_false = findViewById(R.id.car_activity_update_radio_button_false);

        car_update_activity_update_button = findViewById(R.id.car_update_activity_update_button);
        car_update_activity_delete_button = findViewById(R.id.car_update_activity_delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Chi tiết xe ("+regno+")");
        }

        car_update_activity_update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                DBHelper myDB = new DBHelper(Car_UpdateActivity.this);
                regno = car_update_activity_regno.getText().toString().trim();
                brand = car_update_activity_brand.getText().toString().trim();
                model = car_update_activity_model.getText().toString().trim();
                Integer car_price = Integer.valueOf(car_update_activity_price.getText().toString().trim());


//                Boolean car_available = car_update_activity_radio_button_true.isChecked();
                Boolean car_available = true;
                if (available.equals("1")) car_available = true;
                else {
                    car_available = false;
                }
                myDB.updateData_car(car_id, regno, brand, model, car_price, car_available);
                myDB.updateData_rent_when_car_update(old_car_regno,regno);
                Intent intent = new Intent(Car_UpdateActivity.this,Car_MainActivity.class);
                startActivity(intent);
            }
        });
        car_update_activity_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("car_id") &&
                getIntent().hasExtra("regno") &&
                getIntent().hasExtra("brand") &&
                getIntent().hasExtra("model") &&
                getIntent().hasExtra("price") &&
                getIntent().hasExtra("available")
        ){
            //Getting Data from Intent
            car_id = getIntent().getStringExtra("car_id");
            regno = getIntent().getStringExtra("regno");
            brand = getIntent().getStringExtra("brand");
            model = getIntent().getStringExtra("model");
            price = getIntent().getStringExtra("price");
            available = getIntent().getStringExtra("available");
            old_car_regno = regno;


            //Setting Intent Data
            car_update_activity_regno.setText(regno);
            car_update_activity_brand.setText(brand);
            car_update_activity_model.setText(model);
            car_update_activity_price.setText(price);

//            if (available.equals("1")) {
//                car_update_activity_radio_button_true.setChecked(true);
//                car_update_activity_radio_button_false.setChecked(false);
//            }
//            else {
//                car_update_activity_radio_button_true.setChecked(false);
//                car_update_activity_radio_button_false.setChecked(true);
//            }

            Log.d("stev", regno+" "+brand+" "+model);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa " + regno + " ?");
        builder.setMessage("Bạn chắc chắn muốn xóa xe " + regno + " ?");
        builder.setPositiveButton("Đúng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper myDB = new DBHelper(Car_UpdateActivity.this);
                myDB.delete_one_car(car_id);
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