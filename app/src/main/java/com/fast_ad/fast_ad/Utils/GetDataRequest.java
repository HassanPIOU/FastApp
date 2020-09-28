package com.fast_ad.fast_ad.Utils;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GetDataRequest {

 Activity activity;

    public GetDataRequest(Activity activity) {
        this.activity = activity;
    }

    public JSONArray[] MainMenuData(String link , final String  telephone){
        final SimpleArcDialog mDialog = new SimpleArcDialog(activity);
        ArcConfiguration configuration = new ArcConfiguration(activity);
        configuration.setText("Chargement en cours...");
        mDialog.setConfiguration(configuration);
        mDialog.show();
        final JSONArray[] jsonElements = {null};
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    boolean error = json.getBoolean("error");
                    if(!error)
                    {
                        jsonElements[0] = json.getJSONArray("data");

                    }else{
                        String message = json.getString("message");
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }

                } catch (JSONException e) {
                    mDialog.dismiss();
                    e.printStackTrace();
                    Toast.makeText(activity, "Erreur de chargement des produits ", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mDialog.dismiss();
                        Toast.makeText(activity, "Erreur de connexion veuillez Réessayer", Toast.LENGTH_SHORT).show();
                    }
                }

        ){
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                    map.put("telephone", telephone);
                return  map;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(stringRequest);

        return jsonElements;

    }


}
