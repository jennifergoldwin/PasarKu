package com.example.pasar_ku;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import Adapter.LoginAdapter;

public class LoginActivity extends AppCompatActivity {

    private SignInButton signInButton;
    private GoogleSignInClient mGoogleSignInClient;
//    private FirebaseAuth mAuth;

    private int RC_SIGN_IN = 1;

//    private TextView textViewRegister;
//    private Button buttonLogin;
//    private ProgressBar progressBar;
//    public TextInputLayout mTILEmail, mTILPassword;
//    private EditText mEDTEmail,mEDTPassword;

    private TabLayout tabLayout;
    private ViewPager viewPager;


    void initView(){
//        mTILEmail = findViewById(R.id.id_activity_login_iptlayout_username);
//        mTILPassword = findViewById(R.id.id_activity_login_iptlayout_password);
//
//        mEDTEmail = findViewById(R.id.id_activity_login_edt_username);
//        mEDTPassword = findViewById(R.id.id_activity_login_edt_password);
//
//        textViewRegister = findViewById(R.id.activity_login_tv_register);
//        buttonLogin = findViewById(R.id.id_activity_login_btn_login);
        signInButton = findViewById(R.id.id_activity_login_google_login);
        tabLayout = findViewById(R.id.id_login_activity_tab_layout);
        viewPager = findViewById(R.id.id_login_activity_view_pager);

//        progressBar = findViewById(R.id.activity_login_progress_bar);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Register"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final LoginAdapter loginAdapter = new LoginAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        viewPager.setAdapter(loginAdapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

//        mAuth =FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                progressBar.setVisibility(View.VISIBLE);
                signIn();
            }
        });
//
//        textViewRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
//            }
//        });
//
//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signInUser();
//            }
//        });
    }

//    private void signInUser() {
//        String email = mEDTEmail.getText().toString().trim();
//        String pass = mEDTPassword.getText().toString().trim();
//
//        if (email.isEmpty()){
//
//            return;
//
//        }
//
//        if (pass.isEmpty()){
//
//            return;
//
//        }
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//
//            return;
//        }

//        progressBar.setVisibility(View.VISIBLE);
//        Handle.mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()){
////                    progressBar.setVisibility(View.GONE);
//                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
//                }
//                else{
//                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

    private void signIn() {
        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount acc = task.getResult(ApiException.class);
            FirebaseGoogleAuth(acc);
        }
        catch (ApiException e){
            //FirebaseGoogleAuth(null);
        }
    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acc) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(acc.getIdToken(),null);
        Handle.mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Success",Toast.LENGTH_SHORT).show();
//                    progressBar.setVisibility(View.GONE);
                    FirebaseUser user = Handle.mAuth.getCurrentUser();
                    updateUI(user);
                }
                else {
                    Toast.makeText(LoginActivity.this,"Error",Toast.LENGTH_SHORT).show();
//                    progressBar.setVisibility(View.GONE);
                    updateUI(null);

                }
            }
        });
    }

    private void updateUI(FirebaseUser user) {
        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
    }
}