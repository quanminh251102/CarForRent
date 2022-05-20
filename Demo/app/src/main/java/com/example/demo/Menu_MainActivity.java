package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu_MainActivity extends AppCompatActivity {

    CardView accountCard;
    CardView customerCard;
    CardView carCard;
    CardView rentCard;
    CardView exitCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);


        accountCard = findViewById(R.id.accountCard);
        customerCard = findViewById(R.id.customCard);
        carCard = findViewById(R.id.carCard);
        rentCard = findViewById(R.id.rentCard);
        exitCard = findViewById(R.id.exitCard);

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