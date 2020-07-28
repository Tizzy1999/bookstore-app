package com.example.swe1709151;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private EditText editEmail, editPassword, editConfirm;
    private Button btnRegister;
    private Button btnLoginR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editConfirm = findViewById(R.id.editConfirm);
        btnRegister = findViewById(R.id.btnRegister);
        btnLoginR = findViewById(R.id.btnLoginR);

        btnLoginR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();
                String confirm = editConfirm.getText().toString();
                if(email.equals("")||password.equals("")||confirm.equals("")){
                    Toast.makeText(getApplicationContext(),
                            "Fields are empty",
                            Toast.LENGTH_LONG).show();
                }else{
                    if(password.equals(confirm)){
                        boolean checkEmail = db.checkEmail(email);
                        // If email not registered, insert into the database.
                        if(checkEmail==true){
                            boolean insert = db.insert(email, password);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(),
                                        "Registered successfully",
                                        Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),
                                    "Email already registered",
                                    Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),
                                "Password do not matched",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}