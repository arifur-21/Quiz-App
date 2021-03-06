package com.example.quizapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.quizapp.models.CategoryModel;
import com.example.quizapp.models.Users;
import com.example.quizapp.repository.Repository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {
private Repository repository;
    public CategoryViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();

    }

    public MutableLiveData<List<CategoryModel>> fetchAllCategories()
    {
      return repository.getAllCategories();
    }

    /*public MutableLiveData<List<Users>> fetchData()
    {
      return repository.getWalletCoins();
    }*/
}
