package com.example.pasar_ku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Fragments.MessageFragment;
import Fragments.FavFragment;
import Fragments.HomeFragment;
import Fragments.AccountFragment;
import Fragments.OrderFragment;

public class HomeActivity extends AppCompatActivity {

    private HomeFragment homeFragment = new HomeFragment();
    private OrderFragment orderFragment = new OrderFragment();
    private FavFragment favFragment = new FavFragment();
    private MessageFragment messageFragment = new MessageFragment();
    private AccountFragment accountFragment = new AccountFragment();

    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;

//    public static GoogleSignInAccount signInAccount;
//    public static FirebaseUser user;
//    public static DatabaseReference databaseReference;
//    public static String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bottomNavigationView = findViewById(R.id.id_activity_home_bottom_nav);
        replaceFragment(homeFragment);

//
//        signInAccount = GoogleSignIn.getLastSignedInAccount(this);
//        user = FirebaseAuth.getInstance().getCurrentUser();
//        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
//        UID = user.getUid();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_home:
                    replaceFragment(homeFragment);
                    break;
                case R.id.menu_list:
                    replaceFragment(orderFragment);
                    break;
                case R.id.menu_fav:
                    replaceFragment(favFragment);
                    break;
                case R.id.menu_message:
                    replaceFragment(messageFragment);
                    break;
                case R.id.menu_account:
                    replaceFragment(accountFragment);
                    break;
            }

            return true;
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar,menu);
        return true;
    }

    private void replaceFragment(Fragment fragment){
        if(fragment!=null){
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            trans.replace(R.id.id_activity_home_frame_layout,fragment);
            trans.commit();
        }

    }
}