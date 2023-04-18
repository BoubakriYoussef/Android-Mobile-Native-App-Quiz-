package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    //déclaration des objets

    EditText email;
    EditText password;
    Button sigin;
    TextView toregister;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //associer le layout à une classe java
        //accéder à une resource depuis class java
        setContentView(R.layout.activity_main);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        //recuperer les élements
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        sigin = findViewById(R.id.signin);
        toregister = findViewById(R.id.toregister);
        //setEventListner
        sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_ = email.getText().toString().trim();
                String password_ = password.getText().toString().trim();

                if(email_.isEmpty()){
                    email.setError("Enter your mail address");
                    email.requestFocus();
                    return;
                }

                if(password_.isEmpty()){
                    password.setError("Enter your password");
                    password.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email_, password_)
                        .addOnCompleteListener(MainActivity.this, task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(MainActivity.this, "Authentication succeeded.",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Question1.class);
                                startActivity(intent);

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

        toregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });




    }

}