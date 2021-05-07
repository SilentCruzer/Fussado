package com.example.fussado.Model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fussado.MovieActivity;
import com.example.fussado.R;

import java.util.ArrayList;
import java.util.List;

public class MovieInfoActivity extends AppCompatActivity {
    ImageView moviePoster;
    TextView  MovieName, movieYear, movieRating,movieDescription;
    List<Movie> copyList;


    Movie incomingMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);


        initViews();

        Bundle extras = getIntent().getExtras();
        String id = "", title="", image="", vote_average="", release_date="", overview ="";
        if(extras != null){
            id = extras.getString("id");
            title = extras.getString("title");
            image = extras.getString("image");
            vote_average = extras.getString("vote_average");
            release_date = extras.getString("release_date");
            overview = extras.getString("overview");
        }
        MovieName.setText(title);
        movieYear.setText(release_date);
        movieRating.setText(vote_average);
        movieDescription.setText(overview);

        Glide.with(this)
                .load(image)
                .into(moviePoster);
    }


    private void initViews () {
        moviePoster = findViewById(R.id.moviePoster);
        MovieName = findViewById(R.id.MovieName);
        movieYear = findViewById(R.id.movieYear);
        movieRating = findViewById(R.id.movieRating);
        movieDescription = findViewById(R.id.movieDescription);
    }

}