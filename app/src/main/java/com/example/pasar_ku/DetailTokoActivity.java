package com.example.pasar_ku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.graphics.PorterDuff;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import Adapter.DetailPasarAdapter;
import Adapter.DetailTokoAdapter;

public class DetailTokoActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;

    private ImageView imgToko;
    private ImageView imgPenjual;
    private TextView ratingToko;
    private TextView namaPenjual;
    private TextView namaToko;
    private TextView namaPasar;
    private TextView toolbarTextView;

    void initView(){
        tabLayout = findViewById(R.id.activity_detail_toko_tab_layout);
        viewPager = findViewById(R.id.activity_detail_toko_tab_view_pager);
        toolbar = findViewById(R.id.toolbar2);
        toolbarTextView = findViewById(R.id.activity_detail_toko_toolbar_nama);

        imgPenjual = findViewById(R.id.activity_detail_toko_imgPenjual);
        imgToko = findViewById(R.id.activity_detail_toko_img_toko);
        imgToko.setColorFilter(R.color.black, PorterDuff.Mode.OVERLAY);
        ratingToko = findViewById(R.id.activity_detail_toko_rating);
        namaPenjual = findViewById(R.id.activity_detail_toko_namaPenjual);
        namaToko = findViewById(R.id.activity_detail_toko_namaToko);
        namaPasar = findViewById(R.id.activity_detail_toko_namaPasar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_toko);

        initView();
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_button);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getDataFromIntent();

        tabLayout.addTab(tabLayout.newTab().setText("Semua Produk"));
        tabLayout.addTab(tabLayout.newTab().setText("Popular"));
        tabLayout.addTab(tabLayout.newTab().setText("Terlaris"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final DetailTokoAdapter detailTokoAdapter = new DetailTokoAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        viewPager.setAdapter(detailTokoAdapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    private void getDataFromIntent() {
        String imgTokoStr = getIntent().getStringExtra("imgToko");
        String imgPenjualStr = getIntent().getStringExtra("imgPenjual");
        String namaPasarStr = getIntent().getStringExtra("namaPasar");
        String namaTokoStr = getIntent().getStringExtra("namaToko");
        String namaPenjualStr = getIntent().getStringExtra("namaPenjual");
        String ratingTokoStr = getIntent().getStringExtra("ratingToko");

        Glide.with(this).load(imgTokoStr).into(imgToko);
        Glide.with(this).load(imgPenjualStr).into(imgPenjual);

        namaPasar.setText(namaPasarStr);
        namaPenjual.setText(namaPenjualStr);
        namaToko.setText(namaTokoStr);
        ratingToko.setText(ratingTokoStr);
        toolbarTextView.setText(namaTokoStr);


    }


}