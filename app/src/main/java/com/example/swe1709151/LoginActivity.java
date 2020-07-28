package com.example.swe1709151;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private EditText editLoginEmail, editLoginPassword;
    private Button btnLogin;
    private Button btnReHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        editLoginEmail = findViewById(R.id.editLoginEmail);
        editLoginPassword = findViewById(R.id.editLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnReHere = findViewById(R.id.btnReHere);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editLoginEmail.getText().toString();
                String password = editLoginPassword.getText().toString();
                boolean authentication = db.userAuthentication(email, password);
                if(authentication==true){
                    Toast.makeText(getApplicationContext(),
                            "Login successfully",
                            Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Wrong email or password",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        btnReHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}