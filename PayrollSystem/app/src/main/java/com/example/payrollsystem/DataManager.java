package com.example.payrollsystem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataManager {
    // This is the actual database
    private SQLiteDatabase db;
    //rows for table employee
    public static final String TABLE_ROW_ID = "_id";
    public static final String TABLE_ROW_FNAME = "firstname";
    public static final String TABLE_ROW_LNAME = "lastname";
    public static final String TABLE_ROW_MNAME = "middle_name";
    public static final String TABLE_ROW_SEX = "sex";
    public static final String TABLE_ROW_DOB = "date_of_birth";
    public static final String TABLE_ROW_ADDRESS = "address";
    public static final String TABLE_ROW_POSITIONCODE = "position_code";
    public static final String TABLE_ROW_CONTACT = "contact";
    public static final String TABLE_ROW_STARTDATE = "starting_date";
    public static final String TABLE_ROW_RATEPERDAY = "rateperday";
    public static final String TABLE_ROW_OVERTIMEPAY = "overtimepay";

    //rows for table payroll
    public static final String TABLE_ROW_PID = "pid";
    public static final String TABLE_ROW_EMPID = "empid";
    public static final String TABLE_ROW_NUMOFWORKDAYS = "numofworkdays";
    public static final String TABLE_ROW_OVERTIMEHOURS = "overtime_hours";
    public static final String TABLE_ROW_SSS = "sss";
    public static final String TABLE_ROW_PHILHEALTH = "philhealth";
    public static final String TABLE_ROW_PAGIBIG = "pagibig";
    public static final String TABLE_ROW_TOTALDEDUCTION = "totaldeductions";
    public static final String TABLE_ROW_TOTALSALARY = "totalsalary";
    public static final String TABLE_ROW_PAYMONTH = "paymonth";
    public static final String TABLE_ROW_SALARYRELEASED = "salaryreleased";


    /*
    Next we have a private static final strings for
    database and table that we need to refer to just
    inside this class
    */
    private static final String DB_NAME = "payroll_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_EMPLOYEE = "tbl_employee", TABLE_PAYROLL="tbl_payroll";

    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {
        public CustomSQLiteOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        // This method only runs the first time the database is created
        @Override
        public void onCreate(SQLiteDatabase db) {
            // Create a table for photos and all their details
            String newTableQueryString = "create table "
                    + TABLE_EMPLOYEE + " ("
                    + TABLE_ROW_ID
                    + " integer primary key autoincrement not null,"
                    + TABLE_ROW_FNAME
                    + " text,"
                    + TABLE_ROW_LNAME
                    + " text,"
                    + TABLE_ROW_MNAME
                    + " text,"
                    + TABLE_ROW_SEX
                    + " text,"
                    + TABLE_ROW_DOB
                    + " date,"
                    + TABLE_ROW_CONTACT
                    + " text,"
                    + TABLE_ROW_ADDRESS
                    + " text,"
                    + TABLE_ROW_POSITIONCODE
                    + " text,"
                    + TABLE_ROW_RATEPERDAY
                    + " integer,"
                    + TABLE_ROW_OVERTIMEPAY
                    + " integer,"
                    + TABLE_ROW_STARTDATE
                    + " text);";
            db.execSQL(newTableQueryString);

            String newTableQueryString2 = "create table "
                    + TABLE_PAYROLL + " ("
                    + TABLE_ROW_PID
                    + " integer primary key autoincrement not null,"
                    + TABLE_ROW_EMPID
                    + " text,"
                    + TABLE_ROW_NUMOFWORKDAYS
                    + " text,"
                    + TABLE_ROW_OVERTIMEHOURS
                    + " text,"
                    + TABLE_ROW_SSS
                    + " text,"
                    + TABLE_ROW_PHILHEALTH
                    + " date,"
                    + TABLE_ROW_PAGIBIG
                    + " text,"
                    + TABLE_ROW_PAYMONTH
                    + " text,"
                    + TABLE_ROW_TOTALDEDUCTION
                    + " text,"
                    + TABLE_ROW_SALARYRELEASED
                    + " text,"
                    + TABLE_ROW_TOTALSALARY
                    + " text);";
            db.execSQL(newTableQueryString2);
        }

        // This method only runs when we increment DB_VERSION
        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {
        }

    }
    public DataManager(Context context) {
        // Create an instance of our internal
        //CustomSQLiteOpenHelper class
        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
        // Get a writable database
        db = helper.getWritableDatabase();
    }

    // Here are all our helper methods
    // Insert a record
    public void insert(String lname, String fname, String mname, String dob, String address, String sex, String contact, String position, String startdate, String rpd, String otpay){
        // Add all the details to the table
        String query = "INSERT INTO " + TABLE_EMPLOYEE + " (" +
                TABLE_ROW_LNAME + ", " +
                TABLE_ROW_FNAME + ", " +
                TABLE_ROW_MNAME + ", " +
                TABLE_ROW_DOB + ", " +
                TABLE_ROW_ADDRESS + ", " +
                TABLE_ROW_SEX + ", " +
                TABLE_ROW_CONTACT + ", " +
                TABLE_ROW_POSITIONCODE + ", " +
                TABLE_ROW_RATEPERDAY + ", " +
                TABLE_ROW_OVERTIMEPAY + ", " +
                TABLE_ROW_STARTDATE + ") " +
                "VALUES (" +
                "'" + lname + "'" + ", " +
                "'" + fname + "'" + ", " +
                "'" + mname + "'" + ", " +
                "'" + dob + "'" + ", " +
                "'" + address + "'" + ", " +
                "'" + sex + "'" + ", " +
                "'" + contact + "'" + ", " +
                "'" + position + "'" + ", " +
                "'" + rpd + "'" + ", " +
                "'" + otpay + "'" + ", " +
                "'" + startdate + "'" +
                ")";
        Log.i("insert() = ", query);
        db.execSQL(query);
    }
   // Insert a record
    public void insertPay(String empid, String nwd, String ot, String sss, String pb, String ph, String td, String ts, String rdate, String sr){
        // Add all the details to the table
        String query = "INSERT INTO " + TABLE_PAYROLL + " (" +
                TABLE_ROW_EMPID + ", " +
                TABLE_ROW_NUMOFWORKDAYS + ", " +
                TABLE_ROW_OVERTIMEHOURS + ", " +
                TABLE_ROW_SSS + ", " +
                TABLE_ROW_PAGIBIG + ", " +
                TABLE_ROW_PHILHEALTH + ", " +
                TABLE_ROW_TOTALDEDUCTION + ", " +
                TABLE_ROW_TOTALSALARY + ", " +
                TABLE_ROW_PAYMONTH + ", " +
                TABLE_ROW_SALARYRELEASED + ") " +
                "VALUES (" +
                "'" + empid + "'" + ", " +
                "'" + nwd + "'" + ", " +
                "'" + ot + "'" + ", " +
                "'" + sss + "'" + ", " +
                "'" + pb + "'" + ", " +
                "'" + ph + "'" + ", " +
                "'" + td + "'" + ", " +
                "'" + ts + "'" + ", " +
                "'" + rdate + "'" + ", " +
                "'" + sr + "'" +
                ")";
        Log.i("insert() = ", query);
        db.execSQL(query);
    }

    // Get all the records
    public Cursor selectAll() {
        Cursor c = db.rawQuery("SELECT *" +" from " +
                TABLE_EMPLOYEE, null);
        return c;
    }

    public void update(String _id,String lname, String fname, String mname, String dob, String address, String sex, String contact, String position,String stdate, String rpd, String otpay){
        // Add all the details to the table
        String query = "UPDATE " + TABLE_EMPLOYEE +
                " SET " +
                TABLE_ROW_LNAME + " = " +
                "'" + lname + "'," +
                TABLE_ROW_FNAME + " = " +
                "'" + fname + "'," +
                TABLE_ROW_MNAME + " = " +
                "'" + mname + "'," +
                TABLE_ROW_DOB + " = " +
                "'" + dob + "'," +
                TABLE_ROW_SEX + " = " +
                "'" + sex + "'," +
                TABLE_ROW_CONTACT + " = " +
                "'" + contact + "'," +
                TABLE_ROW_ADDRESS + " = " +
                "'" + address + "'," +
                TABLE_ROW_STARTDATE + " = " +
                "'" + stdate + "'," +
                TABLE_ROW_RATEPERDAY + " = " +
                "'" + rpd + "'," +
                TABLE_ROW_OVERTIMEPAY + " = " +
                "'" + otpay + "'," +
                TABLE_ROW_POSITIONCODE + " = " +
                "'" + position + "'" +
                " WHERE " + TABLE_ROW_ID + " = '" + _id + "'";
        Log.i("update() = ", query);
        db.execSQL(query);
    }

    public void delete(String ID){
        // Delete the details from the table if already exists
        String query = "DELETE FROM " + TABLE_EMPLOYEE +
                " WHERE " + TABLE_ROW_ID +
                " = '" + ID + "';";
        Log.i("delete() = ", query);
        db.execSQL(query);
        String query2 = "DELETE FROM " + TABLE_PAYROLL +
                " WHERE " + TABLE_ROW_EMPID +
                " = '" + ID + "';";
        Log.i("delete() = ", query2);
        db.execSQL(query2);
    }
}
