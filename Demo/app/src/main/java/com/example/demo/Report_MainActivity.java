package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;

public class Report_MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinnerSelect;
    TextInputLayout textInputLayout_ReportDate;
    Button buttonReport;
    TextView textViewAllMoney;
    TextView textView_money_today;
    TextView textView_date_near_month;
    TextView textView_money_near_month;
    TextView textView_date_choose_month;
    TextView textView_money_choose_month;
    TextView textView_today;
    EditText reportDate;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_main);

        spinnerSelect = findViewById(R.id.spinnerSelect);
        textInputLayout_ReportDate = findViewById(R.id.textInputLayout_ReportDate);
        buttonReport = findViewById(R.id.buttonReport);
        textViewAllMoney = findViewById(R.id.textViewAllMoney);
        textView_money_today = findViewById(R.id.textView_money_today);
        textView_date_near_month = findViewById(R.id.textView_date_near_month);
        textView_money_near_month = findViewById(R.id.textView_money_near_month);
        textView_date_choose_month = findViewById(R.id.textView_date_choose_month);
        textView_money_choose_month = findViewById(R.id.textView_money_choose_month);
        textView_today =  findViewById(R.id.textView_today);
        reportDate = findViewById(R.id.reportDate);

        db = new DBHelper(Report_MainActivity.this);

        textViewAllMoney.setText(String.valueOf(db.get_rent_money_all()) + " VNĐ");
        Select_All();

        spinnerSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                // On selecting a spinner item
                String label = parentView.getItemAtPosition(position).toString().trim();

                if (label.equals("Tất cả")) {
                    Select_All();
                }
                if (label.equals("Tháng và năm")){
                    Select_Choose();
                }
//                textView.setText(label);
                // Showing selected spinner item
//                Toast.makeText(parentView.getContext(), label,
//                        Toast.LENGTH_LONG).show();
//                caculator_fees();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        buttonReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = reportDate.getText().toString().trim();

                if (str.length() == 0){
                    Toast.makeText(view.getContext(), "Nhập tháng và năm",
                            Toast.LENGTH_LONG).show();
                    return;
                }


                if (checkDateFormat(str) == false){
                    Toast.makeText(view.getContext(), "Nhập tháng và năm sai quy định",
                            Toast.LENGTH_LONG).show();
                    textView_money_choose_month.setVisibility(View.GONE);
                    textView_date_choose_month.setVisibility(View.GONE);
                    return;
                }
                textView_money_choose_month.setVisibility(View.VISIBLE);
                textView_date_choose_month.setVisibility(View.VISIBLE);

                String arr[] = str.split("/");
                String month = arr[0]; 	//2
                String year = arr[1];			//2022

                if (month.length() == 1) month = "0" + month;
                textView_money_choose_month.setText(String.valueOf(db.get_rent_money_month(month,year)) + " VNĐ");
                textView_date_choose_month.setText(month+"/"+year);
            }
        });
    }

    boolean checkDateFormat(String str){
        boolean res = true;
        String arr[] = str.split("/");
        for (int i = 0 ;i<str.length();i++){
            if (    str.charAt(i) == '0' ||
                    str.charAt(i) == '1' ||
                    str.charAt(i) == '2' ||
                    str.charAt(i) == '3' ||
                    str.charAt(i) == '4' ||
                    str.charAt(i) == '5' ||
                    str.charAt(i) == '6' ||
                    str.charAt(i) == '7' ||
                    str.charAt(i) == '8' ||
                    str.charAt(i) == '9' ||
                    str.charAt(i) == '/'
            ){

            }
            else {
                return false;
            }
        }
        int month = Integer.valueOf(arr[0]);
        int year = Integer.valueOf(arr[1]);
        if (month <= 0 || month > 12){
            return false;
        }
        if (year <=0){
            return false;
        }
        return res;
    }

    void Select_All(){

        textInputLayout_ReportDate.setVisibility(View.GONE);
        buttonReport.setVisibility(View.GONE);
        textView_money_choose_month.setVisibility(View.GONE);
        textView_date_choose_month.setVisibility(View.GONE);

        textView_today.setVisibility(View.VISIBLE);
        textView_money_today.setVisibility(View.VISIBLE);
        textView_date_near_month.setVisibility(View.VISIBLE);
        textView_money_near_month.setVisibility(View.VISIBLE);

        LocalDate today = LocalDate.now();	//23-Feb-022

        String day = String.valueOf(today.getDayOfMonth());	//23
        String month = String.valueOf(today.getMonthValue()); 	//2
        String year = String.valueOf(today.getYear());			//2022

        if (day.length() == 1) day = "0" + day;
        if (month.length() == 1) month = "0" + month;

        textView_money_today.setText(String.valueOf(db.get_rent_money_today(day,month,year)) + " VNĐ");
        textView_date_near_month.setText(month +"/"+year);
        textView_money_near_month.setText(String.valueOf(db.get_rent_money_month(month,year)) + " VNĐ");
    }

    void Select_Choose(){
        textInputLayout_ReportDate.setVisibility(View.VISIBLE);
        buttonReport.setVisibility(View.VISIBLE);
//        textView_money_choose_month.setVisibility(View.VISIBLE);
//        textView_date_choose_month.setVisibility(View.VISIBLE);

        textView_today.setVisibility(View.GONE);
        textView_money_today.setVisibility(View.GONE);
        textView_date_near_month.setVisibility(View.GONE);
        textView_money_near_month.setVisibility(View.GONE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}