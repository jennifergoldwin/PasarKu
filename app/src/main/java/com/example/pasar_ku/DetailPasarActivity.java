package com.example.pasar_ku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import Adapter.DetailPasarAdapter;
import Adapter.LoginAdapter;
import Model.Pasar;

public class DetailPasarActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView imageView;
    private TextView namaPasar;
    private TextView alamatPasar;
    private TextView jlhToko;
    private Toolbar toolbar;

    void initview(){
        tabLayout = findViewById(R.id.activity_detail_pasar_tab_layout);
        viewPager = findViewById(R.id.activity_detail_pasar_tab_view_pager);
        imageView = findViewById(R.id.activity_detail_pasar_img);
        namaPasar = findViewById(R.id.activity_detail_pasar_nama);
        alamatPasar = findViewById(R.id.activity_detail_alamat);
        jlhToko = findViewById(R.id.activity_detail_jlh);
        toolbar = findViewById(R.id.toolbar1);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pasar);

        initview();

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_button);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tabLayout.addTab(tabLayout.newTab().setText("Semua Toko"));
        tabLayout.addTab(tabLayout.newTab().setText("Toko Popular"));
        tabLayout.addTab(tabLayout.newTab().setText("Kategori Toko"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final DetailPasarAdapter detailPasarAdapter = new DetailPasarAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        viewPager.setAdapter(detailPasarAdapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        Picasso.get().load(getIntent().getStringExtra("Image")).into(imageView);
        String strnama =getIntent().getStringExtra("Nama");
        namaPasar.setText(strnama);
        String str = getIntent().getStringExtra("Alamat").substring(0,40) + "....";
        alamatPasar.setText(str);
        String jlh = " " + getIntent().getStringExtra("Jlh") + " Toko";
        jlhToko.setText(jlh);

    }


}