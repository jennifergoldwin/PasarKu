package Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.pasar_ku.Handle;
import com.example.pasar_ku.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import Adapter.FirebaseAdapter;
import Model.Pasar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavPasarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavPasarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private LinearLayout linearLayout;
    private ViewGroup root;
    private FirestoreRecyclerAdapter adapter;
    private Query query;

    public FavPasarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavPasarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavPasarFragment newInstance(String param1, String param2) {
        FavPasarFragment fragment = new FavPasarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =(ViewGroup) inflater.inflate(R.layout.fragment_fav_pasar, container, false);
        linearLayout = root.findViewById(R.id.fragment_fav_pasar_linearlayout);
        recyclerView = root.findViewById(R.id.fragment_fav_pasar_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        query = Handle.firebaseFirestore.collection("Favourite");

        FirestoreRecyclerOptions<Pasar> options = new FirestoreRecyclerOptions.Builder<Pasar>()
                .setQuery(query,Pasar.class).build();

        adapter = new FirebaseAdapter(options,getContext(),true,FavPasarFragment.this) ;

        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();

    }



}