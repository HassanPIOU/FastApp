package com.fast_ad.fast_ad.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.fast_ad.fast_ad.R;
import com.fast_ad.fast_ad.Utils.SessionManager;
import com.fast_ad.fast_ad.database.UserDbHelper;

public class PolitiqueActivity extends AppCompatActivity {
    SessionManager sessionManager;
    private UserDbHelper db;
    TextView validate_politique;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politique);

         db = new UserDbHelper(getApplicationContext());
        sessionManager = new SessionManager(getApplicationContext());

        validate_politique = findViewById(R.id.validate_politique);

        validate_politique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.setLogin(true);
                db.addUser("Tester","tester.test@test.fr","22891121670","4654654655645454");
                Intent i = new Intent(PolitiqueActivity.this, MainActivity.class);
                startActivity(i);
                Animatoo.animateSlideRight(PolitiqueActivity.this);
            }
        });


    }
}
