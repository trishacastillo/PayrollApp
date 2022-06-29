package com.example.payrollsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class EditDeleteActivity extends AppCompatActivity {
    TextView tvID, tvIDview, tvFirstname,tvLastname, tvMiddlename, tvAddress,tvContact, tvDob, tvSex, tvPosition, tvStDate;
    EditText etEditLastName, etEditFirstname, etEditMiddlename, etEditAddress,etEditContact, etEditBday, etEditStDate;
    Button btnUpdate, btnDelete, btnEdit,btnCancel;
    ScrollView svViewFrame, sbEditViewFrame;
    Spinner spEditPosition;
    RadioButton rbEditSex,rbMale,rbFemale;
    RadioGroup rgEditSex;
    int year, month, day;
    String bdate, sdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete);

       final DataManager dm = new DataManager(this);
       //Button here-----------------------
        btnDelete=findViewById(R.id.btnDelete);
        btnEdit=findViewById(R.id.btnEdit);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnCancel=findViewById(R.id.btnCancel);

        //Edit Text here-------------------------
        tvID = findViewById(R.id.tvID);
        etEditLastName = findViewById(R.id.etEditLastName);
        etEditMiddlename = findViewById(R.id.etEditMiddleName);
        etEditFirstname = findViewById(R.id.etEditFirstName);
        etEditAddress= findViewById(R.id.etEditAddress);
        etEditContact= findViewById(R.id.etEditContact);
        etEditBday = findViewById(R.id.etEditBday);
        etEditStDate = findViewById(R.id.etEditStartDate);
        spEditPosition = findViewById(R.id.spEditPosition);

        //Textview here------------------------------
        tvIDview  = findViewById(R.id.tvIDview);
        tvFirstname = findViewById(R.id.tvFirstname);
        tvLastname = findViewById(R.id.tvLastname);
        tvMiddlename= findViewById(R.id.tvMiddlename);
        tvAddress= findViewById(R.id.tvAddress);
        tvContact= findViewById(R.id.tvContact);
        tvDob= findViewById(R.id.tvdob);
        tvSex= findViewById(R.id.tvSex);
        tvPosition= findViewById(R.id.tvPosition);
        tvStDate= findViewById(R.id.tvStartDate);

        //Frames and Radio Buttons here------------------------------
        svViewFrame=findViewById(R.id.svViewFrame);
        sbEditViewFrame=findViewById(R.id.svEditFrame);
        rbMale=findViewById(R.id.rbMale);
        rbFemale=findViewById(R.id.rbFemale);
        rgEditSex=findViewById(R.id.rgEditSex);


        //Getting the values from ViewEmployeeActivity
        Intent i = getIntent();

        String stremployeeid = i.getStringExtra("id").toString();
        String strfname = i.getStringExtra("fname").toString();
        String strlname = i.getStringExtra("lname").toString();
        String strmname = i.getStringExtra("mname").toString();
        String strsex = i.getStringExtra("sex").toString();
        String strdob = i.getStringExtra("dob").toString();
        String straddress = i.getStringExtra("address").toString();
        String strcontact = i.getStringExtra("contact").toString();
        String strposition = i.getStringExtra("position").toString();
        String strstdate = i.getStringExtra("stdate").toString();


        //Setting the text of the textviews
        tvIDview.setText(stremployeeid);
        tvLastname.setText(strlname);
        tvFirstname.setText(strfname);
        tvMiddlename.setText(strmname);
        tvSex.setText(strsex);
        tvContact.setText(strcontact);
        tvAddress.setText(straddress);
        tvPosition.setText(strposition);
        tvDob.setText(strdob);
        tvStDate.setText(strstdate);

        //Spinner Value
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
        spEditPosition .setAdapter(adapter);

        //Creating a Calendar Picker for users convenience in input date
        Calendar cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        etEditBday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditDeleteActivity.this, new
                        DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int day, int month, int year) {
                                month = month + 1;
                                bdate = month + "/" + day + "/" + year;
                                etEditBday.setText(bdate);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });
        etEditStDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditDeleteActivity.this, new
                        DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int day, int month, int year) {
                                month = month + 1;
                                sdate = month + "/" + day + "/" + year;
                                etEditStDate.setText(sdate);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        tvID.setText(stremployeeid);
        etEditLastName.setText(strlname);
        etEditFirstname.setText(strfname);
        etEditMiddlename.setText(strmname);
        etEditAddress.setText(straddress);
        etEditContact.setText(strcontact);
        etEditBday.setText(strdob);
        etEditStDate.setText(strstdate);
        spEditPosition.setSelection(positionData.indexOf(strposition));
        if(strsex.equals("Male")){rbMale.setChecked(true);}
        else{rbFemale.setChecked(true);}

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dm.delete(tvIDview.getText().toString());
                Toast.makeText(EditDeleteActivity.this,
                        "SUCCESSFULLY DELETED!",
                        Toast.LENGTH_SHORT).show();
                Intent i = new Intent(EditDeleteActivity.this,DashboardActivity.class);
                startActivity(i);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditDeleteActivity.this,DashboardActivity.class);
                startActivity(i);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svViewFrame.setVisibility(View.GONE);
                sbEditViewFrame.setVisibility(View.VISIBLE);
            }
        });

        int rpd,otpay;
        String strposition1=spEditPosition.getSelectedItem().toString();
        if(strposition1.equals("Chef")){
            rpd=1500;
            otpay=200;
        }else if(strposition1.equals("Suise Chef")){
            rpd=1200;
            otpay=150;
        }else if(strposition1.equals("Cooks")){
            rpd=1000;
            otpay=100;
        }else if(strposition1.equals("Store Man")){
            rpd=900;
            otpay=90;
        }else if(strposition1.equals("Administration")){
            rpd=950;
            otpay=95;
        }else if(strposition1.equals("Front Man")){
            rpd=950;
            otpay=95;
        }else if(strposition1.equals("Cleaners")){
            rpd=500;
            otpay=50;
        }else if(strposition1.equals("Kitchen Hand")){
            rpd=700;
            otpay=70;
        }else{
            rpd=0;
            otpay=0;
        }
        String strRateperday=Integer.toString(rpd);
        String strOvertimePay=Integer.toString(otpay);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected=rgEditSex.getCheckedRadioButtonId();
                rbEditSex=findViewById(selected);
                dm.update(tvID.getText().toString(),etEditLastName.getText().toString(),etEditFirstname.getText().toString(),
                        etEditMiddlename.getText().toString(), etEditBday.getText().toString(),
                        etEditAddress.getText().toString(), rbEditSex.getText().toString(),etEditContact.getText().toString(),
                        spEditPosition.getSelectedItem().toString(),etEditStDate.getText().toString(),strRateperday,strOvertimePay);
                Toast.makeText(EditDeleteActivity.this,
                        " SUCESSFULLY UPDATED!",
                        Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EditDeleteActivity.this,DashboardActivity.class);
                startActivity(i);
            }
        });

    }


}