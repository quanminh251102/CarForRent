package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class forgetpassword_3 extends AppCompatActivity {
    TextInputEditText register_tv, repass_tv, repass_confirm_tv;
    Button reset_pass;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_fotgerpassword3);

        register_tv = findViewById(R.id.register_id_tv);
        repass_tv = findViewById(R.id.repass_tv);
        repass_confirm_tv = findViewById(R.id.repass_confirm_tv);
        reset_pass = findViewById(R.id.reset_password_btn1);
        DB = new DBHelper(this);

        reset_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regis_id = register_tv.getText().toString();
                String new_pass = repass_tv.getText().toString();
                String new_pass_confirm = repass_confirm_tv.getText().toString();
                String regis_email = DB.get_Email(regis_id);

                if(new_pass.equals(new_pass_confirm))
                {
                    Boolean check_regis = DB.checkID(regis_id,regis_email);
                    if(check_regis == true)
                    {
                        Boolean update = DB.updateData(regis_id,new_pass);
                        if(update == true)
                        {
                            Intent intent = new Intent(forgetpassword_3.this,login.class);
                            startActivity(intent);
                            Toast.makeText(forgetpassword_3.this,"Đổi mật khẩu thành công!",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(forgetpassword_3.this,"Đổi mật khẩu thất bại!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(forgetpassword_3.this,"Sai mã xác nhận!",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(forgetpassword_3.this,"Mật khẩu không khớp, vui lòng kiếm tra lại!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}