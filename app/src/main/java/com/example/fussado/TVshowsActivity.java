package com.example.fussado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fussado.Adapter.genreAdapter;
import com.example.fussado.Model.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TVshowsActivity extends AppCompatActivity {

    RecyclerView tvWishRec;
    FloatingActionButton addTvButton;
    ArrayList<Movie> tvShowWish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshows);

        tvShowWish = Utils.getInstance(this).getTvShows();
        tvWishRec = findViewById(R.id.tvWishRec);

        genreAdapter tvAdapter = new genreAdapter(this,tvShowWish);
        tvWishRec.setLayoutManager(new GridLayoutManager(this,2));
        tvWishRec.setAdapter(tvAdapter);


        addTvButton = findViewById(R.id.addTvButton);

        addTvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchActivity.class);
                intent.putExtra("Key","tv");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}