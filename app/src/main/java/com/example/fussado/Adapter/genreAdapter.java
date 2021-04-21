package com.example.fussado.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fussado.MainActivity;
import com.example.fussado.Model.genreModel;
import com.example.fussado.R;

import java.util.List;

public class genreAdapter extends RecyclerView.Adapter<genreAdapter.ViewHolder> {

    private List<genreModel> genrelist;
    private MainActivity activity;

    public genreAdapter(MainActivity activity){
        this.activity = activity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.genre_card_layout, parent, false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        genreModel item = genrelist.get(position);
        holder.name.setText(item.getGenre());
    }

    public int getItemCount(){
        return genrelist.size();

    }

    public void setGenre(List<genreModel> genrelist){
        this.genrelist = genrelist;
        notifyDataSetChanged();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.genreName);
        }
    }

}

