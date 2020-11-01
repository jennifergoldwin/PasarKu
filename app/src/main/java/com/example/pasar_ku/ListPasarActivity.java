package com.example.pasar_ku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.FirebaseException;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.rpc.ErrorInfo;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import Adapter.FirebaseAdapter;
import Fragments.HomeFragment;
import Model.Pasar;

public class ListPasarActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseAdapter adapter;
    Query query;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pasar);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_button);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView = findViewById(R.id.activity_list_pasar_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        query = Handle.collectionReference;

        FirestoreRecyclerOptions<Pasar> options = new FirestoreRecyclerOptions.Builder<Pasar>()
                .setQuery(query,Pasar.class).build();

        adapter = new FirebaseAdapter(options,getApplicationContext(),false) ;

        recyclerView.setAdapter(adapter);
        adapter.startListening();


    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
        Toast.makeText(this,"jenii2",Toast.LENGTH_SHORT).show();
        adapter.setOnItemClickListener(new FirebaseAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(DocumentSnapshot snapshot, int pos) {
                Intent intent = new Intent(getApplicationContext(),DetailPasarActivity.class);

                Pasar pasar = snapshot.toObject(Pasar.class);
                intent.putExtra("Image", pasar.getImage());
                intent.putExtra("Nama",pasar.getNama());
                intent.putExtra("Alamat",pasar.getAlamat());
                intent.putExtra("Jlh",pasar.getJlhToko());
                startActivity(intent);

            }
        });
    }


}