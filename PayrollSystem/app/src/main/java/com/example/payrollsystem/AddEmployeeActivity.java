package com.example.payrollsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AddEmployeeActivity extends AppCompatActivity {

    Spinner spPosition;
    RadioButton rbSex;
    RadioGroup rgSex;

    EditText etLastname, etFirstname, etMiddlename, etContact, etAddress;
    TextView etBday, etStartDate;
    Button btnSubmit, btnCancel;

    //Variables for Calendars
    String bdate, sdate;
    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        final DataManager dm = new DataManager(this);

        rgSex = findViewById(R.id.rgSex);

        etLastname = findViewById(R.id.etLastName);
        etFirstname = findViewById(R.id.etFirstName);
        etMiddlename = findViewById(R.id.etMiddleName);
        etContact = findViewById(R.id.etContact);
        etAddress = findViewById(R.id.etAddress);

        btnCancel = findViewById(R.id.btnCancel);
        btnSubmit = findViewById(R.id.btnSubmit);

        //Creation of ist of options in the Gender
        spPosition = findViewById(R.id.spPosition);
        ArrayList<String> positionData = new ArrayList<>();
        positionData.add("Select Job Position");
        positionData.add("Chef");
        positionData.add("Suise Chef");
        positionData.add("Cooks");
        positionData.add("Store Man");
        positionData.add("Administration");
        positionData.add("Front Man");
        positionData.add("Cleaners");
        positionData.add("Kitchen Hand");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, positionData);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPosition.setAdapter(adapter);

        //Creating a Calendar Picker for users convenience in input date
        Calendar cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        etBday = findViewById(R.id.etBday);
        etStartDate = findViewById(R.id.etStartDate);


        etBday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddEmployeeActivity.this, new
                        DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int day, int month, int year) {
                                month = month + 1;
                                bdate = month + "/" + day + "/" + year;
                                etBday.setText(bdate);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });
        etStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddEmployeeActivity.this, new
                        DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int day, int month, int year) {
                                month = month + 1;
                                sdate = month + "/" + day + "/" + year;
                                etStartDate.setText(sdate);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rpd,otpay;
                String strposition=spPosition.getSelectedItem().toString();
                if(strposition.equals("Chef")){
                    rpd=1500;
                    otpay=200;
                }else if(strposition.equals("Suise Chef")){
                    rpd=1200;
                    otpay=150;
                }else if(strposition.equals("Cooks")){
                    rpd=1000;
                    otpay=100;
                }else if(strposition.equals("Store Man")){
                    rpd=900;
                    otpay=90;
                }else if(strposition.equals("Administration")){
                    rpd=950;
                    otpay=95;
                }else if(strposition.equals("Front Man")){
                    rpd=950;
                    otpay=95;
                }else if(strposition.equals("Cleaners")){
                    rpd=500;
                    otpay=50;
                }else if(strposition.equals("Kitchen Hand")){
                    rpd=700;
                    otpay=70;
                }else{
                    rpd=0;
                    otpay=0;
                }
                String strRateperday=Integer.toString(rpd);
                String strOvertimePay=Integer.toString(otpay);
                int selected=rgSex.getCheckedRadioButtonId();
                rbSex=findViewById(selected);
                try {
                    dm.insert(etLastname.getText().toString(), etFirstname.getText().toString(),
                            etMiddlename.getText().toString(), etBday.getText().toString(),
                            etAddress.getText().toString(), rbSex.getText().toString(),etContact.getText().toString(),
                            spPosition.getSelectedItem().toString(),etStartDate.getText().toString(),strRateperday,strOvertimePay);
                    Toast.makeText(AddEmployeeActivity.this,
                            "ADDED SUCCESSFULLY",
                            Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AddEmployeeActivity.this,DashboardActivity.class);
                    startActivity(i);

                } catch (Exception ex) {
                    Toast.makeText(AddEmployeeActivity.this, "Kindly fillup all the information needed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddEmployeeActivity.this,DashboardActivity.class);
                startActivity(i);
            }
        });


    }
}
