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

import java.util.ArrayList;

public class ViewEmployeeActivity extends AppCompatActivity {

    ListView EmpList;
    ArrayList<String> empNames = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);

        SQLiteDatabase db = openOrCreateDatabase("payroll_db", Context.MODE_PRIVATE,null);

        EmpList = findViewById(R.id.listEmployee);
        final Cursor c = db.rawQuery("select * from tbl_employee order by lastname",null);
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
        empNames.clear();


        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, empNames);
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
                emp.add(employee);

                empNames.add(c.getString(lastname) + ",\t "  + c.getString(firstname) + "\t"  + c.getString(middlename) );

            } while(c.moveToNext());
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

                EmpList = findViewById(R.id.listEmployee);
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
                empNames.clear();


                //arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, empNames);
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
                        Intent intent = new Intent(ViewEmployeeActivity.this, EditDeleteActivity.class);
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
                Intent intent = new Intent(ViewEmployeeActivity.this, EditDeleteActivity.class);
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
                startActivity(intent);
            }
        });
    }
}