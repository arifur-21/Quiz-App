package com.example.quizapp.navigationButtonFragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizapp.adapter.LeaderbordAdapter;
import com.example.quizapp.databinding.FragmentLeaderbordBinding;
import com.example.quizapp.models.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class LeaderbordFragment extends Fragment {

    private FragmentLeaderbordBinding binding;
    private FirebaseFirestore database;
    private ProgressDialog progressDialog;

    public LeaderbordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentLeaderbordBinding.inflate(inflater);
        database = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(getActivity());


        ArrayList<Users> usersArrayList = new ArrayList<>();
        progressDialog.show();
        final LeaderbordAdapter adapter = new LeaderbordAdapter(getActivity(),usersArrayList);
        binding.leaderboredReccleId.setAdapter(adapter);
        binding.leaderboredReccleId.setLayoutManager(new LinearLayoutManager(getActivity()));



        database.collection("UserModel")
                .orderBy("coins", Query.Direction.DESCENDING)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (DocumentSnapshot snapshot : queryDocumentSnapshots)
                {
                    Users users = snapshot.toObject(Users.class);
                    usersArrayList.add(users);
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

        });

        return binding.getRoot();


    }
}