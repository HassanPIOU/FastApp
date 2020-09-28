package com.fast_ad.fast_ad.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.fast_ad.fast_ad.R;
import com.fast_ad.fast_ad.common.Api;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password,regphoneregister,emailregister;
    ImageView close;
    TextView tologin, regiter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        close = findViewById(R.id.close);
        tologin = findViewById(R.id.tologin);
        regiter = findViewById(R.id.regiter);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, StartActivity.class);
                startActivity(i);
                Animatoo.animateSlideDown(RegisterActivity.this);
            }
        });

        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                Animatoo.animateFade(RegisterActivity.this);
            }
        });

        username = findViewById(R.id.regusername);
        password = findViewById(R.id.regpassword);
        regphoneregister = findViewById(R.id.regphoneregister);
        emailregister = findViewById(R.id.emailregister);



        regiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
    }

    private void Register() {
        final String fullname = username.getEditableText().toString().trim();
       final String reg_password = password.getEditableText().toString().trim();
        final String reg_email = emailregister.getEditableText().toString().trim();
        final String reg_phone = regphoneregister.getEditableText().toString().trim();

            if(fullname.isEmpty()){
                username.setError("Veuillez saisir votre Nom");
            }
            else if(reg_password.isEmpty()){
                password.setError("Veuillez saisir votre mot de passe");
            }
            else if(reg_email.isEmpty()){
                emailregister.setError("Veuillez saisir votre Email");
            }
            else if(reg_phone.isEmpty()){
                regphoneregister.setError("Veuillez saisir votre numero de telephone");
            }
             else  {
            Registery(fullname,reg_phone,reg_email,reg_password);
        }
    }


    private void Registery(final String fullname, final String reg_phone, final String reg_email, final String reg_password) {
        final SimpleArcDialog mDialog = new SimpleArcDialog(this);
        ArcConfiguration configuration = new ArcConfiguration(this);
        configuration.setText("Inscription en cours...");
        mDialog.setConfiguration(configuration);
        mDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    String  status = json.getString("status");
                    if(status.equals("000")){
                        JSONArray jsonArray = json.getJSONArray("information");
                        for (int i = 0 ; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String auth_key = jsonObject.getString("auth_key").trim();
                            Intent  intent = new Intent(RegisterActivity.this,ConfirmationActivity.class);
                            intent.putExtra("auth_key",auth_key);
                            startActivity(intent);
                            finish();
                        }

                    }else{
                        String message = json.getString("message");
                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }

                } catch (JSONException e) {
                    mDialog.dismiss();
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this, "Erreur de connexion veuillez Réessayer", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Erreur de connexion Internet veuillez Réessayer", Toast.LENGTH_SHORT).show();
                    }
                }

        ){
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("access_token", Api.TOKEN);
                map.put("nom", fullname);
                map.put("username", reg_phone);
                map.put("email", reg_email);
                map.put("password", reg_password);
                map.put("pays", "214");
                map.put("registration_id", "");
                return  map;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(RegisterActivity.this);
    }
}
