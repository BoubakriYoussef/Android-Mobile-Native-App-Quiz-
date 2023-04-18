package com.example.quizapp;

import static android.os.Build.VERSION_CODES.O;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class Question3 extends AppCompatActivity {

    private TextView textViewQuestion;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private Button buttonNext;
    FirebaseAuth mAuth;
    private int score;
    private TextView scoreTextView;

    private Button disconnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);



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

        // affichage du score
        scoreTextView.setText("Score: " + score + "/3");

        // action du bouton "Next Question"
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // vérification de la réponse choisie
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == radioButton1.getId()) {
                    score++;
                }
                // Update the score display
                scoreTextView.setText("Score: " + score + "/3");
                // affichage du popup avec le score final
                AlertDialog.Builder builder = new AlertDialog.Builder(Question3.this);
                builder.setTitle("Quiz Score");
                builder.setMessage("Votre score final est : " + score);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }
}
