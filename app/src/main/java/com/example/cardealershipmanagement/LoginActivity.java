package com.example.cardealershipmanagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardealershipmanagement.Database.DBHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText UsernameEt, PasswordEt;
    private TextView forgot;
    DBHelper dbHelper;
    private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UsernameEt = findViewById(R.id.usernameEt);
        PasswordEt = findViewById(R.id.passwordEt);
        dbHelper = DBHelper.getInstance(this);

        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.loginBtn) {
                String username = UsernameEt.getText().toString();
                String password = PasswordEt.getText().toString();

                if (username.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Enter Username", Toast.LENGTH_LONG).show();
                }
                else if (password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Enter Password", Toast.LENGTH_LONG).show();
                }
                else if (password.length() < 8) {
                    Toast.makeText(LoginActivity.this, "Password must have atleast 8 characters.", Toast.LENGTH_LONG).show();
                }

                else {
                    performLogin();
                }
            }
        }
    });
}

private void performLogin() {
        Cursor cursor = dbHelper.getLoginData();
        boolean flag = false;
        cursor.moveToFirst();
        String Username = UsernameEt.getText().toString();
        String Password = PasswordEt.getText().toString();
        do {
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            if(Username.equals(username) && Password.equals(password)) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                flag = true;
                String name = cursor.getString(2);

                SharedPreferences sharedPreferences = getSharedPreferences("DIR", Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("SKEY_username",""+UsernameEt.getText().toString()).commit();
                sharedPreferences.edit().putString("SKEY_pass",""+PasswordEt.getText().toString()).commit();
                sharedPreferences.edit().putString("SKEY","Hello, "+name).commit();

            }
        }while (cursor.moveToNext());
        if(!flag) {
            Toast.makeText(this, "Wrong Username/Password",Toast.LENGTH_LONG).show();
        }
    }
}