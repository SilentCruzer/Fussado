package com.example.fussado.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.fussado.MainActivity;
import com.example.fussado.Model.Movie;
import com.example.fussado.Model.MovieInfoActivity;
import com.example.fussado.Model.genreModel;
import com.example.fussado.MovieActivity;
import com.example.fussado.R;

import java.util.List;

public class genreAdapter extends RecyclerView.Adapter<genreAdapter.ViewHolder> {

    private List<Movie> moviesList;
    private Context mContext;
    private String parentActivity;

    public genreAdapter(Context mContext, List<Movie> moviesList){

        this.mContext = mContext;
        this.moviesList = moviesList;
        this.parentActivity = parentActivity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card, parent, false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie item = moviesList.get(position);
        holder.name.setText(item.getName());
        Log.e("testing", "onBindViewHolder: ");
        Glide.with(mContext)
                .load(item.getImage())
                .into(holder.img);


        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieInfoActivity.class);
                intent.putExtra("type",moviesList.get(position).getType());
                intent.putExtra("id",moviesList.get(position).getId());
                intent.putExtra("title",moviesList.get(position).getName());
                intent.putExtra("image",moviesList.get(position).getImage());
                intent.putExtra("vote_average",moviesList.get(position).getRating());
                intent.putExtra("release_date",moviesList.get(position).getYear());
                intent.putExtra("overview",moviesList.get(position).getOverview());
                mContext.startActivity(intent);
            }
        });
    }

    public int getItemCount(){
        return moviesList.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView img;
        RelativeLayout parent;
        ViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.movieTitle);
            img = view.findViewById(R.id.poster);
            parent = view.findViewById(R.id.parent);
        }
    }

}

