package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class forgetpassword_2 extends AppCompatActivity {
    private String appName = "Gmail";
    private String packageName = "com.google.android.gm";

    private Button open_mail;
    private TextView skip_textView,other_mail_textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_fotgetpassword2);

        open_mail = findViewById(R.id.open_mail_btn);
        skip_textView = findViewById(R.id.skip_tv);
        other_mail_textView = findViewById(R.id.use_other_mail_tv);

        open_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
                if (intent != null) {
                    // We found the activity now start the activity
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else {
                    // Bring user to the market or let them choose an app?
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("market://details?id=" + "com.google.android.gm"));
                    startActivity(intent);
                }
            }
        });


        other_mail_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(forgetpassword_2.this,forgetpassword.class);
                startActivity(intent);
            }
        });

        skip_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(forgetpassword_2.this,forgetpassword_3.class);
                startActivity(intent);
            }
        });
    }
}