package com.fast_ad.fast_ad.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.fast_ad.fast_ad.Models.Payment;
import com.fast_ad.fast_ad.Models.Service;
import com.fast_ad.fast_ad.R;
import com.fast_ad.fast_ad.Utils.GetDataRequest;
import com.fast_ad.fast_ad.activities.LoginActivity;
import com.fast_ad.fast_ad.activities.product.CoursierActivity;
import com.fast_ad.fast_ad.activities.product.ProductListActivity;
import com.fast_ad.fast_ad.activities.product.RapideActivity;
import com.fast_ad.fast_ad.adapters.ServiceAdapter;
import com.fast_ad.fast_ad.common.Api;
import com.fast_ad.fast_ad.components.Statuts;
import com.fast_ad.fast_ad.database.UserDbHelper;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class Acceuil extends Fragment implements View.OnClickListener {

    private List<Service> serviceList ;
    RecyclerView myrecyclerview;
    JsonArrayRequest request;
    RequestQueue requestQueue;
    private List<Payment> PaymentList;

    View view;

//    RelativeLayout hungry, ptidej,sendcolis,sendco,supermarket,apero,boutiques,magic,ap;

    public Acceuil() {
        // Required empty public constructor
    }

    EditText searchprodorlocate;
     UserDbHelper db;
     String phone;
    JSONArray[] data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         view =  inflater.inflate(R.layout.fragment_acceuil,
                container, false);

       myrecyclerview = (RecyclerView) view.findViewById(R.id.service_recycler);

        serviceList = new ArrayList<>();
        PaymentList = new ArrayList<>();

        db = new UserDbHelper(getContext());
        HashMap<String, String> user = db.getUserDetails();
        phone = user.get("telephone");

         if (Statuts.isNetworkAvaliable(getContext())){
           jsonRequest();
         }
         else{
             data = null;
         }

        searchprodorlocate = view.findViewById(R.id.searchprodorlocate);

        searchprodorlocate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Intent intent = new Intent(getActivity(), ProductListActivity.class);
                startActivity(intent);
                Animatoo.animateFade(getActivity());
            }
        });

//// Restaurant
//        hungry = view.findViewById(R.id.hungry);
//// Transport scolaire de vos enfant
//        ptidej = view.findViewById(R.id.ptidej);
//// Colis surprise
//        sendcolis = view.findViewById(R.id.sendcolis);
// // Coursier
//        sendco = view.findViewById(R.id.sendco);
//// Bouteille de Gaz
//        supermarket = view.findViewById(R.id.supermarket);
//// Pharmacie
//        apero = view.findViewById(R.id.apero);
//// boutiques
//        boutiques = view.findViewById(R.id.boutiques);
//
//        // Rapide
//        magic = view.findViewById(R.id.magic);
//
// // Bouquet de fleur
//        ap = view.findViewById(R.id.ap);
//
//
//
//
//
//        hungry.setOnClickListener(this);
//        ptidej.setOnClickListener(this);
//        sendcolis.setOnClickListener(this);
//        sendco.setOnClickListener(this);
//        supermarket.setOnClickListener(this);
//        apero.setOnClickListener(this);
//        boutiques.setOnClickListener(this);
//        magic.setOnClickListener(this);
//        ap.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.hungry:
//                // Restaurant
//                break;
//            case R.id.ptidej:
//                // Transport scolaire de vos enfant
//                break;
//
//            case R.id.sendcolis:
//                // Colis surprise
//                break;
//
//            case R.id.sendco:
//                 startActivity(new Intent(getActivity(), CoursierActivity.class));
//                break;
//
//            case R.id.supermarket:
//                // Bouteille de Gaz
//                break;
//             case R.id.magic:
//
//            case R.id.apero:
//                startActivity(new Intent(getActivity(), RapideActivity.class));
//                 break;
//
//            case R.id.ap:
//                // Bouquet de fleur
//                break;
//
//                case R.id.boutiques:
//                // boutiques
//                break;
//        }
//
    }




    private void jsonRequest() {
        final SimpleArcDialog mDialog = new SimpleArcDialog(getActivity());
        ArcConfiguration configuration = new ArcConfiguration(getActivity());
        configuration.setText("Chargement en cours...");
        mDialog.setConfiguration(configuration);
        mDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.URL_MENU_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    if (json == null){

                    }else{

                    }

                    String status = json.getString("status");
                    Log.d("dataservice",String.valueOf(json));
                 if(status.equals("000")) {
                    Toast.makeText(getActivity(), String.valueOf(json), Toast.LENGTH_SHORT).show();


                    JSONArray  services = json.getJSONArray("information");

                        for (int i = 0 ; i < services.length(); i++){
                            JSONObject jsonObject = services.getJSONObject(i);
                            Service service = new Service();
                            service.setKey(jsonObject.getString("service_key").trim());
                            service.setHint(jsonObject.getString("hint_details_commande"));
                            service.setLogo(jsonObject.getString("logo").trim());
                            service.setMenu(jsonObject.getInt("sous_menu"));
                            service.setName(jsonObject.getString("service_denomination").trim());
                            service.setSous_menu(jsonObject.getJSONArray("sous_menu_liste"));
                            service.setActif(jsonObject.getInt("actif"));
                            service.setAdresse_collect(jsonObject.getString("adresse_collect").trim());
                            service.setAdresse_livraison(jsonObject.getString("adresse_livraison").trim());

                           serviceList.add(service);
                            mDialog.dismiss();
                            // dialogHelper.AccountVerify();
                        }
                    }else{
                        String message = json.getString("message");
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }

                } catch (JSONException e) {
                    mDialog.dismiss();
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_SHORT).show();
                }

                setuprecyclerview(serviceList);

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


    private void setuprecyclerview(List<Service> serviceList) {
        ServiceAdapter serviceAdapter = new ServiceAdapter(getContext(), serviceList, getActivity());
        myrecyclerview.setLayoutManager(new GridLayoutManager(getContext(),3));
        myrecyclerview.setAdapter(serviceAdapter);
    }
}
