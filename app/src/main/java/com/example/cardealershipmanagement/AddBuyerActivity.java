package com.example.cardealershipmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cardealershipmanagement.Database.BuyerTable;
import com.example.cardealershipmanagement.Database.DBHelper;

public class AddBuyerActivity extends AppCompatActivity {

    private EditText fname, lname, mobile, address, aadhar_no, license_no;
    private RadioGroup genderRb;
    private Button buyerBtn;
    DBHelper dbHelper;
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_buyer);

        fname = findViewById(R.id.first_nameEt);
        lname = findViewById(R.id.last_nameEt);
        mobile = findViewById(R.id.mobileEt);
        address = findViewById(R.id.address);
        aadhar_no = findViewById(R.id.aadharEt);
        license_no = findViewById(R.id.licenseEt);
        genderRb = findViewById(R.id.Gender);
        buyerBtn = findViewById(R.id.BuyerBtn);

        dbHelper = DBHelper.getInstance(this);

        buyerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameF = fname.getText().toString();
                String nameL = lname.getText().toString();
                String mobileF = mobile.getText().toString();
                String Address = address.getText().toString();
                String aadhar = aadhar_no.getText().toString();
                String license = license_no.getText().toString();

                int id = genderRb.getCheckedRadioButtonId();
                if(id == R.id.Male){
                    gender = "Male";
                } else if(id == R.id.Female) {
                    gender = "Female";
                }else if (id == R.id.other){
                    gender = "Other";
                }

                if (nameF.isEmpty()) {
                    fname.setError("Enter First Name");
                }
                else if (nameL.isEmpty()) {
                    lname.setError("Enter Last Name");
                }
                else if (mobileF.isEmpty() || mobileF.length()!=10 || mobileF.matches(".*\\D+.*")) {
                    mobile.setError("Enter Valid Mobile No.");
                }
                else if(Address.isEmpty()) {
                    address.setError("Address is necessary");
                }
                else if(aadhar.isEmpty()) {
                    aadhar_no.setError("Aadhar Number has to be entered");
                }
                else if(gender.isEmpty()) {
                    Toast.makeText(AddBuyerActivity.this, "Select Gender", Toast.LENGTH_LONG).show();
                }
                else if(license.isEmpty()) {
                    license_no.setError("Licencse Number has to entered");
                }
                else{
                    addBuyer(v);
                }
            }
        });

    }

    private void addBuyer(View v) {
        BuyerTable inputData = new BuyerTable(fname.getText().toString(), lname.getText().toString(),
                address.getText().toString(), mobile.getText().toString(), gender,
                aadhar_no.getText().toString(), license_no.getText().toString());

        if (dbHelper.addBuyerData(inputData)) {
            Toast.makeText(this, "Data Stored", Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Buyer Details Added Successfully!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Data Not Stored", Toast.LENGTH_LONG).show();
        }
    }
}
