package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pasar_ku.Handle;
import com.example.pasar_ku.HomeActivity;
import com.example.pasar_ku.R;
import Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterTabFragment extends Fragment {

    ViewGroup root;

    public TextInputLayout mTILEmail, mTILPassword, mTILConfirmPassword, mTILPhone;
    private EditText mEDTEmail,mEDTPassword,mEDTConfirmPassword, mEDTPhone;

    public Button btnRegister;
    private ProgressBar progressBar;


    void initView(){
        mTILEmail = root.findViewById(R.id.id_activity_register_iptlayout_email);
        mTILPassword = root.findViewById(R.id.id_activity_register_iptlayout_password);
        mTILConfirmPassword = root.findViewById(R.id.id_activity_register_iptlayout_cpass);
        mTILPhone = root.findViewById(R.id.id_activity_register_iptlayout_phone);

        mEDTEmail= root.findViewById(R.id.id_activity_register_edt_email);
        mEDTPassword = root.findViewById(R.id.id_activity_register_edt_password);
        mEDTConfirmPassword = root.findViewById(R.id.id_activity_register_edt_cpass);
        mEDTPhone = root.findViewById(R.id.id_activity_register_edt_phone);

        btnRegister = root.findViewById(R.id.id_activity_register_btn_register);
        progressBar = root.findViewById(R.id.activity_register_progress_bar);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.register_tab_fragment,container,false);

        initView();


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        return root;
    }
    private void registerUser() {
        String email = mEDTEmail.getText().toString().trim();
        String pass = mEDTPassword.getText().toString().trim();
        String cpass = mEDTConfirmPassword.getText().toString().trim();
        String phone = mEDTPhone.getText().toString().trim();

        if (email.isEmpty()){
            mTILEmail.setError("Email is required!");
            mTILEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEDTEmail.setError("Please provide valid email!");
            mEDTEmail.requestFocus();
            return;
        }
        if (phone.isEmpty()){
            mEDTPhone.setError("Phone Number is required!");
            mEDTPhone.requestFocus();
            return;
        }
        if (pass.isEmpty() ){
            mEDTPassword.setError("Password is required!");
            mEDTPassword.requestFocus();
            return;
        }
        if (cpass.isEmpty()){
            mEDTConfirmPassword.setError("Confirm Password is required!");
            mEDTConfirmPassword.requestFocus();
            return;
        }


        if (!Patterns.PHONE.matcher(phone).matches()){
            mEDTPhone.setError("Please provide valid Phone Number!");
            mEDTPhone.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        Handle.mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d("jenjen","hi");

                            User user = new User(email,phone);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(Handle.mAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(getContext(),"Success",Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                        startActivity(new Intent(getContext(), HomeActivity.class));
                                    }
                                    else {
                                        Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(getContext(),task.getException().toString(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}
