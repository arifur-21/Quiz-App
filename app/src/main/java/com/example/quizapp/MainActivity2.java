package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;


import com.example.quizapp.databinding.ActivityMain2Binding;
import com.example.quizapp.models.Question;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {


    ActivityMain2Binding binding;
    private ArrayList<Question> questions;
    int index = 0;
    private Question question;
    private CountDownTimer timer;
    private FirebaseFirestore db;
    private int correctAnswer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        questions = new ArrayList<>();
        db = FirebaseFirestore.getInstance();

        final String catId = getIntent().getStringExtra("catId");
        Random random = new Random();
       final int rend = random.nextInt(5);
                /*questions.add(new Question("whaet is","dfkdf","fieif","dofoe","ieir","oefkdf"));
                questions.add(new Question("whaet is","dfkdf","fieif","dofoe","ieir","oefkdf"));
                questions.add(new Question("whaet is","dfkdf","fieif","dofoe","ieir","oefkdf"));*/

        db.collection("categories")
                .document(catId)
                .collection("questions")
                .whereGreaterThanOrEqualTo("index",rend)
                .orderBy("index")
                .limit(5).get().addOnSuccessListener(queryDocumentSnapshots -> {
                    if (queryDocumentSnapshots.getDocuments().size() < 5)
                    {

                        db.collection("categories")
                                .document(catId)
                                .collection("questions")
                                .whereLessThanOrEqualTo("index",rend)
                                .orderBy("index")
                                .limit(5).get().addOnSuccessListener(queryDocumentSnapshots1 -> {
                                    for (DocumentSnapshot snapshot : queryDocumentSnapshots1)
                                    {
                                        Question question = snapshot.toObject(Question.class);
                                        questions.add(question);
                                    }
                                    setNextQuestion();
                                });
                    }
                    else {
                        for (DocumentSnapshot snapshot : queryDocumentSnapshots)
                        {
                            Question question = snapshot.toObject(Question.class);
                            questions.add(question);
                        }
                        setNextQuestion();
                    }
                });

        binding.quizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        resetTimer();


    }

    public void onClick(View view) {
        switch (view.getId()){

            case R.id.option_1:
            case R.id.option_2:
            case R.id.option_3:
            case R.id.option_4:

                if (timer != null)
                    timer.cancel();
                TextView selected = (TextView) view;
                checkAnswer(selected);
                break;

            case R.id.nextBtn:
                reset();
                if (index <= questions.size())
                {
                    index++;
                    setNextQuestion();
                }

                else {
                    Intent intent = new Intent(MainActivity2.this, ResultActivity.class);
                    intent.putExtra("correct",correctAnswer);
                    intent.putExtra("total",questions.size());
                    startActivity(intent);

                }
                break;
        }
    }


    private void setNextQuestion() {
        if (timer != null)
            timer.cancel();

        timer.start();

        if (index < questions.size()){
            binding.questionCounter.setText(String.format("%d/%d",(index+1),questions.size()));
            question = questions.get(index);
            binding.question.setText(question.getQuestion());
            binding.option1.setText(question.getOption1());
            binding.option2.setText(question.getOption2());
            binding.option3.setText(question.getOption3());
            binding.option4.setText(question.getOption4());
        }
    }
    private void checkAnswer(TextView textView)
    {
        String selectedAnswer = textView.getText().toString();
        if (selectedAnswer.equals(question.getAnswer()))
        {
            correctAnswer++;
            textView.setBackground(getResources().getDrawable(R.drawable.option_right));
        }
        else {
            showAnswer();
            textView.setBackground(getResources().getDrawable(R.drawable.option_wrong));
        }
    }
    private void reset()
    {
        binding.option1.setBackground(getResources().getDrawable(R.drawable.option_unselected));
        binding.option2.setBackground(getResources().getDrawable(R.drawable.option_unselected));
        binding.option3.setBackground(getResources().getDrawable(R.drawable.option_unselected));
        binding.option4.setBackground(getResources().getDrawable(R.drawable.option_unselected));
    }
    private void showAnswer()
    {
        if (question.getAnswer().equals(binding.option1.getText().toString()))
        {
            binding.option1.setBackground(getResources().getDrawable(R.drawable.option_right));
        }
       else if (question.getAnswer().equals(binding.option2.getText().toString()))
        {
            binding.option2.setBackground(getResources().getDrawable(R.drawable.option_right));
        }
      else if (question.getAnswer().equals(binding.option3.getText().toString()))
        {
            binding.option3.setBackground(getResources().getDrawable(R.drawable.option_right));
        }
        else if (question.getAnswer().equals(binding.option4.getText().toString()))
        {
            binding.option4.setBackground(getResources().getDrawable(R.drawable.option_right));
        }
    }

    private void resetTimer()
    {
        timer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                binding.timer.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {

            }
        };
    }
}