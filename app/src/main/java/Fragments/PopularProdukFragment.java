package Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pasar_ku.Handle;
import com.example.pasar_ku.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;

import Adapter.ProdukAdapter;
import Model.Produk;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PopularProdukFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PopularProdukFragment extends Fragment {

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

    public PopularProdukFragment() {
        // Required empty public constructor
    }

    public static PopularProdukFragment newInstance(String param1, String param2) {
        PopularProdukFragment fragment = new PopularProdukFragment();
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
        root = (ViewGroup) inflater.inflate(R.layout.fragment_popular_produk, container, false);
        recyclerView = root.findViewById(R.id.fragment_popular_produk_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        String namaPasarStr = getActivity().getIntent().getStringExtra("namaPasar");
        String namaTokoStr = getActivity().getIntent().getStringExtra("namaToko");
        reference = Handle.firebaseFirestore.collection("listpasar")
                .document(namaPasarStr).collection("TokoBeruang")
                .document(namaTokoStr).collection("Produk");

        FirestoreRecyclerOptions<Produk> options = new FirestoreRecyclerOptions.Builder<Produk>()
                .setQuery(reference,Produk.class)
                .build();

        adapter = new ProdukAdapter(options,getContext()) ;

        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
}