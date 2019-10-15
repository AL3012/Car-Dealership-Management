package com.example.cardealershipmanagement.Database;

import android.database.sqlite.SQLiteDatabase;

public class BuyerTable {
    private String tableName = "BuyerTable";
    private String id = "id";
    private String vin = "vin";
    private String fname = "fname";
    private String lname = "lname";
    private String address = "address";
    private String mobile = "mobile";
    private String aadhar_no = "aadhar_no";
    private String gender = "gender";
    private String license = "license";

    public BuyerTable() {
    }

    public BuyerTable(String fname, String lname, String address, String mobile, String aadhar_no, String gender, String license, String vin) {
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.mobile = mobile;
        this.aadhar_no = aadhar_no;
        this.gender = gender;
        this.license = license;
        this.vin = vin;
    }

    public BuyerTable(SQLiteDatabase sqLiteDatabase) {

        CarTable carTable = new CarTable();
        String sql = "create table "+tableName+ " ("+id+" integer PRIMARY KEY AUTOINCREMENT, "
                +fname+" VARCHAR(20), " +lname+" VARCHAR(20), "+address+" varchar(150), " +mobile+" int(10), "
                +gender+" varchar(8), "+aadhar_no+ " int(15)," +license+" varchar(30), "+vin+" varchar(30)," +
                " FOREIGN KEY("+vin+") REFERENCES "+carTable.getTableName()+"("+carTable.getVin()+") );";

        sqLiteDatabase.execSQL(sql);
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAadhar_no() {
        return aadhar_no;
    }

    public void setAadhar_no(String aadhar_no) {
        this.aadhar_no = aadhar_no;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}
