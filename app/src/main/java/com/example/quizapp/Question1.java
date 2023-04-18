package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Question1 extends AppCompatActivity {

    private RadioButton radioButton1, radioButton2;
    private Button nextButton;
    private int score = 0;
    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        radioButton1 = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        nextButton = findViewById(R.id.disconnect);
        scoreTextView = findViewById(R.id.score);
        scoreTextView.setText("Your Score: " + score + "/3");
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButton2.isChecked()) {
                    score++;
                }
                // Update the score display
                scoreTextView.setText("Your Score: " + score + "/3");

                Intent intent = new Intent(getApplicationContext(), Question2.class);
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            }
        });

        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButton1.isChecked()) {
                    // Do not decrease score if the answer is incorrect
                    radioButton2.setChecked(false);
                }
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButton2.isChecked()) {
                    // Do not decrease score if the answer is correct
                    radioButton1.setChecked(false);
                }
            }
        });
    }
}
