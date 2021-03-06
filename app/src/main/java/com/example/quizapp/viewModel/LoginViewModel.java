package com.example.quizapp.viewModel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginViewModel extends AndroidViewModel {
    private FirebaseAuth auth;
    private FirebaseFirestore database;
    public MutableLiveData<String> errorMsg;
    public MutableLiveData<Authentication> authenticationMutableLiveData;


    public LoginViewModel(@NonNull Application application) {
        super(application);
        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        errorMsg = new MutableLiveData<>();

        authenticationMutableLiveData = new MutableLiveData<>();


        if (auth.getCurrentUser() == null)
        {
            authenticationMutableLiveData.postValue(Authentication.UNAUTHENTICATION);
        }
        else {
            authenticationMutableLiveData.postValue(Authentication.AUTHENTICATION);
        }
    }



    public void register(String name, String email, String passwored, String postcode)
    {
        auth.createUserWithEmailAndPassword(email,passwored).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                authenticationMutableLiveData.postValue(Authentication.AUTHENTICATION);
            }
        })
      .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplication(), "Error"+e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void login(String email, String password)
    {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                authenticationMutableLiveData.postValue(Authentication.AUTHENTICATION);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplication(),"Error"+e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void LogOut()
    {
        if (auth.getCurrentUser() != null)
        {
            auth.signOut();
            authenticationMutableLiveData.postValue(Authentication.UNAUTHENTICATION);
        }
    }

    public enum Authentication{
        AUTHENTICATION, UNAUTHENTICATION
    }
}
