package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu_MainActivity extends AppCompatActivity {
    Button edit;
    TextView name;
    CardView accountCard;
    CardView customerCard;
    CardView carCard;
    CardView rentCard;
    CardView exitCard;
    DBHelper MyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu_main);

        name = findViewById(R.id.name_textView);
        accountCard = findViewById(R.id.accountCard);
        customerCard = findViewById(R.id.customCard);
        carCard = findViewById(R.id.carCard);
        rentCard = findViewById(R.id.rentCard);
        exitCard = findViewById(R.id.exitCard);
        edit = findViewById(R.id.editProfileB);
        MyDB = new DBHelper(this);

//Load tên lên Card
        String current_account = MyDB.get_current_account();
        String admin_name = MyDB.get_Name(current_account);
        name.setText(admin_name);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_MainActivity.this,Account_UpdateActivity.class);
                startActivity(intent);
            }
        });

        accountCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Menu_MainActivity.this, Account_MainActivity.class);
//                startActivity(intent);
            }
        });

        customerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_MainActivity.this, Customer_MainActivity.class);
                startActivity(intent);
            }
        });

        carCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent intent = new Intent(Menu_MainActivity.this, Car_MainActivity.class);
                        startActivity(intent);
            }
        });

        rentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_MainActivity.this, Rent_MainActivity.class);
                startActivity(intent);
            }
        });

        exitCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_MainActivity.this, login.class);
                startActivity(intent);
            }
        });
    }
}