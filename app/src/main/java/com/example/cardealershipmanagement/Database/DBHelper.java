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
        new LoginTable(sqLiteDatabase);
        new EmployeeTable(sqLiteDatabase);
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

    public boolean addLoginData(LoginTable loginTable)
    {
        try{
            LoginTable keyUserName = new LoginTable();
            SQLiteDatabase sqLiteDatabase = getMyWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(keyUserName.getUsername(), loginTable.getUsername());
            contentValues.put(keyUserName.getPassword(), loginTable.getPassword());

            sqLiteDatabase.insert(keyUserName.getTableName(), null, contentValues);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /*public Cursor getLoginData() {
        SQLiteDatabase sqLiteDatabase = getMyWritableDatabase();
        LoginTable loginTable = new LoginTable();

        return sqLiteDatabase.rawQuery("select * from "+loginTable.getTableName()+";", null);
    }*/

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
}
