package com.fast_ad.fast_ad.plugins.places;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fast_ad.fast_ad.R;

/**
 * Created by Hp on 3/18/2016.
 */
public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView img;
    TextView nametxt,posTxt;
    ItemClickListener itemClickListener;


    public MyHolder(View itemView) {
        super(itemView);


        nametxt= (TextView) itemView.findViewById(R.id.placechoosedinpast);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener=ic;

    }
}
