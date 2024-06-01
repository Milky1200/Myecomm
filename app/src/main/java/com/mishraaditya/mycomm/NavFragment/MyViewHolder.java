package com.mishraaditya.mycomm.NavFragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mishraaditya.mycomm.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView iThumbnail;
    TextView iTitle,iDescription,iCategory,iPrice,iBrand,iWarranty;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        iThumbnail=itemView.findViewById(R.id.item_thumbnail);
        iDescription=itemView.findViewById(R.id.item_description);
        iCategory=itemView.findViewById(R.id.item_category);
        iBrand=itemView.findViewById(R.id.item_brand);
        iPrice=itemView.findViewById(R.id.item_price);
        iWarranty=itemView.findViewById(R.id.item_warranty);
        iTitle=itemView.findViewById(R.id.item_title);

    }
}
