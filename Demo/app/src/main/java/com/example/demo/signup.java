package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.Random;

public class signup extends AppCompatActivity {
    TextInputEditText full_name,username,password,phone_number,email;
    Button signup_button;
    TextView signin_textView;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);

        full_name = findViewById(R.id.signup_full_name);
        username = findViewById(R.id.signup_username);
        password = findViewById(R.id.signup_password);
        phone_number = findViewById(R.id.signup_phone_number);
        email = findViewById(R.id.signup_email);
        signup_button = findViewById(R.id.signup_btn);
        signin_textView = findViewById(R.id.signin_tv);
        DB = new DBHelper(this);

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = full_name.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String phone = phone_number.getText().toString();
                String mail = email.getText().toString();

                if(name.equals("") || user.equals("") || pass.equals("") || phone.equals("") || mail.equals("")){
                    Toast.makeText(signup.this,"Vui lòng điền đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkuser = DB.checkusername(user);
                    if(checkuser == false)
                    {
                        Random random = new Random();
                        int My_ID = random.nextInt(999999) + 100000;
                        String id = Integer.toString(My_ID);
                        Boolean insert = DB.insertData(user,pass,mail,phone,name,id);
                        if(insert == true)
                        {
                            Toast.makeText(signup.this,"Đăng ký thành công!",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(signup.this,login.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(signup.this,"Đăng ký thất bại!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(signup.this,"Người dùng đã tồn tại!",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        signin_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this,login.class);
                startActivity(intent);
            }
        });
    }
}