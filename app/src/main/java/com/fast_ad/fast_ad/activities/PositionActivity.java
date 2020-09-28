package com.fast_ad.fast_ad.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.fast_ad.fast_ad.R;
import com.fast_ad.fast_ad.Utils.Helper;
import com.fast_ad.fast_ad.components.DialogHelper;
import com.fast_ad.fast_ad.plugins.places.DBAdapter;
import com.fast_ad.fast_ad.plugins.places.MyAdapter;
import com.fast_ad.fast_ad.plugins.places.PlaceModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class PositionActivity extends AppCompatActivity {

    DialogHelper dialogHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Helper.restorePrefData(getApplicationContext())) {
            Intent start = new Intent(getApplicationContext(), StartActivity.class);
            startActivity(start);
            finish();
        }
        setContentView(R.layout.activity_position);
        dialogHelper =  new DialogHelper(PositionActivity.this, getApplicationContext());
        dialogHelper.positioncheckdialog();
    }




}

