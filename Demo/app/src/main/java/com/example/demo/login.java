package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class login extends AppCompatActivity {
    TextInputEditText username,password;
    private Button forgetpass_button,signin_button;
    private TextView signup_textView;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        username =(TextInputEditText) findViewById(R.id.username);
        password =(TextInputEditText) findViewById(R.id.password);
        signin_button = findViewById(R.id.signin_btn);
        forgetpass_button = findViewById(R.id.forgetpassword_btn);
        signup_textView = findViewById(R.id.signup_tv);
        DB = new DBHelper(this);

        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("") || pass.equals(""))
                {
                    Toast.makeText(login.this,"Vui lòng điền đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if(checkuserpass == true){
                        DB.delete_current_account();
                        Boolean add_current = DB.add_current_account(user);
                        Toast.makeText(login.this,"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this,Menu_MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(login.this,"Sai tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        signup_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent changeActivity = new Intent(login.this,signup.class);
                startActivity(changeActivity);
            }
        });
        forgetpass_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent changeActivity = new Intent(login.this,forgetpassword.class);
                startActivity(changeActivity);
            }
        });

    }
}