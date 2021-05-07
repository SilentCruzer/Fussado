package com.example.fussado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;

import com.example.fussado.Model.Movie;
import com.example.fussado.Adapter.genreAdapter;
import com.example.fussado.Model.MovieInfoActivity;
import com.google.android.material.tabs.TabLayout;

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


    private static final  String url = "";
    private ArrayList<Movie> movieList;

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
        String name = "", id ="", image = "",rating = "",year = "" ,overview = "";
        try{
            JSONObject jsonObject =  new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for(int i=0;i<jsonArray.length(); i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                id = jsonObject1.getString("id");
                name= jsonObject1.getString("title");
                image = jsonObject1.getString("poster_path");
                rating = jsonObject1.getString("vote_average");
                year = jsonObject1.getString("release_date");
                overview = jsonObject1.getString("overview");

                movieList.add( new Movie(id,name,image, rating, year, overview));

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