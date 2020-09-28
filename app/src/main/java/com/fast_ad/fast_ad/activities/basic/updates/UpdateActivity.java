package com.fast_ad.fast_ad.activities.basic.updates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.fast_ad.fast_ad.R;

public class UpdateActivity extends AppCompatActivity {

    ImageView toback;
    RelativeLayout passwordrelativeupdate,numberrelativeupdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        passwordrelativeupdate = findViewById(R.id.passwordrelativeupdate);
        numberrelativeupdate = findViewById(R.id.numberrelativeupdate);
        toback = findViewById(R.id.toback);

        toback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Animatoo.animateSlideLeft(UpdateActivity.this);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
