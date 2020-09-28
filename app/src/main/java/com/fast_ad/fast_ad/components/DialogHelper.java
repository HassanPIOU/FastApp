package com.fast_ad.fast_ad.components;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.fast_ad.fast_ad.R;
import com.fast_ad.fast_ad.Utils.Helper;
import com.fast_ad.fast_ad.Utils.SessionManager;
import com.fast_ad.fast_ad.activities.GetAddressActivity;
import com.fast_ad.fast_ad.activities.LoginActivity;
import com.fast_ad.fast_ad.activities.MainActivity;
import com.fast_ad.fast_ad.activities.MenuActivity;
import com.fast_ad.fast_ad.activities.PositionActivity;
import com.fast_ad.fast_ad.activities.StartActivity;
import com.fast_ad.fast_ad.database.UserDbHelper;
import com.fast_ad.fast_ad.database.UserLocation;
import com.google.android.gms.maps.model.LatLng;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;

import org.w3c.dom.Text;

import java.util.HashMap;

public class DialogHelper {

    Activity activity;
    Context mContext;
    private UserDbHelper db;
    SessionManager sessionManager;
    UserLocation userLocation;
    public DialogHelper(Activity activity, Context mContext) {
        this.activity = activity;
        this.mContext = mContext;
        db = new UserDbHelper(activity);
        sessionManager = new SessionManager(mContext);
        userLocation = new UserLocation(activity.getApplicationContext());
    }

    public  void  positioncheckdialog(){
        final Dialog dialoglogout = new Dialog(activity);
        dialoglogout.setContentView(R.layout.dialog_position_check);
        dialoglogout.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialoglogout.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;


        ((TextView) dialoglogout.findViewById(R.id.shareposition)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.requestMultiplePermissions(activity);
            }
        });

        ((TextView) dialoglogout.findViewById(R.id.positionafter)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(activity.getApplicationContext(), StartActivity.class);
                activity.startActivity(start);
                activity.finish();
                Animatoo.animateSlideLeft(activity);
                Helper.savePrefsData(activity.getApplicationContext());
            }
        });
        dialoglogout.show();
        dialoglogout.getWindow().setAttributes(lp);
    }


    public  void  optiondialog(){
        final Dialog optiondialog = new Dialog(activity);
        optiondialog.setContentView(R.layout.dialog_choose_position);
        optiondialog.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(optiondialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        optiondialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        UserLocation userLocatio = new UserLocation(activity);
        if (userLocatio.getUserSaveLocation().size() > 0){
            HashMap<String, String> user  = userLocatio.getUserSaveLocation();
             optiondialog.findViewById(R.id.pointchooselinear).setVisibility(View.VISIBLE);
             String address =  GetLocation.getCompleteAddressString(activity, Double.valueOf(user.get("latitude")),Double.valueOf(user.get("longitude")));
           TextView textView = (TextView) optiondialog.findViewById(R.id.pointchoosed);
          textView.setText(String.valueOf(address));
        }

        ((LinearLayout) optiondialog.findViewById(R.id.actualplace)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optiondialog.dismiss();
                Dexter.withActivity(activity)
                        .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                final SimpleArcDialog mDialog = new SimpleArcDialog(activity);
                                ArcConfiguration configuration = new ArcConfiguration(activity);
                                configuration.setText("Chargement en cours...");
                                mDialog.setConfiguration(configuration);
                                mDialog.show();
                                 LatLng location =  GetLocation.position(activity);
                                 userLocation.addLocation(location.longitude,location.latitude);
                                Intent i = new Intent(activity, MenuActivity.class);
                                activity.startActivity(i);
                                Animatoo.animateFade(activity);
                                mDialog.dismiss();
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                if (response.isPermanentlyDenied()) {

                                } else {
                                    Intent i = new Intent(activity, MenuActivity.class);
                                    activity.startActivity(i);
                                    Animatoo.animateFade(activity);
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(
                                    PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();

                optiondialog.dismiss();
            }

        });

        ((LinearLayout) optiondialog.findViewById(R.id.addplace)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optiondialog.dismiss();
                Intent start = new Intent(activity.getApplicationContext(), GetAddressActivity.class);
                activity.startActivity(start);
                Animatoo.animateSlideLeft(activity);

            }
        });
        optiondialog.show();
        optiondialog.getWindow().setAttributes(lp);
    }



    public void LogoutVerifi(){
        final Dialog dialoglogout = new Dialog(activity);
        dialoglogout.setContentView(R.layout.dialog_logout_warning);
        dialoglogout.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialoglogout.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


        ((TextView) dialoglogout.findViewById(R.id.logoutConf)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        ((TextView) dialoglogout.findViewById(R.id.logoutAnn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialoglogout.dismiss();

            }
        });

        dialoglogout.show();
        dialoglogout.getWindow().setAttributes(lp);
    }

    public void logoutUser() {
        sessionManager.setLogin(false);
        db.deleteUsers();
        userLocation.deletelocation();
        Intent intent = new Intent(activity, StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    public void optionPaydialog(){
        final Dialog optiondialog = new Dialog(activity);
        optiondialog.setContentView(R.layout.dialog_choose_payment_option);
        optiondialog.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(optiondialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        optiondialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        ((LinearLayout) optiondialog.findViewById(R.id.tmoney)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ((LinearLayout) optiondialog.findViewById(R.id.flooz)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        optiondialog.show();
        optiondialog.getWindow().setAttributes(lp);
    }

}
