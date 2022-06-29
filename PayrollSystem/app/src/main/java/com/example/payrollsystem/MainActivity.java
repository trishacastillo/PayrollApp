package com.example.payrollsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOpen = findViewById(R.id.btnOpen);
        Button btnExit = findViewById(R.id.btnExit);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,DashboardActivity.class);
                startActivity(i);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setTitle("RestoPay Payroll System");
                alertDialogBuilder
                        .setMessage("Are you sure want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("YES",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                finishAffinity();
                                System.exit(0);
                            }
                        });

                alertDialogBuilder.setNeutralButton("CANCEL",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
                alertDialogBuilder.setNegativeButton("NO",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }
}