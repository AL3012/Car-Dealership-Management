package com.example.cardealershipmanagement;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cardealershipmanagement.Database.DBHelper;
import com.example.cardealershipmanagement.Database.EmployeeTable;

import java.util.Calendar;

public class NewEmployeeActivity extends AppCompatActivity {

    private EditText dobEt, fname, lname, emailEt, mobile, password, city, pincode, username, position;
    private int year, month, day;
    private RadioGroup genderRb;
    private Button EmpBtn;
    DBHelper dbHelper;

    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_employee);

        fname = findViewById(R.id.first_nameEt);
        lname = findViewById(R.id.last_nameEt);
        emailEt = findViewById(R.id.emailEt);
        mobile = findViewById(R.id.mobileEt);
        password = findViewById(R.id.password);
        city = findViewById(R.id.cityEt);
        pincode = findViewById(R.id.pincodeEt);
        position = findViewById(R.id.EPost);
        username = findViewById(R.id.Username);
        EmpBtn = findViewById(R.id.AddBtn);

        genderRb = findViewById(R.id.Gender);
        dobEt = findViewById(R.id.dob);
        dobEt.setEnabled(false);

        ImageView calendarIcon = findViewById(R.id.calendar_icon);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        dobEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate(year, month, day);
            }
        });

        dbHelper = DBHelper.getInstance(this);

        EmpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.AddBtn) {
                    String nameF = fname.getText().toString();
                    String nameL = lname.getText().toString();
                    String email = emailEt.getText().toString();
                    String mobileF = mobile.getText().toString();
                    String City = city.getText().toString();
                    String dob = dobEt.getText().toString();
                    String Position = position.getText().toString();
                    String Pincode = pincode.getText().toString();
                    String Password = password.getText().toString();
                    String Username = username.getText().toString();

                    int id = genderRb.getCheckedRadioButtonId();
                    if(id == R.id.Male) {
                        gender = "Male";
                    } else if(id == R.id.Female) {
                        gender = "Female";
                    } else if(id == R.id.other){
                        gender = "Other";
                    }

                    if (nameF.isEmpty()) {
                        fname.setError("Enter First Name");
                    }
                    else if (nameL.isEmpty()) {
                        lname.setError("Enter Last Name");
                    }
                    else if (!email.contains("@") || !email.contains(".") || email.lastIndexOf('.') < email.indexOf('@') || email.lastIndexOf('.') == email.length() - 1 || email.lastIndexOf('.') - email.indexOf('@') <= 2) {
                        emailEt.setError("Enter Valid Email");
                    }
                    else if (mobileF.length() != 10 || mobileF.matches(".*\\D+.*")) {
                        mobile.setError("Enter Valid Mobile No.");
                    }
                    else if (!Password.matches(".*\\d+.*") || !Password.matches(".*[a-zA-Z].*")
                            || Password.isEmpty()) {
                        password.setError("Enter Valid Password");
                    }
                    else if (City.isEmpty()) {
                        city.setError("Enter City");
                    }
                    else if (Pincode.length() != 6 || Pincode.matches(".*\\D+.*")) {
                        pincode.setError("Enter Valid Pincode");
                    }
                    else if (dob.isEmpty()) {
                        dobEt.setError("Enter Date of Birth");
                    }
                    else if(Position.isEmpty()) {
                        position.setError("Enter Valid Position");
                    }
                    else if(gender.isEmpty()) {
                        Toast.makeText(NewEmployeeActivity.this, "Select Gender", Toast.LENGTH_LONG).show();
                    }
                    else if(Username.isEmpty() || Username.length()<=6){
                        username.setError("Enter valid Username");
                    }
                    else {
                        addEmployee(v);
                    }
                }
            }
        });
    }

    private void addEmployee(View v) {
        EmployeeTable inputData = new EmployeeTable(fname.getText().toString(),lname.getText().toString(),
                emailEt.getText().toString(), mobile.getText().toString(), dobEt.getText().toString(),
                position.getText().toString(), gender, city.getText().toString(), pincode.getText().toString(),
                username.getText().toString(), password.getText().toString());

        if (dbHelper.addEmployeeData(inputData)) {
            Toast.makeText(this, "Data Stored", Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Employee Added Successfully!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Data Not Stored", Toast.LENGTH_LONG).show();
        }
    }

    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int y, int m, int d) {
            year = y;
            month = m+1;
            day = d;
            showDate(year, month, day);
        }
    };

    private void showDate(int year, int month, int day) {
        dobEt.setText(day+"/"+ month +"/"+year);
    }
}
