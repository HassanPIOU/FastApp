package com.fast_ad.fast_ad.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fast_ad.fast_ad.Models.Places;
import com.fast_ad.fast_ad.Models.Service;
import com.fast_ad.fast_ad.R;
import com.fast_ad.fast_ad.adapters.PlacesAdapter;
import com.fast_ad.fast_ad.common.Api;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GetAddressActivity extends AppCompatActivity {

    private List<Places> placesList ;
    RecyclerView myrecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_address);

        placesList = new ArrayList<>();
           myrecyclerview = (RecyclerView) findViewById(R.id.places_recycler);
          jsonRequest();
        }


    private void jsonRequest() {
        final SimpleArcDialog mDialog = new SimpleArcDialog(this);
        ArcConfiguration configuration = new ArcConfiguration(this);
        configuration.setText("Chargement en cours...");
        mDialog.setConfiguration(configuration);
        mDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.URL_ADDRESS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    String status = json.getString("status");
                    if(status == "000") {
                    JSONArray services = json.getJSONArray("information");

                    for (int i = 0 ; i < services.length(); i++){
                        JSONObject jsonObject = services.getJSONObject(i);

                        Places places = new Places();
                        places.setKey(jsonObject.getString("adresse_key").trim());
                        places.setName(jsonObject.getString("designation"));
                        places.setZone(jsonObject.getString("zone"));
                        places.setTarif(jsonObject.getJSONArray("tarif"));
                        placesList.add(places);
                        mDialog.dismiss();
                    }
                    }else{
                        String message = json.getString("message");
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }

                } catch (JSONException e) {
                    mDialog.dismiss();
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error1 "+ e.toString(), Toast.LENGTH_SHORT).show();
                }

                setuprecyclerview(placesList);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mDialog.dismiss();
                        Toast.makeText(getApplicationContext(), String.valueOf(error), Toast.LENGTH_SHORT).show();
                    }
                }

        ){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("access_token", Api.TOKEN);
                map.put("u_identifiant", Api.U_Identifiant);
                return  map;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }


    private void setuprecyclerview(List<Places> placesList) {
        PlacesAdapter placesAdapter = new PlacesAdapter(getApplicationContext(), placesList,GetAddressActivity.this);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        myrecyclerview.setAdapter(placesAdapter);
    }
}
