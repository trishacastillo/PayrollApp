package com.example.payrollsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SalaryCalculatorActivity extends AppCompatActivity {
    EditText etNumdays, etOT,etDateMonth;
    TextView tvEmpfullname, tvempID, tvviewID,tvDate, tvFull, tvPosition, tvNumdays, tvOT, tvrpd, tvOTPay,tvSSS,
    tvPagibig, tvPH, tvTotalDeduction, tvTotalSalary, tvSR;
    Button btnSubmit, btnViewReport;
    LinearLayout RecieptView, PayView;
    int year, month, day;
    String bdate, sdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_calculator);

        final DataManager dm = new DataManager(this);

        RecieptView=findViewById(R.id.RecieptView);
        PayView=findViewById(R.id.PayformView);

        //Textview
        tvEmpfullname=findViewById(R.id.tvEmpName);
        tvempID=findViewById(R.id.tvempID);
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

        //EditText
        etNumdays=findViewById(R.id.etNumdays);
        etOT=findViewById(R.id.etOT);
        etDateMonth=findViewById(R.id.etDateMonth);

        //Buttons
        btnSubmit=findViewById(R.id.btnSubmitPayslip);
        btnViewReport=findViewById(R.id.btnViewReport);

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
        String strotpay = i.getStringExtra("otpay").toString();
        String strrpd = i.getStringExtra("rpd").toString();


        String fullname=strlname+","+strfname+" "+strmname;
        tvempID.setText(stremployeeid);
        tvEmpfullname.setText(fullname);
        tvFull.setText(fullname);
        tvPosition.setText(strposition);
        tvOTPay.setText(strotpay);
        tvrpd.setText(strrpd);
        //Computations Process


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    int nwd1 = Integer.parseInt(etNumdays.getText().toString());
                    int parseOTHours1 = Integer.parseInt(etOT.getText().toString());
                    if ((nwd1 > 30) || (parseOTHours1 > 72)) {
                        Toast.makeText(SalaryCalculatorActivity.this, "INVALID INPUT.", Toast.LENGTH_SHORT).show();
                    } else {
                        PayView.setVisibility(View.GONE);
                        RecieptView.setVisibility(View.VISIBLE);
                        String strotpay = i.getStringExtra("otpay").toString();
                        String strrpd = i.getStringExtra("rpd").toString();

                        int nwd = Integer.parseInt(etNumdays.getText().toString());
                        int parseOTPAY = Integer.parseInt(strotpay);
                        int parseRPD = Integer.parseInt(strrpd);
                        int parseOTHours = Integer.parseInt(etOT.getText().toString());
                        double monthsalary, finalSalary, totalop, totalsal;
                        double sss, pagibig, philhealth, totaldeduction;

                        //calculations
                        monthsalary = nwd * parseRPD;
                        totalop = parseOTPAY * parseOTHours;
                        totalsal = monthsalary + totalop;
                        sss = totalsal * .04;
                        pagibig = 100;
                        philhealth = 200;

                        totaldeduction = sss + pagibig + philhealth;
                        finalSalary = totalsal - totaldeduction;
                        String strsss, strph, strpagibig, strtotalsalary, strtotaldeduction, strSR;
                        //converting int to string to display
                        strsss = Double.toString(sss);
                        strpagibig = Double.toString(pagibig);
                        strph = Double.toString(philhealth);
                        strSR = Double.toString(finalSalary);
                        strtotaldeduction = Double.toString(totaldeduction);
                        strtotalsalary = Double.toString(totalsal);
                        //setting the textfields
                        tvNumdays.setText(etNumdays.getText().toString());
                        tvOT.setText(etOT.getText().toString());
                        tvSSS.setText(strsss);
                        tvPagibig.setText(strpagibig);
                        tvPH.setText(strph);
                        tvTotalSalary.setText(strtotalsalary);
                        tvTotalDeduction.setText(strtotaldeduction);
                        tvSR.setText(strSR);
                        tvDate.setText(etDateMonth.getText().toString());

                        try {
                            dm.insertPay(stremployeeid, etNumdays.getText().toString(),
                                    etOT.getText().toString(), strsss,
                                    strpagibig, strph, strtotaldeduction,
                                    strtotalsalary, etDateMonth.getText().toString(), strSR);
                        } catch (Exception ex) {
                            Toast.makeText(SalaryCalculatorActivity.this, "Kindly fillup all the information needed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception ex) {
                    Toast.makeText(SalaryCalculatorActivity.this, "Kindly fillup all the information needed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        etDateMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SalaryCalculatorActivity.this, new
                        DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int day, int month, int year) {
                                month = month + 1;
                                bdate = month + "/" + day + "/" + year;
                                etDateMonth.setText(bdate);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });
        btnViewReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SalaryCalculatorActivity.this,DashboardActivity.class);
                startActivity(i);
            }
        });


    }
}