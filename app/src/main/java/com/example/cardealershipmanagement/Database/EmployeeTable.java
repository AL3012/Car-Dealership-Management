package com.example.cardealershipmanagement.Database;

import android.database.sqlite.SQLiteDatabase;

public class EmployeeTable {
    private String tableName = "EmployeeTable";
    private String id = "id";
    private String fname = "fname";
    private String lname = "lname";
    private String email = "email";
    private String mobile = "mobile";
    private String dob = "dob";
    private String position = "position";
    private String gender = "gender";
    private String city = "city";
    private String pincode = "pincode";
    private String username = "username";
    private String password = "password";

    public EmployeeTable() {
    }

    public EmployeeTable(String fname, String lname, String email, String mobile, String dob, String position, String gender, String city, String pincode, String username, String password) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.mobile = mobile;
        this.dob = dob;
        this.position = position;
        this.gender = gender;
        this.city = city;
        this.pincode = pincode;
        this.username = username;
        this.password = password;
    }

    public EmployeeTable(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table "+tableName+ " ("+id+" integer PRIMARY KEY AUTOINCREMENT, "+
                fname+" VARCHAR(20), " +lname+" VARCHAR(20), "+email+" varchar(100), "
                +mobile+" int(10), " +dob+" DATE, "+position+" varchar(20), "+gender+ " varchar(8),"
                +city+" varchar(30), "+pincode+" int(8), "+username+" varchar(20), "
                +password+" varchar(20));";

        sqLiteDatabase.execSQL(sql);

        String initSql = "insert into "+tableName+" values ('Anay', 'Gupta', 'guptaanay98@gmail.com', " +
                "9907378178, '1998-12-30', 'Owner', 'Male', 'Indore', '452009', 'ShowroomAdmin', 'Admin12345')";

        sqLiteDatabase.execSQL(initSql);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
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

