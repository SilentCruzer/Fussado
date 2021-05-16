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

public class GamesActivity extends AppCompatActivity {

    RecyclerView gamesWishRec;
    FloatingActionButton addGamesButton;
    ArrayList<Movie> gamesWish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        gamesWish = Utils.getInstance(this).getGames();
        gamesWishRec = findViewById(R.id.gamesWishRec);

        genreAdapter gamesAdapter = new genreAdapter(this,gamesWish);
        gamesWishRec.setLayoutManager(new GridLayoutManager(this,3));
        gamesWishRec.setAdapter(gamesAdapter);

        addGamesButton = findViewById(R.id.addGamesButton);

        addGamesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchActivity.class);
                intent.putExtra("Key","game");
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