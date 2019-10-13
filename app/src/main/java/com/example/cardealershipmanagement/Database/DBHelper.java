package com.example.cardealershipmanagement.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static String dbName = "data_dir";
    private static DBHelper dbHelper;
    private SQLiteDatabase myWritableDb;

    private DBHelper(Context context) {
        super(context, dbName, null, 1);
    }

    public static DBHelper getInstance(Context context) {
        if (dbHelper == null) {
            return dbHelper = new DBHelper(context);
        } else {
            return dbHelper;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        new EmployeeTable(sqLiteDatabase);
        new BuyerTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private SQLiteDatabase getMyWritableDatabase() {
        if ((myWritableDb == null) || (!myWritableDb.isOpen())) {
            myWritableDb = this.getWritableDatabase();
        }
        return myWritableDb;
    }

    public boolean addEmployeeData (EmployeeTable employeeTable){
        try {
            EmployeeTable keyEmployee = new EmployeeTable();
            SQLiteDatabase sqLiteDatabase = getMyWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(keyEmployee.getFname(), employeeTable.getFname());
            contentValues.put(keyEmployee.getLname(), employeeTable.getLname());
            contentValues.put(keyEmployee.getEmail(), employeeTable.getEmail());
            contentValues.put(keyEmployee.getMobile(), employeeTable.getMobile());
            contentValues.put(keyEmployee.getDob(), employeeTable.getDob());
            contentValues.put(keyEmployee.getPosition(), employeeTable.getPosition());
            contentValues.put(keyEmployee.getGender(), employeeTable.getGender());
            contentValues.put(keyEmployee.getCity(), employeeTable.getCity());
            contentValues.put(keyEmployee.getPincode(), employeeTable.getPincode());
            contentValues.put(keyEmployee.getUsername(), employeeTable.getUsername());
            contentValues.put(keyEmployee.getPassword(), employeeTable.getPassword());

            sqLiteDatabase.insert(keyEmployee.getTableName(), null, contentValues);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addBuyerData (BuyerTable buyerTable) {
        try{
            BuyerTable keyBuyer = new BuyerTable();
            SQLiteDatabase sqLiteDatabase = getMyWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(keyBuyer.getFname(), buyerTable.getFname());
            contentValues.put(keyBuyer.getLname(), buyerTable.getLname());
            contentValues.put(keyBuyer.getGender(), buyerTable.getGender());
            contentValues.put(keyBuyer.getAadhar_no(), buyerTable.getAadhar_no());
            contentValues.put(keyBuyer.getAddress(), buyerTable.getAddress());
            contentValues.put(keyBuyer.getMobile(), buyerTable.getMobile());
            contentValues.put(keyBuyer.getLicense(), buyerTable.getLicense());

            sqLiteDatabase.insert(keyBuyer.getTableName(),null,contentValues);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cursor getEmployeeData() {
        SQLiteDatabase sqLiteDatabase = getMyWritableDatabase();
        EmployeeTable employeeTable = new EmployeeTable();
        return sqLiteDatabase.rawQuery("Select * from "+ employeeTable.getTableName()+";", null);
    }
}
