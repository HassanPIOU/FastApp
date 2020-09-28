package com.fast_ad.fast_ad.activities.basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fast_ad.fast_ad.R;
import com.fast_ad.fast_ad.activities.basic.updates.NumberUpdateActivity;
import com.fast_ad.fast_ad.activities.basic.updates.UpdateActivity;
import com.fast_ad.fast_ad.database.UserDbHelper;

import java.util.HashMap;

public class UserActivity extends AppCompatActivity {

    UserDbHelper db;
    TextView fullname_info,email_info;
    LinearLayout passwordchange,numberchange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        db = new UserDbHelper(getApplicationContext());
        HashMap<String, String> user = db.getUserDetails();
      String  fullname = user.get("name");
      String  email = user.get("email");


        fullname_info = findViewById(R.id.fullname_info);
        email_info = findViewById(R.id.email_info);

        passwordchange = findViewById(R.id.passwordchange);
        numberchange = findViewById(R.id.numberchange);

        fullname_info.setText(fullname);
        email_info.setText(email);

        passwordchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserActivity.this, UpdateActivity.class);
                startActivity(i);
            }
        });

        numberchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserActivity.this, NumberUpdateActivity.class);
                startActivity(i);
            }
        });
    }
}
