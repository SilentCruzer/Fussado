package com.example.fussado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BooksActivity extends AppCompatActivity {

    RecyclerView booksWishRec;
    FloatingActionButton addBooksButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

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
}