package com.fast_ad.fast_ad.activities.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.fast_ad.fast_ad.R;
import com.fast_ad.fast_ad.activities.GetAddressActivity;
import com.fast_ad.fast_ad.components.DialogHelper;

public class RapideActivity extends AppCompatActivity {

    EditText paymethod, destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapide);

        paymethod = findViewById(R.id.paymethod);
        destination = findViewById(R.id.destination);

        paymethod.setFocusable(false);

        paymethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogHelper dialogHelper = new DialogHelper(RapideActivity.this,getApplicationContext());
                 dialogHelper.optionPaydialog();
            }
        });

        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RapideActivity.this, GetAddressActivity.class));
            }
        });


    }
}
