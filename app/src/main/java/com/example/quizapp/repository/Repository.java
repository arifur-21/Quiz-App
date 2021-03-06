package com.example.quizapp.repository;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.quizapp.models.CategoryModel;
import com.example.quizapp.models.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private String COLLECTION_CATEGORIES = "categories";
    private String COLLECTION_USERS = "UserModel";
    private FirebaseFirestore db;

    public Repository()
    {
        db = FirebaseFirestore.getInstance();
    }

public MutableLiveData<List<CategoryModel>> getAllCategories()
{
    MutableLiveData<List<CategoryModel>> mutableLiveData = new MutableLiveData<>();
    db.collection(COLLECTION_CATEGORIES).addSnapshotListener(new EventListener<QuerySnapshot>() {
        @Override
        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
            List<CategoryModel>list = new ArrayList<>();
            list.clear();
            for (DocumentSnapshot snapshot : value.getDocuments()){
                CategoryModel model = new CategoryModel();
              //  model.setCaterogyId(snapshot.getId());
                list.add(snapshot.toObject(CategoryModel.class));
            }
            mutableLiveData.postValue(list);
        }
    });
    return mutableLiveData;

}

/*
public MutableLiveData<List<Users>> getWalletCoins()
{
    MutableLiveData<List<Users>> mutableLiveData = new MutableLiveData<>();
    String uid = FirebaseAuth.getInstance().getUid();
    db.collection(COLLECTION_USERS).document(uid)
            .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
        @Override
        public void onSuccess(DocumentSnapshot documentSnapshot) {
           */
/* List<UserModel> list = new ArrayList<>();
           *//*
*/
/* list.add(documentSnapshot.toObject(UserModel.class));
            mutableLiveData.postValue(list)*//*
*/
/*
            UserModel userModel = documentSnapshot.toObject(UserModel.class);
            userModel.getCoins();
            list.add(userModel)*//*
;
            Users userModel = documentSnapshot.toObject(Users.class);
            mutableLiveData.postValue((List<Users>) userModel);

        }
    });
    return mutableLiveData;
}
*/





}
