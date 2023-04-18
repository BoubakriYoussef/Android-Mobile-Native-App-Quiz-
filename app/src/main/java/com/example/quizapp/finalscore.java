package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class finalscore extends AppCompatActivity {


    private Button disconnect;
    private int score ;
    private TextView scoreTextView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);


        disconnect = findViewById(R.id.disconnect);
        scoreTextView = findViewById(R.id.score);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            score = extras.getInt("Score");
        }

        scoreTextView.setText("Your final score: " + score + "/3");

        //Disconnect
        disconnect.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(finalscore.this, MainActivity.class));
        });

    }
}
