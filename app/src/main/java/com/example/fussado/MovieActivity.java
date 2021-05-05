package com.example.fussado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;

import com.example.fussado.Model.Movie;
import com.example.fussado.Adapter.genreAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MovieActivity extends AppCompatActivity {

    RecyclerView movieRecycler;
    List<Movie> movieList;

    private static final  String url = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        movieRecycler = findViewById(R.id.movieRecycler);
        movieList =new ArrayList<Movie>();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseData = response.body().string();
                MovieActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getData(responseData);
                    }
                });
            }
        });



    }

    private void getData(String s){

        try{
            JSONObject jsonObject =  new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for(int i=0;i<jsonArray.length(); i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                Movie model = new Movie();
                model.setId(jsonObject1.getString("id"));
                model.setName(jsonObject1.getString("title"));
                model.setImage(jsonObject1.getString("poster_path"));
                model.setRating(jsonObject1.getString("vote_average"));

                movieList.add(model);


            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        PutDataIntoRecyclerView(movieList);
    }

    private void PutDataIntoRecyclerView(List<Movie> movieList){
        genreAdapter movieAdapter = new genreAdapter(this, movieList);
        movieRecycler.setLayoutManager(new GridLayoutManager(this,2));
        movieRecycler.setAdapter(movieAdapter);


    }
}