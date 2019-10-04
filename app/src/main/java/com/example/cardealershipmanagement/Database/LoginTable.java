package com.example.cardealershipmanagement.Database;

import android.database.sqlite.SQLiteDatabase;

public class LoginTable {
    private String tableName = "LoginTable";
    private String username = "username";
    private String password = "password";

    public LoginTable() {
    }

    public LoginTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+tableName+ " ("+username+" VARCHAR(20) PRIMARY KEY, "+password+" VARCHAR(20) );");

        sqLiteDatabase.execSQL("insert into "+tableName+ " values ('Admin', 'Admin123');");
    }

    public LoginTable(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
