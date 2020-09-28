package com.fast_ad.fast_ad.activities.product;

import android.content.Intent;
import android.os.Bundle;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.fast_ad.fast_ad.R;

public class ProductListActivity extends AppCompatActivity {

    EditText searchprodorlocate;
    ImageView gotomenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchprodorlocate =  toolbar.findViewById(R.id.searchprodorlocate);
        searchprodorlocate.requestFocus();
        gotomenu = findViewById(R.id.gotomenu);
        gotomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideLeft(ProductListActivity.this);
    }
}
