package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

    EditText email, name, password1, password2;
    Button SignupButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password1 = findViewById(R.id.password1);
        password2 = findViewById(R.id.password2);
        SignupButton = findViewById(R.id.register);
        name = findViewById(R.id.Name);

        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1,password1,password2,name1;
                email1 = email.getText().toString().trim();
                password1 = SignupActivity.this.password1.getText().toString().trim();
                password2 = SignupActivity.this.password2.getText().toString().trim();
                name1 = name.getText().toString().trim();

                if (TextUtils.isEmpty(name1)) {
                    name.setError("Enter Your name");
                    name.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(email1)) {
                    email.setError("Enter your mail address");
                    email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
                    email.setError("Invalid Mail Address");
                    email.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password1)) {
                    SignupActivity.this.password1.setError("Invalid Password");
                    SignupActivity.this.password1.requestFocus();
                    return;
                }

                if (password1.length() < 6) {
                    SignupActivity.this.password1.setError("Your password must contain atleast 6");
                    SignupActivity.this.password1.requestFocus();
                    return;
                }

                if (!password1.equals(password2)) {
                    SignupActivity.this.password2.setError("Passwords doesn't match each other");
                    SignupActivity.this.password2.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email1, password1)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "The account has been created",
                                            Toast.LENGTH_SHORT).show();
                                    // returns the application context of the current Android application
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);


                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(SignupActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });



        }
    }


