package com.fast_ad.fast_ad.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fast_ad.fast_ad.Models.Places;
import com.fast_ad.fast_ad.Models.Product;
import com.fast_ad.fast_ad.R;
import com.fast_ad.fast_ad.activities.product.RapideActivity;

import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.MyViewHolder>{
    private Context mContext;
    private Activity activity;
    List<Places> mData;
    public PlacesAdapter(Context mContext, List<Places> mData, Activity activity) {
        this.mContext = mContext;
        this.mData = mData;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewgroups, int i) {
        View view;
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_places,viewgroups, false);

        final MyViewHolder viewHolder =  new MyViewHolder(view);


        viewHolder.placeRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RapideActivity.class);
//                intent.putExtra("name", mData.get(viewHolder.getAdapterPosition()).getName());
                mContext.startActivity(intent);
//                Toast.makeText(mContext, String.valueOf(mData.get(viewHolder.getAdapterPosition()).getLatitude()), Toast.LENGTH_SHORT).show();
            }
        });


        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
      myViewHolder.placename.setText(mData.get(i).getName());
//      myViewHolder.agencequartier.setText(mData.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder {

       private TextView placename;
     private LinearLayout placeRelative;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            placename = itemView.findViewById(R.id.placename);
             placeRelative = itemView.findViewById(R.id.placeRelative);
//            newornot = itemView.findViewById(R.id.newornot);
//            viewdetail = itemView.findViewById(R.id.viewdetail);
        }
    }
}
