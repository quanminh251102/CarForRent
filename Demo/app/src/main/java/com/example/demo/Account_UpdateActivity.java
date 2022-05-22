package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class Account_UpdateActivity extends AppCompatActivity {
    TextInputEditText name_user,phone_user,pass_user;
    Button update_user;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_account_update);

        name_user = findViewById(R.id.update_name);
        phone_user = findViewById(R.id.update_phone);
        pass_user = findViewById(R.id.update_pass);
        update_user = findViewById(R.id.update_button);
        DB = new DBHelper(this);

        String current_user = DB.get_current_account();
        String current_name = DB.get_Name(current_user);
        String current_pass = DB.get_Pass(current_user);
        String current_phone = DB.get_Phone(current_user);

        name_user.setText(current_name);
        phone_user.setText(current_phone);
        pass_user.setText(current_pass);

        update_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_name = name_user.getText().toString();
                String new_phone = phone_user.getText().toString();
                String new_pass = pass_user.getText().toString();
                Boolean update = DB.updateData_user(current_user,new_name,new_phone,new_pass);
                if(update == true)
                {
                    Intent intent = new Intent(Account_UpdateActivity.this,Menu_MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(Account_UpdateActivity.this,"Cập nhật thành công!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Account_UpdateActivity.this,"Cập nhật thất bại!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}