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

public class Rent_MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;

    DBHelper myDB;
    ArrayList<String> rent_rent_id;
    ArrayList<String> rent_regno;
    ArrayList<String> rent_cusid;
    ArrayList<String> rent_rentaldate;
    ArrayList<String> rent_returndate;
    ArrayList<String> rent_fees;
    Rent_CustomAdapter rentCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.rent_mainactivity_add_button);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Rent_MainActivity.this,Rent_AddActivity.class);
                startActivity(intent);
            }
        });

        rent_rent_id = new ArrayList<>();
        rent_regno = new ArrayList<>();
        rent_cusid = new ArrayList<>();
        rent_rentaldate = new ArrayList<>();
        rent_returndate = new ArrayList<>();
        rent_fees = new ArrayList<>();

        myDB = new DBHelper(Rent_MainActivity.this);

        storeDataInArrays();

        rentCustomAdapter = new Rent_CustomAdapter(Rent_MainActivity.this,this,
                rent_rent_id,
                rent_regno,
                rent_cusid,
                rent_rentaldate,
                rent_returndate,
                rent_fees
        );

        recyclerView.setAdapter(rentCustomAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(Rent_MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.read_all_rent();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                rent_rent_id.add(cursor.getString(0));
                rent_regno.add(cursor.getString(1));
                rent_cusid.add(cursor.getString(2));
                rent_rentaldate.add(cursor.getString(3));
                rent_returndate.add(cursor.getString(4));
                rent_fees.add(cursor.getString(5));
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
        builder.setMessage("Bạn chắc nhắn muốn xóa tất cả chi tiết thuê xe?");
        builder.setPositiveButton("Đúng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper myDB = new DBHelper(Rent_MainActivity.this);
                myDB.delete_all_rent();
                //Refresh Activity
                Intent intent = new Intent(Rent_MainActivity.this, MainActivity.class);
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