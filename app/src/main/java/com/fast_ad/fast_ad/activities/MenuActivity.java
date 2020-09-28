package com.fast_ad.fast_ad.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fast_ad.fast_ad.R;
import com.fast_ad.fast_ad.activities.basic.FaqActivity;
import com.fast_ad.fast_ad.activities.basic.HelpActivity;
import com.fast_ad.fast_ad.activities.basic.PaymentActity;
import com.fast_ad.fast_ad.activities.basic.UserActivity;
import com.fast_ad.fast_ad.components.DialogHelper;
import com.fast_ad.fast_ad.fragments.Acceuil;

import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    ImageView drawabletoggle;
    Acceuil currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        currentFragment = new Acceuil();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        setFragment(currentFragment);


        drawabletoggle =  findViewById(R.id.user);

        LinearLayout definitagain = findViewById(R.id.definitagain);
        definitagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogHelper dialogHelper = new DialogHelper(MenuActivity.this, getApplicationContext());
                dialogHelper.optiondialog();
            }
        });

        drawabletoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.userinfo) {
            startActivity(new Intent(MenuActivity.this, UserActivity.class));
        } else if (id == R.id.paymentmethod) {
            startActivity(new Intent(MenuActivity.this, PaymentActity.class));
        } else if (id == R.id.help) {
            startActivity(new Intent(MenuActivity.this, HelpActivity.class));
        } else if (id == R.id.parrainage) {

        }
        else if (id == R.id.promocode) {

        }
        else if (id == R.id.faq) {
            startActivity(new Intent(MenuActivity.this, FaqActivity.class));
        }

        else if (id == R.id.logout) {
            DialogHelper dialogHelper = new DialogHelper(MenuActivity.this,getApplicationContext());
            dialogHelper.LogoutVerifi();
        }

        currentFragment = new Acceuil();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    public  void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }
}
