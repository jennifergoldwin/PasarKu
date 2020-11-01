package Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pasar_ku.DetailPasarActivity;
import com.example.pasar_ku.Handle;
import com.example.pasar_ku.ListPasarActivity;
import com.example.pasar_ku.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import Fragments.FavPasarFragment;
import Model.Pasar;

public class FirebaseAdapter extends FirestoreRecyclerAdapter<Pasar, FirebaseAdapter.ViewHolder> {

    private Context context;
    private boolean status;
    private OnItemClickListener listener;
    private FavPasarFragment fragment;

    public FirebaseAdapter(@NonNull FirestoreRecyclerOptions<Pasar> options, Context context, boolean status, FavPasarFragment fragment) {
        super(options);
        this.context = context;
        this.status = status;
        this.fragment = fragment;
    }

    public FirebaseAdapter(@NonNull FirestoreRecyclerOptions<Pasar> options,Context context,boolean status){
        super(options);
        this.context = context;
        this.status = status;
    }

    @Override
    protected void onBindViewHolder(@NonNull FirebaseAdapter.ViewHolder viewHolder, int i, @NonNull Pasar pasar) {
        viewHolder.namaPasar.setText(pasar.getNama());

        String str = pasar.getAlamat().substring(0,30) + "....";
        viewHolder.alamatPasar.setText(str);
        String jlh = " " + pasar.getJlhToko() + " Toko";
        viewHolder.jlhToko.setText(jlh);
        Picasso.get().load(pasar.getImage()).into(viewHolder.imageView);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragment!=null){
                    Intent intent = new Intent(context, DetailPasarActivity.class);
                    intent.putExtra("Image",pasar.getImage());
                    intent.putExtra("Nama",pasar.getNama());
                    intent.putExtra("Alamat",pasar.getAlamat());
                    intent.putExtra("Jlh",pasar.getJlhToko());
                    context.startActivity(intent);
                }
                else {
                    click(getSnapshots().getSnapshot(i),i);
                }
            }
        });
    }

    @NonNull
    @Override
    public FirebaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_list_pasar_recycler_view,
                parent, false);
        return new ViewHolder(v);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView namaPasar;
        private TextView alamatPasar;
        private TextView jlhToko;
        private ToggleButton toggleButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.template_list_imgView);
            namaPasar = itemView.findViewById(R.id.template_list_namaPasar);
            alamatPasar = itemView.findViewById(R.id.template_list_alamatPasar);
            jlhToko = itemView.findViewById(R.id.template_list_JlhToko);
            toggleButton = itemView.findViewById(R.id.template_list_toggle);

            toggleButton.setChecked(status);

            toggleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        int position=getAdapterPosition();
//                        if (getSnapshots().getSnapshot(getAdapterPosition()).exists())
//                            status = true;
//                        else status = false;
                        if(position != RecyclerView.NO_POSITION ){
                            DocumentSnapshot snapshot = getSnapshots().getSnapshot(position);
                            if (toggleButton.isChecked()){
                                Pasar pasar = snapshot.toObject(Pasar.class);
                                Map<String,Object> favPasar = new HashMap<>();
                                favPasar.put("Nama",pasar.getNama());
                                favPasar.put("Alamat",pasar.getAlamat());
                                favPasar.put("JlhToko",pasar.getJlhToko());
                                favPasar.put("Image",pasar.getImage());
                                Handle.firebaseFirestore.collection("Favourite").document(pasar.getNama())
                                        .set(favPasar);

                                Toast.makeText(context,"Success added to favourite!",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Pasar pasar = snapshot.toObject(Pasar.class);
                                Handle.firebaseFirestore.collection("Favourite").document(pasar.getNama())
                                        .delete();
                                Toast.makeText(context,"Success deleted to favourite!",Toast.LENGTH_SHORT).show();
                            }
                        }
                }
            });

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int pos = getAdapterPosition();
//                    if (pos!=RecyclerView.NO_POSITION && listener!=null){
//                        click(getSnapshots().getSnapshot(pos),pos);
//                    }
//                }
//            });
        }
    }


    public interface OnItemClickListener{
        void OnItemClick(DocumentSnapshot snapshot,int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    void click(DocumentSnapshot snapshot,int pos){
        listener.OnItemClick(getSnapshots().getSnapshot(pos),pos);
    }



}
