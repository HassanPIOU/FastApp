package com.fast_ad.fast_ad.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.fast_ad.fast_ad.R;

import com.fast_ad.fast_ad.activities.basic.FaqActivity;
import com.fast_ad.fast_ad.activities.basic.HelpActivity;
import com.fast_ad.fast_ad.components.DialogHelper;
import com.fast_ad.fast_ad.components.Statuts;
import com.fast_ad.fast_ad.database.UserLocation;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity  implements  NavigationView.OnNavigationItemSelectedListener{

    LinearLayout dialogtoggle;
     TextView defin;
     ImageView drawabletoggle, share;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView nav_view = findViewById(R.id.nav_view);
        RelativeLayout content_main_main = findViewById(R.id.content_main_main);
        if ( !Statuts.isNetworkAvaliable(getApplicationContext())){
            nav_view.setVisibility(View.INVISIBLE);
            content_main_main.setVisibility(View.INVISIBLE);

            Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse_second);
            ImageView noconnectionpic = findViewById(R.id.noconnectionpic);
            ImageView noconnectionpictorefresh = findViewById(R.id.noconnectionpictorefresh);
            noconnectionpic.startAnimation(pulse);

            noconnectionpictorefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recreateActivityCompat(MainActivity.this);
                }
            });
        }else{
            nav_view.setVisibility(View.VISIBLE);
            content_main_main.setVisibility(View.VISIBLE);

        }





        dialogtoggle  =  findViewById(R.id.dialogtoggle);
        defin = findViewById(R.id.defin);

        share = findViewById(R.id.share);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Envoyer une invitation");
                String shareMessage= "\nJe vous recommandes cette application\n\n";
                shareMessage = shareMessage + "https://glovoapp.com/get\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "Envoyer une invitation"));
            }
        });


        drawabletoggle = findViewById(R.id.userinfo);

        dialogtoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogHelper dialogHelper = new DialogHelper(MainActivity.this, getApplicationContext());
                 dialogHelper.optiondialog();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawabletoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer1 = findViewById(R.id.drawer_layout);
                drawer1.openDrawer(GravityCompat.START);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.help) {
            startActivity(new Intent(MainActivity.this, HelpActivity.class));
        }
        else if (id == R.id.faq) {
            startActivity(new Intent(MainActivity.this, FaqActivity.class));
        }

        else if (id == R.id.logout) {
            DialogHelper helper = new DialogHelper(MainActivity.this, getApplicationContext());
            helper.logoutUser();
        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Voulez vous quitter FastAd ???").setCancelable(false).setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    UserLocation userLocation = new UserLocation(getApplicationContext());
                    userLocation.deletelocation();
                    MainActivity.super.onBackPressed();
                }
            })
                    .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }


    @SuppressLint("NewApi")
    public final void recreateActivityCompat(final Activity a) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            a.recreate();
            Animatoo.animateShrink(MainActivity.this);
        } else {
            final Intent intent = a.getIntent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            a.finish();
            a.overridePendingTransition(0, 0);
            a.startActivity(intent);
            a.overridePendingTransition(0, 0);
            Animatoo.animateShrink(MainActivity.this);
        }
    }
}
