package com.example.quizapp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.databinding.FragmentSignUpBinding;
import com.example.quizapp.models.Users;
import com.example.quizapp.viewModel.LoginViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpFragment extends Fragment {
    private FragmentSignUpBinding binding;
private LoginViewModel loginViewModel;
private FirebaseFirestore  database;
private ProgressDialog progressDialog;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        database = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(getActivity());
        loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);

        binding = FragmentSignUpBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.createNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = binding.nameBox.getText().toString();
                final String email = binding.emailBox.getText().toString();
                final String password = binding.passwordBox.getText().toString();
                final String referCode = binding.referBox.getText().toString();
                    progressDialog.show();
                Users userModel = new Users(name,email,password,referCode);
                database.collection("UserModel").document().set(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        loginViewModel.register(name,email,password,referCode);
                        Toast.makeText(getActivity(),"Successfull",Toast.LENGTH_LONG).show();
                        Navigation.findNavController(v).navigate(R.id.action_signUpFragment_to_loginFragment);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(),"Error"+e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                    }
                });


            }
        });
    }
}