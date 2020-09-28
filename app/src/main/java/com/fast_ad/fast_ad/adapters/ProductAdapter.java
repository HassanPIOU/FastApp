package com.fast_ad.fast_ad.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fast_ad.fast_ad.Models.Product;
import com.fast_ad.fast_ad.R;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder>{
    private Context mContext;
    private Activity activity;
    List<Product> mData;
    public ProductAdapter(Context mContext, List<Product> mData, Activity activity) {
        this.mContext = mContext;
        this.mData = mData;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewgroups, int i) {
        View view;
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_product_list,viewgroups, false);

        final MyViewHolder viewHolder =  new MyViewHolder(view);


//        viewHolder.viewdetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, GoogleMapsActivity.class);
//                intent.putExtra("name", mData.get(viewHolder.getAdapterPosition()).getName());
//                intent.putExtra("location", mData.get(viewHolder.getAdapterPosition()).getLocation());
//                intent.putExtra("longitude", mData.get(viewHolder.getAdapterPosition()).getLongitude());
//                intent.putExtra("latitude", mData.get(viewHolder.getAdapterPosition()).getLatitude());
//                intent.putExtra("categorie", mData.get(viewHolder.getAdapterPosition()).getType());
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                mContext.startActivity(intent);
////                Toast.makeText(mContext, String.valueOf(mData.get(viewHolder.getAdapterPosition()).getLatitude()), Toast.LENGTH_SHORT).show();
//            }
//        });


        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
//      myViewHolder.agencename.setText(mData.get(i).getName());
//      myViewHolder.agencequartier.setText(mData.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder {

       private TextView agencename, agencequartier,newornot;
//       private MaterialRippleLayout viewdetail;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//          agencename = itemView.findViewById(R.id.agencename);
//            agencequartier = itemView.findViewById(R.id.agencequartier);
//            newornot = itemView.findViewById(R.id.newornot);
//            viewdetail = itemView.findViewById(R.id.viewdetail);
        }
    }
}
