package com.example.fussado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    CardView movieCard, tvShowsCard, gamesCard, booksCard, exploreCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieCard = findViewById(R.id.movieCard);
        tvShowsCard = findViewById(R.id.tvShowsCard);
        gamesCard = findViewById(R.id.gamesCard);
        booksCard = findViewById(R.id.booksCard);
        exploreCard = findViewById(R.id.ExploreCard);

        movieCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(v.getContext(), MovieActivity.class);
                startActivity(intent);
            }
        });

        tvShowsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(v.getContext(), TVshowsActivity.class);
                startActivity(intent);
            }
        });

        gamesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(v.getContext(), GamesActivity.class);
                startActivity(intent);
            }
        });

        booksCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(v.getContext(), BooksActivity.class);
                startActivity(intent);
            }
        });

        exploreCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(v.getContext(),ExploreActivity.class);
                startActivity(intent);
            }
        });

    }

}