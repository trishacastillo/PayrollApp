package com.example.payrollsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class PayslipActivity extends AppCompatActivity {
    String query;
    ListView EmpList;
    ArrayList<String> empNames = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payslip);


        SQLiteDatabase db = openOrCreateDatabase("payroll_db", Context.MODE_PRIVATE,null);

        query="select * from tbl_employee order by lastname";
        EmpList = findViewById(R.id.listEmployeePay);
        Cursor c = db.rawQuery(query,null);
        int id = c.getColumnIndex("_id");
        int firstname = c.getColumnIndex("firstname");
        int lastname = c.getColumnIndex("lastname");
        int middlename = c.getColumnIndex("middle_name");
        int dob = c.getColumnIndex("date_of_birth");
        int sex = c.getColumnIndex("sex");
        int address = c.getColumnIndex("address");
        int contact = c.getColumnIndex("contact");
        int position = c.getColumnIndex("position_code");
        int stdate = c.getColumnIndex("starting_date");
        int rpd = c.getColumnIndex("rateperday");
        int otpay = c.getColumnIndex("overtimepay");
        empNames.clear();


        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, empNames);
        EmpList.setAdapter(arrayAdapter);

        final ArrayList<AppData> emp = new ArrayList<AppData>();


        if(c.moveToFirst()) {
            do {
                AppData employee = new AppData();
                employee._id = c.getString(id);
                employee.fname = c.getString(firstname);
                employee.lname = c.getString(lastname);
                employee.mname = c.getString(middlename);
                employee.dob = c.getString(dob);
                employee.sex = c.getString(sex);
                employee.contact = c.getString(contact);
                employee.address = c.getString(address);
                employee.position = c.getString(position);
                employee.stdate = c.getString(stdate);
                employee.rpd = c.getString(rpd);
                employee.otpay = c.getString(otpay);
                emp.add(employee);

                empNames.add(c.getString(lastname) + ",\t" + c.getString(firstname) + "\t" + c.getString(middlename));

            } while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            EmpList.invalidateViews();
        }

        //-----------------------------------------------------------------------------------------------
        //SEARCH BUTTON
        //-----------------------------------------------------------------------------------------------
        Button btnSearch=findViewById(R.id.btnSearch);
        EditText etSearch=findViewById(R.id.etSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = openOrCreateDatabase("payroll_db", Context.MODE_PRIVATE,null);

                EmpList = findViewById(R.id.listEmployeePay);
                final Cursor c = db.rawQuery("select * from tbl_employee where lastname like"+"'%"+etSearch.getText().toString()+"%' or firstname like"+"'%"+etSearch.getText().toString()+"%' order by lastname",null);
                int id = c.getColumnIndex("_id");
                int firstname = c.getColumnIndex("firstname");
                int lastname = c.getColumnIndex("lastname");
                int middlename = c.getColumnIndex("middle_name");
                int dob = c.getColumnIndex("date_of_birth");
                int sex = c.getColumnIndex("sex");
                int address = c.getColumnIndex("address");
                int contact = c.getColumnIndex("contact");
                int position = c.getColumnIndex("position_code");
                int stdate = c.getColumnIndex("starting_date");
                int rateperday = c.getColumnIndex("rateperday");
                int overtimepay = c.getColumnIndex("overtimepay");
                empNames.clear();

                EmpList.setAdapter(arrayAdapter);

                final ArrayList<AppData> emp = new ArrayList<AppData>();


                if(c.moveToFirst())
                {
                    do{
                        AppData employee = new AppData();
                        employee._id = c.getString(id);
                        employee.fname = c.getString(firstname);
                        employee.lname = c.getString(lastname);
                        employee.mname = c.getString(middlename);
                        employee.dob = c.getString(dob);
                        employee.sex = c.getString(sex);
                        employee.contact = c.getString(contact);
                        employee.address = c.getString(address);
                        employee.position = c.getString(position);
                        employee.stdate = c.getString(stdate);
                        employee.rpd = c.getString(rateperday);
                        employee.otpay = c.getString(overtimepay);
                        emp.add(employee);

                        empNames.add(c.getString(lastname) + ",\t "  + c.getString(firstname) + "\t"  + c.getString(middlename) );

                    } while(c.moveToNext());
                    arrayAdapter.notifyDataSetChanged();
                    EmpList.invalidateViews();

                }
                EmpList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AppData employeeid= emp.get(position);
                        Intent intent = new Intent(PayslipActivity.this, SalaryCalculatorActivity.class);
                        intent.putExtra("id",employeeid._id);
                        intent.putExtra("fname",employeeid.fname);
                        intent.putExtra("lname",employeeid.lname);
                        intent.putExtra("mname",employeeid.mname);
                        intent.putExtra("sex",employeeid.sex);
                        intent.putExtra("dob",employeeid.dob);
                        intent.putExtra("address",employeeid.address);
                        intent.putExtra("contact",employeeid.contact);
                        intent.putExtra("position",employeeid.position);
                        intent.putExtra("stdate",employeeid.stdate);
                        intent.putExtra("rpd",employeeid.rpd);
                        intent.putExtra("otpay",employeeid.otpay);
                        startActivity(intent);
                    }
                });

            }
        });
        //-----------------------------------------------------------------------------------------------
        //-----------------------------------------------------------------------------------------------
        //-----------------------------------------------------------------------------------------------

        EmpList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppData employeeid= emp.get(position);
                Intent intent = new Intent(PayslipActivity.this, SalaryCalculatorActivity.class);
                intent.putExtra("id",employeeid._id);
                intent.putExtra("fname",employeeid.fname);
                intent.putExtra("lname",employeeid.lname);
                intent.putExtra("mname",employeeid.mname);
                intent.putExtra("sex",employeeid.sex);
                intent.putExtra("dob",employeeid.dob);
                intent.putExtra("address",employeeid.address);
                intent.putExtra("contact",employeeid.contact);
                intent.putExtra("position",employeeid.position);
                intent.putExtra("stdate",employeeid.stdate);
                intent.putExtra("rpd",employeeid.rpd);
                intent.putExtra("otpay",employeeid.otpay);
                startActivity(intent);
            }
        });

        }
    }
