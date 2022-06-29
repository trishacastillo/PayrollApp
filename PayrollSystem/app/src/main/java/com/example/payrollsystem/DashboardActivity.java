package com.example.payrollsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button btnAddNew = findViewById(R.id.btnAddNew);
        Button btnPayslip = findViewById(R.id.btnPayslip);
        Button btnViewEmployee = findViewById(R.id.btnViewEmployee);
        Button btnReport=findViewById(R.id.btnSalaryReport);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this,AddEmployeeActivity.class);
                startActivity(i);
            }
        });
        btnViewEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this,ViewEmployeeActivity.class);
                startActivity(i);
            }
        });
        btnPayslip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this,PayslipActivity.class);
                startActivity(i);
            }
        });
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this,SalaryReportActivity.class);
                startActivity(i);
            }
        });

    }
}