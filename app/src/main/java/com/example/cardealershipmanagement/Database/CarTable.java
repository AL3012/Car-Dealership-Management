package com.example.cardealershipmanagement.Database;

import android.database.sqlite.SQLiteDatabase;

public class CarTable {
    private String tableName = "CarTable";
    private String id = "id";
    private String make = "make";
    private String model = "model";
    private String year = "year";
    private String color = "color";
    private String vin = "vin";
    private String bType = "bType";
    private String status = "status";
    private String buyingPrice = "buyingPrice";
    private String sellingPrice = "sellingPrice";
    private String seats = "seats";
    private String fType = "fType";

    public CarTable(){
    }

    public CarTable(String make, String model, String year, String color, String vin, String bType, String status, String buyingPrice, String sellingPrice, String seats, String fType) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.vin = vin;
        this.bType = bType;
        this.status = status;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.seats = seats;
        this.fType = fType;
    }

    public CarTable(SQLiteDatabase sqLiteDatabase){

        BuyerTable buyerTable = new BuyerTable();
        String sql = "create table "+tableName+"( "+id+" integer, "+make+ " varchar(20), "+model+
                " varchar(20), "+year+" int(4), "+color+" varchar(10), "+vin+ " varchar(20), "+bType+
                " varchar(10), "+status+" varchar(25), "+buyingPrice+ " int(10), "+sellingPrice+
                " int(10), "+seats+" int(2), "+fType+" varchar(10), FOREIGN KEY ("+id+") REFERENCES "+
                buyerTable.getTableName()+"("+ buyerTable.getId() +") );";

        String initSql = "insert into "+tableName+"("+make+", "+model+", "+year+", "+color+", "+vin+
                ", "+bType+", "+status+", "+buyingPrice+", "+sellingPrice+", "+seats+", "+fType+
                ") values ('Dodge','Challenger',1970,'Blue','4572JKAUWN129','Muscle','Sold',1826370,2145860,2,'Gas')";

        sqLiteDatabase.execSQL(sql);
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

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getbType() {
        return bType;
    }

    public void setbType(String bType) {
        this.bType = bType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(String buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }
}
