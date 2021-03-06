package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quizapp.databinding.ActivityResultBinding;
import com.example.quizapp.fragments.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class ResultActivity extends AppCompatActivity {

    private ActivityResultBinding binding;
    int POINTS = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int correctAnswers = getIntent().getIntExtra("correct",0);
        int totalQuestions = getIntent().getIntExtra("total",0);

        int points = correctAnswers * POINTS;

        binding.score.setText(String.format("%d/%d",correctAnswers, totalQuestions));
        binding.earnedCoins.setText(String.valueOf(points));


        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection("UserModel")
                .document(FirebaseAuth.getInstance().getUid())
                .update("coins", FieldValue.increment(points));

    }
}