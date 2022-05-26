package com.example.demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
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
import java.util.List;

public class Return_MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;
    SearchView searchView;

    DBHelper myDB;
    ArrayList<String> rent_rent_id;
    ArrayList<String> rent_regno;
    ArrayList<String> rent_cusid;
    ArrayList<String> rent_rentaldate;
    ArrayList<String> rent_returndate;
    ArrayList<String> rent_fees;
    Return_CustomAdapter returnCustomAdapter;

    List<Rent> mListRent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_main);

        recyclerView = findViewById(R.id.recyclerView);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);


        rent_rent_id = new ArrayList<>();
        rent_regno = new ArrayList<>();
        rent_cusid = new ArrayList<>();
        rent_rentaldate = new ArrayList<>();
        rent_returndate = new ArrayList<>();
        rent_fees = new ArrayList<>();

        mListRent = new ArrayList<>();

        myDB = new DBHelper(Return_MainActivity.this);

        storeDataInArrays();

        returnCustomAdapter = new Return_CustomAdapter(Return_MainActivity.this,this,
//                rent_rent_id,
//                rent_regno,
//                rent_cusid,
//                rent_rentaldate,
//                rent_returndate,
//                rent_fees,
                mListRent
        );

        recyclerView.setAdapter(returnCustomAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(Return_MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
//        Cursor cursor = myDB.read_all_rent();

        Cursor cursor = myDB.read_all_rent_with_paid("1");

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
                mListRent.add(new Rent(
                        String.valueOf(cursor.getString(0)),
                        String.valueOf(cursor.getString(1)),
                        String.valueOf(cursor.getString(2)),
                        String.valueOf(cursor.getString(3)),
                        String.valueOf(cursor.getString(4)),
                        String.valueOf(cursor.getString(5)),
                        String.valueOf(cursor.getString(6))
                ));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search_button).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                returnCustomAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                returnCustomAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
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
                DBHelper myDB = new DBHelper(Return_MainActivity.this);
                myDB.delete_all_rent();
                //Refresh Activity
                Intent intent = new Intent(Return_MainActivity.this, MainActivity.class);
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