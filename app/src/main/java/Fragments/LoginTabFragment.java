package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pasar_ku.Handle;
import com.example.pasar_ku.HomeActivity;
import com.example.pasar_ku.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.concurrent.Executor;

public class LoginTabFragment extends Fragment {

    ViewGroup root;

    private int RC_SIGN_IN = 1;

    private TextView textViewRegister;
    private Button buttonLogin;
    private ProgressBar progressBar;
    public TextInputLayout mTILEmail, mTILPassword;
    private EditText mEDTEmail,mEDTPassword;

    void initView(){
        mTILEmail = root.findViewById(R.id.id_activity_login_iptlayout_username);
        mTILPassword = root.findViewById(R.id.id_activity_login_iptlayout_password);

        mEDTEmail = root.findViewById(R.id.id_activity_login_edt_username);
        mEDTPassword = root.findViewById(R.id.id_activity_login_edt_password);

        buttonLogin = root.findViewById(R.id.id_activity_login_btn_login);
//        signInButton = root.findViewById(R.id.id_activity_login_google_login);

        progressBar = root.findViewById(R.id.activity_login_progress_bar);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container,false);

        initView();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser();
            }
        });
       return root;
    }

    private void signInUser() {
        String email = mEDTEmail.getText().toString().trim();
        String pass = mEDTPassword.getText().toString().trim();

        if (email.isEmpty()){

            return;

        }

        if (pass.isEmpty()){

            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        Handle.mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(getContext(), HomeActivity.class));
                }
                else{
                    Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//
    private void updateUI(FirebaseUser user) {
        startActivity(new Intent(getContext(),HomeActivity.class));
    }
}
