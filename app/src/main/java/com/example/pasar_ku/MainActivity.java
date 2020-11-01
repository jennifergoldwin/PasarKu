package com.example.pasar_ku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Objects.requireNonNull(getSupportActionBar()).hide();

        Handle.init(this);
        new Handler().postDelayed(runnable,1000);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (Handle.mAuth.getCurrentUser() != null) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            } else {
                new Handler().postDelayed(() -> {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }, 2000);
            }
        }
    };

}