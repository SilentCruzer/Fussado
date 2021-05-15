package com.example.fussado.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fussado.Model.Movie;
import com.example.fussado.Model.MovieInfoActivity;
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

        holder.searchParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(mContext , MovieInfoActivity.class);
                searchIntent.putExtra("type",searchList.get(position).getType());
                searchIntent.putExtra("id",searchList.get(position).getId());
                searchIntent.putExtra("title",searchList.get(position).getName());
                searchIntent.putExtra("image",searchList.get(position).getImage());
                searchIntent.putExtra("vote_average",searchList.get(position).getRating());
                searchIntent.putExtra("release_date",searchList.get(position).getYear());
                searchIntent.putExtra("overview",searchList.get(position).getOverview());
                mContext.startActivity(searchIntent);
            }
        });
    }

    public int getItemCount(){
        return searchList.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView poster;
        TextView name;
        TextView subTitle;
        CardView searchParent;



        ViewHolder(View view){
            super(view);
            poster = view.findViewById(R.id.poster);
            name = view.findViewById(R.id.name);
            subTitle = view.findViewById(R.id.subTitle);
            searchParent = view.findViewById(R.id.searchParent);

        }
    }
}
