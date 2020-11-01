package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pasar_ku.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;


import Model.Produk;

public class ProdukAdapter  extends FirestoreRecyclerAdapter<Produk, ProdukAdapter.ViewHolder> {

    private Context context;
    private OnItemClickListener listener;
    public ProdukAdapter(@NonNull FirestoreRecyclerOptions<Produk> options,Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ProdukAdapter.ViewHolder viewHolder, int i, @NonNull Produk produk) {
        Picasso.get().load(produk.getImage()).into(viewHolder.imgProduk);
        viewHolder.namaProduk.setText(produk.getNama());
        viewHolder.kategoriProduk.setText(produk.getKategori());
        viewHolder.hrgProduk.setText(produk.getHarga());
        viewHolder.ratingProduk.setText(produk.getRating());
    }

    @NonNull
    @Override
    public ProdukAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_list_produk_recycler_view,
                parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduk;
        TextView namaProduk;
        TextView kategoriProduk;
        TextView hrgProduk;
        TextView ratingProduk;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduk = itemView.findViewById(R.id.template_list_produk_imgView);
            namaProduk = itemView.findViewById(R.id.template_list_produk_namaProduk);
            kategoriProduk = itemView.findViewById(R.id.template_list_produk_kategoriProduk);
            hrgProduk = itemView.findViewById(R.id.template_list_produk_hrg);
            ratingProduk = itemView.findViewById(R.id.template_list_produk_ratingProduk);
        }
    }

    public interface OnItemClickListener{
        void OnItemClick(DocumentSnapshot snapshot, int pos);
    }

    public void setOnItemClickListener(ProdukAdapter.OnItemClickListener listener){
        this.listener = listener;
    }

    void click(DocumentSnapshot snapshot,int pos){
        listener.OnItemClick(getSnapshots().getSnapshot(pos),pos);
    }
}
