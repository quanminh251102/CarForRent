package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class forgetpassword extends AppCompatActivity {
    private EditText editText;
    private String subject = "Đặt lại mật khẩu của bạn";
    private String message = "Đây là mã xác nhận để khôi phục mật khẩu của bạn, mã xác nhận là: ";
    private Button reset_password_btn;
    DBHelper MyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forgetpassword);

        editText = findViewById(R.id.email_input_to_reset);
        reset_password_btn = findViewById(R.id.reset_password_btn1);
        MyDB = new DBHelper(this);

        reset_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }
    private void sendEmail() {
        String mEmail = editText.getText().toString();

        if(mEmail.equals(""))
        {
            Toast.makeText(forgetpassword.this,"Vui lòng điền đầy đủ thông tin",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Boolean check_my_email = MyDB.check_email(mEmail);
            if(check_my_email == true)
            {
                String MyID = MyDB.get_ID(mEmail);
                JavaMailAPI javaMailAPI = new JavaMailAPI(this, mEmail, subject, message + " " +MyID);
                javaMailAPI.execute();
                Intent intent = new Intent(forgetpassword.this, forgetpassword_2.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(forgetpassword.this,"Email không tồn tại",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
