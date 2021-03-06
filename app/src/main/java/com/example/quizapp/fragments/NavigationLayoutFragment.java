package com.example.quizapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.databinding.FragmentNavigationLayoutBinding;
import com.example.quizapp.fragments.HomeFragment;
import com.example.quizapp.navigationButtonFragment.LeaderbordFragment;
import com.example.quizapp.navigationButtonFragment.ProfileFragment;
import com.example.quizapp.navigationButtonFragment.WalletFragment;
import com.example.quizapp.viewModel.LoginViewModel;

import me.ibrahimsn.lib.OnItemSelectedListener;


public class NavigationLayoutFragment extends Fragment {

   private FragmentNavigationLayoutBinding binding;
   private LoginViewModel loginViewModel;

    public NavigationLayoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNavigationLayoutBinding.inflate(inflater);
        loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);

        loginViewModel.authenticationMutableLiveData.observe(getViewLifecycleOwner(), new Observer<LoginViewModel.Authentication>() {
            @Override
            public void onChanged(LoginViewModel.Authentication authentication) {
                if (authentication == LoginViewModel.Authentication.UNAUTHENTICATION)
                {

                    Navigation.findNavController(container).navigate(R.id.action_navigationLayoutFragment_to_loginFragment);
                }
            }
        });

        return binding.getRoot();
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.contentId, new HomeFragment());
        transaction.commit();

        binding.bottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                switch (i)
                {
                    case 0:
                        transaction.replace(R.id.contentId, new HomeFragment());
                        transaction.commit();
                        Toast.makeText(getActivity(),"Home",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        transaction.replace(R.id.contentId, new LeaderbordFragment());
                        transaction.commit();
                        Toast.makeText(getActivity(),"Rank",Toast.LENGTH_LONG).show();
                        break;

                    case 2:
                        transaction.replace(R.id.contentId, new WalletFragment());
                        transaction.commit();
                        Toast.makeText(getActivity(),"Wallet",Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        transaction.replace(R.id.contentId, new ProfileFragment());
                        transaction.commit();
                        Toast.makeText(getActivity(),"Profile",Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });
    }
}