package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Menu_MainActivity extends AppCompatActivity {
    Button edit;
    ImageButton back,logout;
    TextView name;
    CardView accountCard;
    CardView customerCard;
    CardView carCard;
    CardView rentCard;
    CardView exitCard;
    CardView returnCard;
    CardView reportCard;
    DBHelper MyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu_main);

        name = findViewById(R.id.name_textView);

        customerCard = findViewById(R.id.customerCard);
        carCard = findViewById(R.id.carCard);
        rentCard = findViewById(R.id.rentCard);
        returnCard = findViewById(R.id.returnCard);
        reportCard = findViewById(R.id.reportCard);
        exitCard = findViewById(R.id.exitCard);

        edit = findViewById(R.id.editProfileB);



//        back = findViewById(R.id.back_button);
//        logout = findViewById(R.id.logout_button);

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

//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Menu_MainActivity.this,login.class);
//                startActivity(intent);
//            }
//        });
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MyDB.delete_current_account();
//                Intent intent = new Intent(Menu_MainActivity.this,login.class);
//                startActivity(intent);
//            }
//        });

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

        returnCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_MainActivity.this, Return_MainActivity.class);
                startActivity(intent);
            }
        });

        reportCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu_MainActivity.this, Report_MainActivity.class);
                startActivity(intent);
            }
        });
    }
}