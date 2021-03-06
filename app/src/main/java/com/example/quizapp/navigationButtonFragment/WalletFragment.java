package com.example.quizapp.navigationButtonFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.quizapp.databinding.FragmentWalletBinding;
import com.example.quizapp.models.Users;
import com.example.quizapp.viewModel.CategoryViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class WalletFragment extends Fragment {

    private FragmentWalletBinding binding;
private CategoryViewModel categoryViewModel;
private FirebaseFirestore db;
private Users users;


    public WalletFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentWalletBinding.inflate(inflater);

       users = new Users();
       db = FirebaseFirestore.getInstance();
      // categoryViewModel = new ViewModelProvider(getActivity()).get(CategoryViewModel.class);


        db.collection("UserModel").document(FirebaseAuth.getInstance().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                users = documentSnapshot.toObject(Users.class);
                binding.currentCoins.setText(String.valueOf(users.getCoins()));

            }
        });


       return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}