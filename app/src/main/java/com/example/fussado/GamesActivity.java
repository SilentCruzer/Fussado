package com.example.fussado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GamesActivity extends AppCompatActivity {

    RecyclerView gamesWishRec;
    FloatingActionButton addGamesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

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
}