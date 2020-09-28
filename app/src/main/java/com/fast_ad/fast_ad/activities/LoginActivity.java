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
import com.fast_ad.fast_ad.Utils.SessionManager;
import com.fast_ad.fast_ad.common.Api;
import com.fast_ad.fast_ad.database.UserDbHelper;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
          TextView toregister, login;
          ImageView close;
          SessionManager sessionManager;
        private UserDbHelper db;

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toregister = findViewById(R.id.toregister);
        close = findViewById(R.id.close);

        db = new UserDbHelper(getApplicationContext());
        sessionManager = new SessionManager(getApplicationContext());



        login = findViewById(R.id.login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.setLogin(true);
                 final String login_username = username.getEditableText().toString().trim();
                String login_password = password.getEditableText().toString().trim();
                 if (login_username.isEmpty() || login_password.isEmpty()){

                    if(login_username.isEmpty()){
                        username.setError("Veuillez saisir votre Identifiant");
                    }

                    else if(login_password.isEmpty()){
                        password.setError("Veuillez saisir votre mot de passe");
                    }
                }


                if (!login_username.isEmpty() && !login_password.isEmpty()) {
                    Login(login_username,login_password);
                }

            }
        });


        toregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                 startActivity(i);
                Animatoo.animateFade(LoginActivity.this);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }



    private void Login(final String username, final String password) {
        final SimpleArcDialog mDialog = new SimpleArcDialog(this);
        ArcConfiguration configuration = new ArcConfiguration(this);
        configuration.setText("Chargement en cours...");
        mDialog.setConfiguration(configuration);
        mDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    boolean error = json.getBoolean("error");
                    if(!error)
                    {
                        JSONArray jsonArray = json.getJSONArray("information");
                        sessionManager.setLogin(true);
                        for (int i = 0 ; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String uid = jsonObject.getString("id").trim();
                            String name = jsonObject.getString("nom").trim();
                            String email = jsonObject.getString("email").trim();
                            String telephone = jsonObject.getString("username").trim();
                            String auth_key = jsonObject.getString("auth_key").trim();

                            final SimpleArcDialog mDialog = new SimpleArcDialog(LoginActivity.this);
                            ArcConfiguration configuration = new ArcConfiguration(LoginActivity.this);
                            configuration.setText("Connexion en cours...");
                            mDialog.setConfiguration(configuration);
                            mDialog.show();
                            db.addUser(name,email,telephone,auth_key);
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            finish();
                            mDialog.dismiss();
                           // dialogHelper.AccountVerify();
                        }
                    }else{
                        String message = json.getString("message");
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }

                } catch (JSONException e) {
                    mDialog.dismiss();
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Error1 "+ e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Erreur de connexion veuillez Réessayer", Toast.LENGTH_SHORT).show();
                    }
                }

        ){
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("access_token", Api.TOKEN);
                map.put("username", username);
                map.put("password", password);
                return  map;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSlideDown(LoginActivity.this);
    }
}
