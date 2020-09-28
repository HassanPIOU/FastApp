package com.fast_ad.fast_ad.activities.basic.updates;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fast_ad.fast_ad.R;
import com.fast_ad.fast_ad.common.Api;
import com.fast_ad.fast_ad.database.UserDbHelper;
import com.google.android.material.snackbar.Snackbar;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NumberUpdateActivity extends AppCompatActivity {

    private UserDbHelper db;
    EditText oldnumber,newnumber;
    TextView submitupdatenumber;
    String phone, uid;
    RelativeLayout updatenumberlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_update);
        oldnumber = findViewById(R.id.oldnumber);
        newnumber = findViewById(R.id.newnumber);
        submitupdatenumber = findViewById(R.id.submitupdatenumber);

        updatenumberlayout = findViewById(R.id.updatenumberlayout);

        db = new UserDbHelper(getApplicationContext());

        HashMap<String, String> user = db.getUserDetails();
         phone = user.get("telephone");
        uid = user.get("uid");


        submitupdatenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldnum = oldnumber.getEditableText().toString().trim();
                String newnum = newnumber.getEditableText().toString().trim();

                if (!oldnum.equals(String.valueOf(phone))){
                    Snackbar snackbar = Snackbar
                            .make(updatenumberlayout, "Les numéro de téléphone ne sont pas identiques", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
               else{
                    if (newnum.isEmpty()){
                        Snackbar snackbar = Snackbar
                                .make(updatenumberlayout, "Veuillez renseigner votre nouveau numéro de téléphone", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                    if (oldnum.isEmpty()){
                        Snackbar snackbar = Snackbar
                                .make(updatenumberlayout, "Veuillez renseigner votre ancien numéro de téléphone", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }

                    else{
                        updatephonenumber(newnum, uid);
                    }

                }

            }
        });
    }

    private void updatephonenumber(String newnum, String uid) {
        updateSqlite(newnum,uid);
    }

    private void updateSqlite(String newnum, String uid) {
        UserDbHelper userDbHelper = new UserDbHelper(getApplicationContext());
           if (userDbHelper.updateProfil(newnum,uid)){
               updateOnline(newnum, uid);
           }else{
               Toast.makeText(this, "Mise à Jour  du numéro de téléphone échoué", Toast.LENGTH_SHORT).show();
           }
    }

    private void updateOnline(final String newnum, final String uid) {
        final SimpleArcDialog mDialog = new SimpleArcDialog(this);
        ArcConfiguration configuration = new ArcConfiguration(this);
        configuration.setText("Chargement en cours...");
        mDialog.setConfiguration(configuration);
        mDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.URL_PROFIL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    boolean error = json.getBoolean("error");
                    String message = json.getString("message");
                    if(!error)
                    {
                        Toast.makeText(NumberUpdateActivity.this, message, Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }else{
                        Toast.makeText(NumberUpdateActivity.this, message, Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }

                } catch (JSONException e) {
                    mDialog.dismiss();
                    e.printStackTrace();
                    Toast.makeText(NumberUpdateActivity.this, "Error1 "+ e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mDialog.dismiss();
                        Toast.makeText(NumberUpdateActivity.this, "Erreur de connexion veuillez Réessayer", Toast.LENGTH_SHORT).show();
                    }
                }

        ){
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("uid", uid);
                map.put("phone", newnum);
                return  map;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
