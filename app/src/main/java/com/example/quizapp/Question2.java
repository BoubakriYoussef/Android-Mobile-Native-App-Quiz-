package com.example.quizapp;

import static android.os.Build.VERSION_CODES.O;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class Question2 extends AppCompatActivity {

    private TextView textViewQuestion;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private Button buttonNext;
    private TextView scoreTextView;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);



        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        buttonNext = findViewById(R.id.disconnect);
        scoreTextView = findViewById(R.id.score);


        // récupération du score de la question précédente
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            score = extras.getInt("score");
        }


        scoreTextView.setText("Score: " + score + "/3");

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == radioButton1.getId()) {
                    score++;
                }
                // Update the score display
                scoreTextView.setText("Your Score: " + score + "/3");

                Intent intent = new Intent(Question2.this, Question3.class);
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            }
        });
    }
}
