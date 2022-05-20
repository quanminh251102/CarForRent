package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Car_AddActivity extends AppCompatActivity {

    EditText car_regno;
    EditText car_brand;
    EditText car_model;
    EditText car_price;
    EditText car_available;
    RadioButton car_activity_add_radio_button_true;
    RadioButton car_activity_add_radio_button_false;
    Button car_add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_add);

        car_regno = findViewById(R.id.car_regno);
        car_brand = findViewById(R.id.car_brand);
        car_model = findViewById(R.id.car_model);
        car_price = findViewById(R.id.car_price);
        car_activity_add_radio_button_true = findViewById(R.id.car_activity_add_radio_button_true);
        car_activity_add_radio_button_false = findViewById(R.id.car_activity_add_radio_button_false);
        car_add_button = findViewById(R.id.car_add_button);

        car_activity_add_radio_button_true.setChecked(true);
        car_price.setText("0");

        car_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper myDB = new DBHelper(Car_AddActivity.this);
//                        Integer.valueOf(pages_input.getText().toString().trim()));

                String carPrice = car_price.getText().toString().trim();
                if (carPrice.length() == 0 ) carPrice = "0";
                myDB.add_car(
                        car_regno.getText().toString().trim(),
                        car_brand.getText().toString().trim(),
                        car_model.getText().toString().trim(),
                        Integer.valueOf(carPrice),
                        car_activity_add_radio_button_true.isChecked()
                );
            }
        });
    }
}