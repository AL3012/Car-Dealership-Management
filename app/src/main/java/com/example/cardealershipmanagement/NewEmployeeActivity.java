package com.example.cardealershipmanagement;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Calendar;

public class NewEmployeeActivity extends AppCompatActivity {

    private EditText dobEt;
    private int year, month, day;
    private Button EmpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_employee);

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
