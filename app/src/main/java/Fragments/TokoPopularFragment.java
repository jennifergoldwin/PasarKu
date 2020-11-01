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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pasar_ku.DetailTokoActivity;
import com.example.pasar_ku.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

import Model.Toko;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TokoPopularFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TokoPopularFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ViewGroup root;
    private RecyclerView recyclerView;
    CollectionReference reference;
    FirestoreRecyclerAdapter adapter;
    public TokoPopularFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TokoPopularFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TokoPopularFragment newInstance(String param1, String param2) {
        TokoPopularFragment fragment = new TokoPopularFragment();
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
        root = (ViewGroup) inflater.inflate(R.layout.fragment_toko_popular, container, false);
        recyclerView = root.findViewById(R.id.fragment_toko_popular_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        String nama =getActivity().getIntent().getStringExtra("Nama");
        reference= FirebaseFirestore.getInstance().collection("listpasar").document(nama).collection("TokoBeruang");

        Query query = reference.limit(1);

        FirestoreRecyclerOptions<Toko> options = new FirestoreRecyclerOptions.Builder<Toko>()
                .setQuery(query,Toko.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Toko, TokoPopularFragment.TokoViewHolder>(options) {
            @NonNull
            @Override
            public TokoPopularFragment.TokoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_list_toko_recycler_view,
                        parent,false);
                return new TokoPopularFragment.TokoViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull TokoPopularFragment.TokoViewHolder tokoViewHolder, int i, @NonNull Toko toko) {
                Picasso.get().load(toko.getImage()).into(tokoViewHolder.imgToko);
                Glide.with(getContext()).load(toko.getImagePenjual()).placeholder(R.drawable.logo)
                        .into(tokoViewHolder.imgPenjual);

                tokoViewHolder.namaToko.setText(toko.getNama());
                tokoViewHolder.kategori.setText(toko.getKategori());
                tokoViewHolder.rating.setText(toko.getPenilaian());
                tokoViewHolder.namaPenjual.setText(toko.getPenjual());

                tokoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), DetailTokoActivity.class);
                        intent.putExtra("namaPasar",getActivity().getIntent().getStringExtra("Nama"));
                        intent.putExtra("namaPenjual",toko.getPenjual());
                        intent.putExtra("namaToko",toko.getNama());
                        intent.putExtra("ratingToko",toko.getPenilaian());
                        intent.putExtra("imgToko",toko.getImage());
                        intent.putExtra("imgPenjual",toko.getImagePenjual());
                        startActivity(intent);

                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);

        return root;
    }

    private class TokoViewHolder extends RecyclerView.ViewHolder {
        ImageView imgToko;
        ImageView imgPenjual;
        TextView rating;
        TextView kategori;
        TextView namaToko;
        TextView namaPenjual;

        public TokoViewHolder(@NonNull View itemView) {
            super(itemView);

            imgToko = itemView.findViewById(R.id.template_list_toko_imgView);
            imgPenjual = itemView.findViewById(R.id.template_list_toko_imgPenjual);
            rating = itemView.findViewById(R.id.template_list_toko_ratingToko);
            kategori = itemView.findViewById(R.id.template_list_toko_kategoriToko);
            namaToko = itemView.findViewById(R.id.template_list_toko_namaPasar);
            namaPenjual = itemView.findViewById(R.id.template_list_toko_namaPenjual);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
}