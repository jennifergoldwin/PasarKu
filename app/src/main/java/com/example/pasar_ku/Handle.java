package com.example.pasar_ku;

import android.content.Context;
import android.content.SharedPreferences;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import Model.Pasar;

public class Handle {

    public static GoogleSignInAccount signInAccount;
    public static FirebaseUser user;
    public static DatabaseReference databaseReference;
    public static String UID;

    public static FirebaseAuth mAuth;
    public static FirebaseFirestore firebaseFirestore;

    public static CollectionReference collectionReference;


    public static FirestoreRecyclerOptions<Pasar> options;
    public static void init(Context context){
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        collectionReference = firebaseFirestore.collection("listpasar");
        signInAccount = GoogleSignIn.getLastSignedInAccount(context);
        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        UID = user.getUid();

    }
}
