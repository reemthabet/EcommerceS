package com.example.ecommerces;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
EditText emailLoginEdt,passwordLoginEdt;
Button login;
TextView signUpTxt;
ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailLoginEdt=findViewById(R.id.email_login);
        passwordLoginEdt=findViewById(R.id.login_pass);
        progressBar = findViewById(R.id.progressBarLoginActivity);
        login=findViewById(R.id.btn_login);
        signUpTxt=findViewById(R.id.signUp_text);
        login.setOnClickListener(this);
        signUpTxt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                progressBar.setVisibility(View.VISIBLE);
                String emailUser = emailLoginEdt.getText().toString().trim();
                String passwordUser = passwordLoginEdt.getText().toString().trim();

                if (emailUser.equals("") || passwordUser.equals("")) {
                    Toast.makeText(this, "Email or Password is Empty.", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(emailUser).matches()) {
                    Toast.makeText(this, "Email is badly formatted", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (passwordUser.length() < 6) {
                    Toast.makeText(this, "Password cannot be less than 6 letters.", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailUser, passwordUser).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        LoginActivity.this.finishAffinity();

                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });

                break;
            case R.id.signUp_text:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));

                break;

        }
    }

}