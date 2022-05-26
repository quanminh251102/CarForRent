package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Customer_AddActivity extends AppCompatActivity {

    EditText customer_add_activity_cusname;
    EditText customer_add_activity_cusadd;
    EditText customer_add_activity_phone;
    Button customer_add_activity_add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_add);

        customer_add_activity_cusname = findViewById(R.id.customer_add_activity_cusname);
        customer_add_activity_cusadd = findViewById(R.id.customer_add_activity_cusadd);
        customer_add_activity_phone = findViewById(R.id.customer_add_activity_phone);
        customer_add_activity_add_button = findViewById(R.id.customer_add_activity_add_button);
        customer_add_activity_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper myDB = new DBHelper(Customer_AddActivity.this);
//                        Integer.valueOf(pages_input.getText().toString().trim()));
                myDB.add_customer(
                        customer_add_activity_cusname.getText().toString().trim(),
                        customer_add_activity_cusadd.getText().toString().trim(),
                        customer_add_activity_phone.getText().toString().trim()
                );

                Intent intent = new Intent(Customer_AddActivity.this,Customer_MainActivity.class);
                startActivity(intent);
            }
        });
    }
}