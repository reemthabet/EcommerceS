package com.example.ecommerces.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerces.Model.ExploreMenu;
import com.example.ecommerces.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ExploreMenuAdapter extends RecyclerView.Adapter<ExploreMenuAdapter.ViewHolder> {
    private Context context;
    private List<ExploreMenu> exploreMenuList;

    public ExploreMenuAdapter(Context context, List<ExploreMenu> exploreMenuList) {
        this.context=context;
        this.exploreMenuList=exploreMenuList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_explormenu_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        String url="https://console.firebase.google.com/u/0/project/ecommerces-9d23d/firestore/data/~2FExploreMenu";
//        Picasso.get().load(url).into(holder.img);
        Glide.with(context).load(exploreMenuList.get(position).getImg_url()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        try {
            return exploreMenuList.size();
        } catch (Exception ex){return 0;}

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    private ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img =itemView.findViewById(R.id.img_exploreMenu);
        }
    }
}
