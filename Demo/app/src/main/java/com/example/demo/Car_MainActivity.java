package com.example.demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Car_MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;

    DBHelper myDB;
    ArrayList<String> car_car_id;
    ArrayList<String> car_regno;
    ArrayList<String> car_brand;
    ArrayList<String> car_model;
    ArrayList<String> car_price;
    ArrayList<String> car_available;
    Car_CustomAdapter carCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.car_mainactivity_add_button);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Car_MainActivity.this,Car_AddActivity.class);
                startActivity(intent);
            }
        });

        car_car_id = new ArrayList<>();
        car_regno = new ArrayList<>();
        car_brand = new ArrayList<>();
        car_model = new ArrayList<>();
        car_price = new ArrayList<>();
        car_available = new ArrayList<>();

        myDB = new DBHelper(Car_MainActivity.this);

        storeDataInArrays();

        carCustomAdapter = new Car_CustomAdapter(Car_MainActivity.this,this,
                car_car_id,
                car_regno,
                car_brand,
                car_model,
                car_price,
                car_available
        );

        recyclerView.setAdapter(carCustomAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(Car_MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.read_all_car();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                car_car_id.add(cursor.getString(0));
                car_regno.add(cursor.getString(1));
                car_brand.add(cursor.getString(2));
                car_model.add(cursor.getString(3));
                car_price.add(cursor.getString(4));
                car_available.add(cursor.getString(5));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa tất cả?");
        builder.setMessage("Bạn chắc nhắn muốn xóa tất cả xe?");
        builder.setPositiveButton("Đúng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper myDB = new DBHelper(Car_MainActivity.this);
                myDB.delete_all_car();
                //Refresh Activity
                Intent intent = new Intent(Car_MainActivity.this, MainActivity.class);
                startActivity(intent);
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