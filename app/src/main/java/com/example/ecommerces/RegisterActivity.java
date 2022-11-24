package com.example.ecommerces;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText nameEt, emailEt, passwordEt;
    private Button signUp;
    private TextView haveAccount, signin;
    private ProgressBar progressBar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameEt = findViewById(R.id.nameEdt);
        emailEt = findViewById(R.id.emailEdt);
        passwordEt = findViewById(R.id.passwordEdt);
        signin = findViewById(R.id.signUp_text);
        haveAccount = findViewById(R.id.have_account);
        signUp = findViewById(R.id.button_signup);
        progressBar = findViewById(R.id.progressBar_signup);
        signUp.setOnClickListener(this);
        signin.setOnClickListener(this);
        haveAccount.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_signup:

                progressBar.setVisibility(View.VISIBLE);
                String mName = nameEt.getText().toString().trim();
                String mEmail = emailEt.getText().toString().trim();
                String mPassword = passwordEt.getText().toString().trim();


                if (TextUtils.isEmpty(mName)) {
                    emailEt.setError("Name is Required.");
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (TextUtils.isEmpty(mEmail)) {
                    emailEt.setError("Email is Required.");
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
                    emailEt.setError("Email is badly formatted.");
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (TextUtils.isEmpty(mPassword)) {
                    emailEt.setError("Password is Required.");
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (mPassword.length() < 6) {
                    passwordEt.setError("Password Must be 6 Characters");
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        UserProfileChangeRequest.Builder request = new UserProfileChangeRequest.Builder();
                        request.setDisplayName(mName);
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        user.updateProfile(request.build());
                        HashMap<String, String> data = new HashMap<>();
                        data.put("name", mName);
                        data.put("email", mEmail);
                        data.put("password", mPassword);
                        String uid = user.getUid();
                        FirebaseFirestore.getInstance().collection("user").document(uid).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                                startActivity(intent);
                                RegisterActivity.this.finishAffinity();
                            }
                        });
                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                break;
            case R.id.signUp_text:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
            case R.id.have_account:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
        }
    }
}