package com.fast_ad.fast_ad.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.fast_ad.fast_ad.R;
import com.fast_ad.fast_ad.database.UserLocation;

public class SplashActivity extends AppCompatActivity {
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
        logo = findViewById(R.id.logopulse);
        logo.startAnimation(pulse);

        UserLocation userLocation = new UserLocation(SplashActivity.this);

        userLocation.deletelocation();

        final Intent i = new Intent(this, PolitiqueActivity.class);
        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(i);
                    Animatoo.animateSlideLeft(SplashActivity.this);
                    finish();
                }
            }
        };
        timer.start();
    }

}
