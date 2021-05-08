package com.example.fussado.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fussado.Model.Movie;
import com.example.fussado.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private List<Movie> searchList;
    private Context mContext;


    public SearchAdapter(Context mContext, List<Movie> searchList ) {
        this.searchList = searchList;
        this.mContext = mContext;

    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_wishlist, parent, false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(SearchAdapter.ViewHolder holder, int position) {
        Movie item = searchList.get(position);
        holder.name.setText(item.getName());
        holder.subTitle.setText(item.getYear());
        Glide.with(mContext)
                .load(item.getImage())
                .into(holder.poster);
    }

    public int getItemCount(){
        return searchList.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView poster;
        TextView name;
        TextView subTitle;

        ViewHolder(View view){
            super(view);
            poster = view.findViewById(R.id.poster);
            name = view.findViewById(R.id.name);
            subTitle = view.findViewById(R.id.subTitle);

        }
    }
}
