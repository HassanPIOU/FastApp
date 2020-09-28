package com.fast_ad.fast_ad.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.fast_ad.fast_ad.Models.Product;
import com.fast_ad.fast_ad.Models.Service;
import com.fast_ad.fast_ad.R;
import com.fast_ad.fast_ad.activities.product.ProductListActivity;
import com.fast_ad.fast_ad.activities.product.RapideActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyViewHolder>{
    private Context mContext;
    private Activity activity;
    List<Service> mData;
    public ServiceAdapter(Context mContext, List<Service> mData, Activity activity) {
        this.mContext = mContext;
        this.mData = mData;
        this.activity = activity;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewgroups, final int i) {
        View view;
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_service,viewgroups, false);

        final MyViewHolder viewHolder =  new MyViewHolder(view);


        viewHolder.serviceLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf("0").equals(mData.get(viewHolder.getAdapterPosition()).getMenu())) {
                    Intent intent = new Intent(activity,RapideActivity.class);
                    intent.putExtra("address_collect", mData.get(viewHolder.getAdapterPosition()).getAdresse_collect());
                    intent.putExtra("address_livraison",mData.get(viewHolder.getAdapterPosition()).getAdresse_livraison());
                    intent.putExtra("hint_commande",mData.get(viewHolder.getAdapterPosition()).getHint());
                    activity.startActivity(intent);
                    Animatoo.animateSlideLeft(activity);
                }
                else{
                    Intent intent = new Intent(activity, ProductListActivity.class);
                    intent.putExtra("sous_menu", (Parcelable) mData.get(viewHolder.getAdapterPosition()).getSous_menu());
                    intent.putExtra("address_collect", mData.get(viewHolder.getAdapterPosition()).getAdresse_collect());
                    intent.putExtra("address_livraison",mData.get(viewHolder.getAdapterPosition()).getAdresse_livraison());
                    intent.putExtra("hint_commande",mData.get(viewHolder.getAdapterPosition()).getHint());
                    activity.startActivity(intent);
                    Animatoo.animateSlideLeft(activity);

                }
            }
        });


        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.service_title.setText(mData.get(i).getName());
        Picasso.get().load(mData.get(i).getLogo()).into(myViewHolder.logoservice);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder {

     private TextView service_title;
     private ImageView logoservice;
    private LinearLayout serviceLinear;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            service_title = itemView.findViewById(R.id.service_title);
            logoservice = itemView.findViewById(R.id.logoservice);
            serviceLinear = itemView.findViewById(R.id.serviceLinear);
        }
    }
}
