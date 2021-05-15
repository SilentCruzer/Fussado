package com.example.fussado.Model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fussado.BooksActivity;
import com.example.fussado.GamesActivity;
import com.example.fussado.MainActivity;
import com.example.fussado.MovieActivity;
import com.example.fussado.R;
import com.example.fussado.TVshowsActivity;
import com.example.fussado.Utils;

import java.util.ArrayList;
import java.util.List;

public class MovieInfoActivity extends AppCompatActivity {
    ImageView moviePoster;
    TextView  MovieName, movieYear, movieRating,movieDescription;
    Button btnAddMovie;
    public String id = "", title="", image="", vote_average="", release_date="", overview ="", type = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);


        initViews();

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            type = extras.getString("type");
            id = extras.getString("id");
            title = extras.getString("title");
            image = extras.getString("image");
            vote_average = extras.getString("vote_average");
            release_date = extras.getString("release_date");
            overview = extras.getString("overview");

            Movie movie =  new Movie(id,title,image,vote_average,release_date,overview, type);

            handleIncoming(movie, type);
        }
        MovieName.setText(title);
        movieYear.setText(release_date);
        movieRating.setText(vote_average);
        movieDescription.setText(overview);

        Glide.with(this)
                .load(image)
                .into(moviePoster);
    }

    private void handleIncoming(final Movie movie, String type){
        ArrayList<Movie> moviesWishList = Utils.getInstance(this).getMovies();
        ArrayList<Movie> tvWishList = Utils.getInstance(this).getTvShows();
        ArrayList<Movie> booksList = Utils.getInstance(this).getBooks();
        ArrayList<Movie> gamesWishList = Utils.getInstance(this).getGames();

        boolean existIn = false;
        if(type.equals("Movie")) {
            existIn = checkExistence(movie, type, moviesWishList);
        }
        if(type.equals("Tv_Show")) {
            existIn = checkExistence(movie, type, tvWishList);
        }
        if(type.equals("Book")) {
            existIn = checkExistence(movie, type, booksList);
        }
        if(type.equals("Game")) {
            existIn = checkExistence(movie, type, gamesWishList);
        }


        if(existIn){
            btnAddMovie.setText("Remove from list");
            btnAddMovie.setTextColor(Color.parseColor("red"));

            btnAddMovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean remove = false;
                    Intent intent = new Intent();
                    if(type.equals("Movie")){
                        remove = Utils.getInstance(MovieInfoActivity.this).removeFromMovies(movie);
                        intent = new Intent(MovieInfoActivity.this, MovieActivity.class);
                    }
                    if (type.equals("Tv_Show")){
                        remove = Utils.getInstance(MovieInfoActivity.this).removeFromTvShows(movie);
                        intent = new Intent(MovieInfoActivity.this, TVshowsActivity.class);
                    }
                    if (type.equals("Book")){
                        remove = Utils.getInstance(MovieInfoActivity.this).removeFromBooks(movie);
                        intent = new Intent(MovieInfoActivity.this, BooksActivity.class);
                    }
                    if (type.equals("Game")){
                        remove = Utils.getInstance(MovieInfoActivity.this).removeFromGames(movie);
                        intent = new Intent(MovieInfoActivity.this, GamesActivity.class);
                    }


                    if(remove){
                        Toast.makeText(MovieInfoActivity.this, "Removed from list", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }else{
                        Toast.makeText(MovieInfoActivity.this, "Something went wrong, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            btnAddMovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    boolean add = false;
                    if(type.equals("Movie")){
                        add = Utils.getInstance(MovieInfoActivity.this).addToMovies(movie);
                        intent = new Intent(MovieInfoActivity.this, MovieActivity.class);
                    }
                    if (type.equals("Tv_Show")){
                        add = Utils.getInstance(MovieInfoActivity.this).addToTvShows(movie);
                        intent = new Intent(MovieInfoActivity.this, TVshowsActivity.class);
                    }
                    if (type.equals("Book")){
                        add = Utils.getInstance(MovieInfoActivity.this).addToBooks(movie);
                        intent = new Intent(MovieInfoActivity.this, BooksActivity.class);
                    }
                    if (type.equals("Game")){
                        add = Utils.getInstance(MovieInfoActivity.this).addToGames(movie);
                        intent = new Intent(MovieInfoActivity.this, GamesActivity.class);
                    }


                    if(add){
                        Toast.makeText(MovieInfoActivity.this, "Added item", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }else{
                        Toast.makeText(MovieInfoActivity.this, "Something went wrong, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }



    private boolean checkExistence(Movie movie , String type, ArrayList<Movie> list){
        for(Movie b: list){
            if(b.getName().equals(movie.getName())){
                return true;
            }
        }
        return false;
    }


    private void initViews () {
        moviePoster = findViewById(R.id.moviePoster);
        MovieName = findViewById(R.id.MovieName);
        movieYear = findViewById(R.id.movieYear);
        movieRating = findViewById(R.id.movieRating);
        movieDescription = findViewById(R.id.movieDescription);
        btnAddMovie = findViewById(R.id.btnAddMovie);
    }

}