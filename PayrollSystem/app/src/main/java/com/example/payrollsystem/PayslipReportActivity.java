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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PayslipReportActivity extends AppCompatActivity {
    ListView EmpList;
    ArrayList<String> empNames = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    TextView tvviewID,tvDate, tvFull, tvPosition, tvNumdays, tvOT, tvrpd, tvOTPay,tvSSS,
            tvPagibig, tvPH, tvTotalDeduction, tvTotalSalary, tvSR;
    LinearLayout RecieptView, PayView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payslip_report);
        EmpList = findViewById(R.id.listEmployeeReportID);
        RecieptView=findViewById(R.id.RecieptView);
        PayView=findViewById(R.id.ViewLayout);
        //Textview
        tvviewID=findViewById(R.id.tvEmpcode);
        tvDate=findViewById(R.id.tvDate);
        tvFull=findViewById(R.id.tvFullname);
        tvPosition=findViewById(R.id.tvPosition);
        tvNumdays=findViewById(R.id.tvNumdays);
        tvOT=findViewById(R.id.tvOvertime);
        tvrpd=findViewById(R.id.tvRpd);
        tvOTPay=findViewById(R.id.tvOvertimepay);
        tvSSS=findViewById(R.id.tvsss);
        tvPagibig=findViewById(R.id.tvPagibig);
        tvPH=findViewById(R.id.tvPhilhealth);
        tvTotalDeduction=findViewById(R.id.tvtotaldeduction);
        tvTotalSalary=findViewById(R.id.tvtotalsalary);
        tvSR=findViewById(R.id.tvReleasedSalary);

        Intent i = getIntent();

        String stremployeeid = i.getStringExtra("id").toString();
        String strfname = i.getStringExtra("fname").toString();
        String strlname = i.getStringExtra("lname").toString();
        String strmname = i.getStringExtra("mname").toString();
        String strposition = i.getStringExtra("position").toString();
        String strotpay = i.getStringExtra("otpay").toString();
        String strrpd = i.getStringExtra("rpd").toString();

      SQLiteDatabase db = openOrCreateDatabase("payroll_db", Context.MODE_PRIVATE,null);


        final Cursor c = db.rawQuery("select * from tbl_payroll where empid="+stremployeeid,null);
        int pid = c.getColumnIndex("pid");
        int empid = c.getColumnIndex("empid");
        int nwd = c.getColumnIndex("numofworkdays");
        int ot = c.getColumnIndex("overtime_hours");
        int sss = c.getColumnIndex("sss");
        int philhealth = c.getColumnIndex("philhealth");
        int pagibig = c.getColumnIndex("pagibig");
        int totaldeductions = c.getColumnIndex("totaldeductions");
        int totalsalary = c.getColumnIndex("totalsalary");
        int paymonth = c.getColumnIndex("paymonth");
        int salaryreleased = c.getColumnIndex("salaryreleased");
        empNames.clear();


        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, empNames);
        EmpList.setAdapter(arrayAdapter);

        final ArrayList<AppData> emp = new ArrayList<AppData>();
        if(c.moveToFirst())
        {
            do{
                AppData employee = new AppData();
                employee.pid = c.getString(pid);
                employee.empid = c.getString(empid);
                employee.nwd = c.getString(nwd);
                employee.ot = c.getString(ot);
                employee.sss = c.getString(sss);
                employee.ph = c.getString(philhealth);
                employee.totaldeduction = c.getString(totaldeductions);
                employee.pb = c.getString(pagibig);
                employee.totalsalary = c.getString(totalsalary);
                employee.paydate = c.getString(paymonth);
                employee.sr = c.getString(salaryreleased);
                emp.add(employee);

                empNames.add("Date: "+c.getString(paymonth) + " \t Salary Released:  "  + c.getString(salaryreleased));

            } while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            EmpList.invalidateViews();

        }
        EmpList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PayView.setVisibility(View.GONE);
                EmpList.setVisibility(View.GONE);
                RecieptView.setVisibility(View.VISIBLE);

                AppData employeeid= emp.get(position);

                tvviewID.setText("EMPID-"+stremployeeid);
                tvFull.setText(strlname+","+strfname+" "+strmname);
                tvPosition.setText(strposition);
                tvOTPay.setText(strotpay);
                tvOT.setText(employeeid.ot);
                tvNumdays.setText(employeeid.nwd);
                tvrpd.setText(strrpd);
                tvSSS.setText(employeeid.sss);
                tvPagibig.setText(employeeid.pb);
                tvPH.setText(employeeid.ph);
                tvTotalDeduction.setText(employeeid.totaldeduction);
                tvTotalSalary.setText(employeeid.totalsalary);
                tvSR.setText(employeeid.sr);
                tvDate.setText("Date:"+employeeid.paydate);


            }
        });
    }
}