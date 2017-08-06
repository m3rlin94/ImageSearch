package com.example.naveen.imagesearch.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.naveen.imagesearch.R;
import com.example.naveen.imagesearch.model.ImageObject;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by naveen on 06/08/2017.
 */

public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ImageObject> imageList;
    private Context mContext;
    private RecyclerView recyclerView;

    public RvAdapter(RecyclerView rv, List<ImageObject> io, Context context) {
        this.recyclerView = rv;
        this.imageList = io;
        this.mContext = context;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UserViewHolder viewHolder = (UserViewHolder)holder;
        ImageObject imgObject = imageList.get(position);

        Picasso.with(mContext).load(imgObject.getThumbnail()).into(viewHolder.item_thumbnail);

        viewHolder.item_thumbnail.setImageResource(R.color.cardview_dark_background);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_item, parent, false);
        return new UserViewHolder(view);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView item_thumbnail;

        public UserViewHolder(View view) {
            super(view);
            item_thumbnail = (ImageView) view.findViewById(R.id.customImage);
            item_thumbnail.setImageResource(R.color.cardview_light_background);
        }

    }

}
