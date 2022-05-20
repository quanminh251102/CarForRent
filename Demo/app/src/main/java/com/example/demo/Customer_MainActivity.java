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

public class Customer_MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;

    DBHelper myDB;
    ArrayList<String> customer_cusid;
    ArrayList<String> customer_cusname;
    ArrayList<String> customer_cusadd;
    ArrayList<String> customer_phone;
    Customer_CustomAdapter customerCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.customer_mainactivity_add_button);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer_MainActivity.this, Customer_AddActivity.class);
                startActivity(intent);
            }
        });

        customer_cusid = new ArrayList<>();
        customer_cusname = new ArrayList<>();
        customer_cusadd = new ArrayList<>();
        customer_phone = new ArrayList<>();
        myDB = new DBHelper(Customer_MainActivity.this);

//        myDB.add_customer("Nguyễn Hoàng Kiệt","TP Cao Lãnh, Đồng Tháp","0123242443");

        storeDataInArrays();

        customerCustomAdapter = new Customer_CustomAdapter(Customer_MainActivity.this,this,
                customer_cusid,
                customer_cusname,
                customer_cusadd,
                customer_phone
        );

        recyclerView.setAdapter(customerCustomAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(Customer_MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.read_all_customer();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                customer_cusid.add(cursor.getString(0));;
                customer_cusname.add(cursor.getString(1));;
                customer_cusadd.add(cursor.getString(2));;
                customer_phone.add(cursor.getString(3));;
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
        builder.setMessage("Bạn chắc nhắn muốn xóa tất cả khách hàng?");
        builder.setPositiveButton("Đúng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper myDB = new DBHelper(Customer_MainActivity.this);
                myDB.delete_all_customer();
                //Refresh Activity
                Intent intent = new Intent(Customer_MainActivity.this, MainActivity.class);
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