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

public class BooksActivity extends AppCompatActivity {

    RecyclerView booksWishRec;
    FloatingActionButton addBooksButton;
    ArrayList<Movie> booksWish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        booksWish = Utils.getInstance(this).getBooks();
        booksWishRec = findViewById(R.id.booksWishRec);

        genreAdapter booksAdapter = new genreAdapter(this,booksWish);
        booksWishRec.setLayoutManager(new GridLayoutManager(this,2));
        booksWishRec.setAdapter(booksAdapter);

        addBooksButton = findViewById(R.id.addBookButton);

        addBooksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchActivity.class);
                intent.putExtra("Key","book");
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