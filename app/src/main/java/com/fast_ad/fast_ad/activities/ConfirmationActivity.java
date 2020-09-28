package com.fast_ad.fast_ad.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.chaos.view.PinView;
import com.fast_ad.fast_ad.R;
import com.fast_ad.fast_ad.common.Api;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ConfirmationActivity extends AppCompatActivity {

    TextView confirmValidation;
    PinView pinView;
    String auth_key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        auth_key =  getIntent().getStringExtra("auth_key");

        confirmValidation = findViewById(R.id.confirmValidation);
        pinView = findViewById(R.id.confirmationPin);

        String  pin = pinView.getEditableText().toString().trim();
        confirmation(pin);
    }


    private void confirmation(final String code) {
        final SimpleArcDialog mDialog = new SimpleArcDialog(this);
        ArcConfiguration configuration = new ArcConfiguration(this);
        configuration.setText("Verification en cours...");
        mDialog.setConfiguration(configuration);
        mDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.URL_CONFIRMATION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    String  status = json.getString("status");
                    if(status.equals("000")){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            pinView.setLineColor(getColor(R.color.quantum_googgreen));
                        }else{
                            pinView.setLineColor(Color.GREEN);
                        }
                            Intent  intent = new Intent(ConfirmationActivity.this,PolitiqueActivity.class);
                            startActivity(intent);
                            finish();

                    }else{
                        pinView.setLineColor(Color.RED);
                        String message = json.getString("message");
                        Toast.makeText(ConfirmationActivity.this, message, Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }

                } catch (JSONException e) {
                    mDialog.dismiss();
                    e.printStackTrace();
                    Toast.makeText(ConfirmationActivity.this, "Erreur de connexion veuillez Réessayer", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mDialog.dismiss();
                        Toast.makeText(ConfirmationActivity.this, "Erreur de connexion Internet veuillez Réessayer", Toast.LENGTH_SHORT).show();
                    }
                }

        ){
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("access_token", Api.TOKEN);
                map.put("u_identifiant", auth_key);
                map.put("code_validation", code);
                return  map;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
