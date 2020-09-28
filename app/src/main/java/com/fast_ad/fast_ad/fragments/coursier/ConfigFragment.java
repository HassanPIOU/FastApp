package com.fast_ad.fast_ad.fragments.coursier;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fast_ad.fast_ad.R;
import com.fast_ad.fast_ad.activities.product.CoursierActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfigFragment extends Fragment {


    public ConfigFragment() {
        // Required empty public constructor
    }

    View view;
   TextView valide_first_step;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_config,
                container, false);

        valide_first_step =    view.findViewById(R.id.valide_first_step);

        sharedpreferences = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        String restoredText = sharedpreferences.getString("option", null);

        if (restoredText == "valider"){
            editor.clear();
            editor.commit();
        }


        valide_first_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textPassToB = "valider";
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("option", textPassToB);
                editor.commit();
          }
        });

        return view;
    }

}
