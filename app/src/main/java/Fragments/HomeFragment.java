package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pasar_ku.DetailPasarActivity;
import com.example.pasar_ku.Handle;
import com.example.pasar_ku.HomeActivity;
import com.example.pasar_ku.ListPasarActivity;
import com.example.pasar_ku.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

import Model.Pasar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private LinearLayout linearLayout;

    FirestoreRecyclerAdapter adapter;


    Query query;
    ViewGroup root;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    public void onStart() {
        super.onStart();


        FirestoreRecyclerOptions<Pasar> options = new FirestoreRecyclerOptions.Builder<Pasar>()
                .setQuery(query,Pasar.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<Pasar, PasarViewHolder>(options) {
            @NonNull
            @Override
            public PasarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_home_pasar_recycler_view,
                        parent,false);
                return new PasarViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull PasarViewHolder pasarViewHolder, int i, @NonNull Pasar pasar) {
                pasarViewHolder.namaPasar.setText(pasar.getNama());

                String str = pasar.getAlamat().substring(0,30) + "....";
                pasarViewHolder.alamatPasar.setText(str);
                String jlh = " " + pasar.getJlhToko() + " Toko";
                pasarViewHolder.jlhToko.setText(jlh);
                Picasso.get().load(pasar.getImage()).into(pasarViewHolder.imageView);

                pasarViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), DetailPasarActivity.class);
                        intent.putExtra("Image",pasar.getImage());
                        intent.putExtra("Nama",pasar.getNama());
                        intent.putExtra("Alamat",pasar.getAlamat());
                        intent.putExtra("Jlh",pasar.getJlhToko());
                        startActivity(intent);
                    }
                });

            }

        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        linearLayout = root.findViewById(R.id.fragment_home_seeall_linear_layout);

        recyclerView = root.findViewById(R.id.fragment_home_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));


        query =  FirebaseFirestore.getInstance().collection("listpasar").limit(5);

        linearLayout.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(), ListPasarActivity.class));
    }

    private class PasarViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView namaPasar;
        private TextView alamatPasar;
        private TextView jlhToko;

        public PasarViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.template_img);
            namaPasar = itemView.findViewById(R.id.template_nama_pasar);
            alamatPasar = itemView.findViewById(R.id.template_alamat);
            jlhToko = itemView.findViewById(R.id.template_toko);



        }
    }
}