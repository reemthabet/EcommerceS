package com.example.ecommerces;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button loginBtn;
TextView signUp ;
FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginBtn=findViewById(R.id.buttonLogin);
        signUp=findViewById(R.id.textViewSignUp);
        loginBtn.setOnClickListener(this);
        signUp.setOnClickListener(this);
        mAuth=FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
            finish();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLogin:
           startActivity(new Intent(MainActivity.this,LoginActivity.class));
                break;
            case R.id.textViewSignUp:
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
                break;

        }
    }
}