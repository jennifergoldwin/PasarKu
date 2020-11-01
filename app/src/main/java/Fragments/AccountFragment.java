package Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pasar_ku.Handle;
import com.example.pasar_ku.HomeActivity;
import com.example.pasar_ku.R;
import Model.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btnLogOut;
    private TextView tvEmail;
    private TextView tvPhone;
    private ImageView imageView;


    ViewGroup root;
//    FirebaseUser user;
//    DatabaseReference databaseReference;
//    String UID;
//    GoogleSignInAccount signInAccount;

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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

        getUserData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = (ViewGroup) inflater.inflate(R.layout.fragment_account, container, false);
        btnLogOut = root.findViewById(R.id.account_fragment_btn_logout);
        tvEmail = root.findViewById(R.id.account_fragment_tv_email);
        tvPhone = root.findViewById(R.id.account_fragment_tv_phone);
        imageView = root.findViewById(R.id.account_fragment_url_image);



        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Sign Out");
                alertDialog.setMessage("Are you sure you want to sign out?");
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Sign Out",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Handle.mAuth.signOut();
                                getActivity().finish();
                            }
                        });
                alertDialog.show();
            }
        });
        return root;
    }

    private void getUserData() {
//        signInAccount = GoogleSignIn.getLastSignedInAccount(getContext());
//        user = FirebaseAuth.getInstance().getCurrentUser();
//        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
//        UID = user.getUid();
        if (Handle.signInAccount!=null){

            tvEmail.setText(Handle.signInAccount.getEmail());
            tvPhone.setText("Set Phone Number");
            Glide.with(getContext()).load(Handle.signInAccount.getPhotoUrl()).placeholder(R.drawable.ic_account)
                    .into(imageView);
        }

        Handle.databaseReference.child(Handle.UID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                if (user != null){
                    tvEmail.setText(user.getEmail());
                    tvPhone.setText(user.getPhoneNumber());
                    Picasso.get().load(R.drawable.ic_account).into(imageView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}