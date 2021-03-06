package com.example.quizapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.adapter.CategoryAdapter;
import com.example.quizapp.databinding.FragmentHomeBinding;
import com.example.quizapp.models.CategoryModel;
import com.example.quizapp.viewModel.CategoryViewModel;
import com.example.quizapp.viewModel.LoginViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class HomeFragment extends Fragment {

private FragmentHomeBinding binding;
private LoginViewModel loginViewModel;
private CategoryViewModel categoryViewModel;
private FirebaseAuth auth;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_item,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {

            case R.id.logOutId:
                loginViewModel.LogOut();
                Toast.makeText(getActivity(),"Log Out",Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater);
        loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        categoryViewModel = new ViewModelProvider(getActivity()).get(CategoryViewModel.class);
        auth = FirebaseAuth.getInstance();


      /*  loginViewModel.authenticationMutableLiveData.observe(getViewLifecycleOwner(), new Observer<LoginViewModel.Authentication>() {
            @Override
            public void onChanged(LoginViewModel.Authentication authentication) {
                if (authentication == LoginViewModel.Authentication.UNAUTHENTICATION)
                {
                    Navigation.findNavController(container).navigate(R.id.action_homeFragment_to_loginFragment);
                }
            }
        });*/

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            binding.spinwheelId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).navigate(R.id.action_navigationLayoutFragment_to_spinnerFragment);
                }
            });


        categoryViewModel.fetchAllCategories().observe(getViewLifecycleOwner(), new Observer<List<CategoryModel>>() {
            @Override
            public void onChanged(List<CategoryModel> categoryModels) {

                CategoryAdapter adapter = new CategoryAdapter(getActivity(),categoryModels);
                binding.categoryRecycleViewId.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                binding.categoryRecycleViewId.setAdapter(adapter);
            }
        });





    }
}