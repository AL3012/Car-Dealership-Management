package com.example.cardealershipmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cardealershipmanagement.Database.CarTable;
import com.example.cardealershipmanagement.Database.DBHelper;

public class AddCarActivity extends AppCompatActivity {

    private EditText make, model, year, buyingPrice, sellingPrice, color, vin, ftype, btype, status, seats;
    private Button carBtn;
    DBHelper dbHelper;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        make = findViewById(R.id.make);
        model = findViewById(R.id.model);
        year = findViewById(R.id.Year);
        buyingPrice = findViewById(R.id.BuyPrice);
        sellingPrice = findViewById(R.id.SellPrice);
        color = findViewById(R.id.color);
        vin = findViewById(R.id.Vin);
        ftype = findViewById(R.id.fuel_type);
        btype = findViewById(R.id.body_type);
        status = findViewById(R.id.status);
        seats = findViewById(R.id.seat);

        carBtn = findViewById(R.id.AddCarBtn);

        dbHelper = DBHelper.getInstance(this);

        carBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Make = make.getText().toString();
                String Model = model.getText().toString();
                String Year = year.getText().toString();
                String BuyingPrice = buyingPrice.getText().toString();
                String SellingPrice = sellingPrice.getText().toString();
                String Color = color.getText().toString();
                String Vin = vin.getText().toString();
                String Ftype = ftype.getText().toString();
                String Btype = btype.getText().toString();
                String Status = status.getText().toString();
                String Seat = seats.getText().toString();

                if(Make.isEmpty()) {
                    make.setError("Enter Make/Brand");
                } else if(Model.isEmpty()) {
                    model.setError("Enter Model");
                } else if(Year.isEmpty()) {
                    year.setError("Enter Year");
                } else if(BuyingPrice.isEmpty()) {
                    buyingPrice.setError("Enter Buying Price");
                } else if(SellingPrice.isEmpty()) {
                    sellingPrice.setError("Enter Selling Price");
                } else if(Color.isEmpty()) {
                    color.setError("Enter Color");
                } else if(Vin.isEmpty()) {
                    vin.setError("Enter Vin");
                } else if(Ftype.isEmpty()) {
                    ftype.setError("Enter Fuel Type");
                } else if(Btype.isEmpty()) {
                    btype.setError("Enter Body Type");
                } else if(Seat.isEmpty()) {
                    seats.setError("Enter Seating Capacity");
                } else if(Status.isEmpty()) {
                    status.setError("Enter Current Status of Car");
                } else {
                    AddCar(v);
                }
            }
        });
    }

    private void AddCar(View v) {
        CarTable inputData = new CarTable(make.getText().toString(), model.getText().toString(),
                year.getText().toString(), color.getText().toString(), vin.getText().toString(),
                btype.getText().toString(), status.getText().toString(), buyingPrice.getText().toString(),
                sellingPrice.getText().toString(), seats.getText().toString(), ftype.getText().toString());

        if (dbHelper.addCarData(inputData)) {
            Toast.makeText(this, "Data Stored", Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Car Details Added Successfully!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Data Not Stored", Toast.LENGTH_LONG).show();
        }
    }
}
