package com.example.fussado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fussado.Adapter.genreAdapter;
import com.example.fussado.Model.genreModel;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView  genreRecyclerView;
    private genreAdapter genreAdapter;


    private List<genreModel> genrelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        genrelist = new ArrayList<>();

        genreRecyclerView = findViewById(R.id.genreRecyclerView);
        genreRecyclerView.setLayoutManager(new GridLayoutManager(this,2, GridLayoutManager.VERTICAL, false));
        genreAdapter = new genreAdapter(this);
        genreRecyclerView.setAdapter(genreAdapter);

        genreModel genre = new genreModel();
        genre.setGenre("Movies");
        genre.setId(1);

        genrelist.add(genre);
        genrelist.add(genre);
        genrelist.add(genre);

        genreAdapter.setGenre(genrelist);
    }
}