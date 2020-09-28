package com.fast_ad.fast_ad.fragments.coursier;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fast_ad.fast_ad.R;
import com.fast_ad.fast_ad.activities.product.CoursierActivity;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmFragment extends Fragment {

    View view;
  String b_received;
    public ConfirmFragment() {
        // Required empty public constructor
    }

    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     view =    inflater.inflate(R.layout.fragment_confirm, container, false);


        SharedPreferences prefs = getActivity().getSharedPreferences(MyPREFERENCES , MODE_PRIVATE);
        String restoredText = prefs.getString("option", null);
        TextView bbb = view.findViewById(R.id.bbb);

        if (restoredText !=  ""){
            bbb.setText(restoredText);
        }
        return  view;

    }



}
