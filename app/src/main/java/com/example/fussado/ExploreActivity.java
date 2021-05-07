package com.example.fussado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.fussado.Adapter.ImageAdapter;
import com.example.fussado.Adapter.genreAdapter;
import com.example.fussado.Model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ExploreActivity extends AppCompatActivity {

    TextView discoverMovies, discoverTv, discoverBooks, discoverGames;
    RecyclerView disMovieRecycler, disTvRecycler, disBooksRecycler, disGamesRecycler;

    private static final  String movieURL = "";
    private static final  String tvURL = "";
    private static final  String bookURL = "";
    private static final  String gamesURL = "";

    public ArrayList<Movie> movieList;
    public ArrayList<Movie> tvList;
    public ArrayList<Movie> bookList;
    public ArrayList<Movie> gameList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        movieList =new ArrayList<Movie>();
        tvList =new ArrayList<Movie>();
        bookList =new ArrayList<Movie>();
        gameList = new ArrayList<Movie>();

        initViews();

        initializeView();


    }

    private void initializeView(){
        ViewPager viewPager = findViewById(R.id.allposterView);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);

        setAPI(movieURL);
        setAPI(tvURL);
        setAPI(bookURL);
        setAPI(gamesURL);
    }
    private void initViews(){
        discoverMovies = findViewById(R.id.discoverMovies);
        discoverTv = findViewById(R.id.discoverTv);
        discoverBooks = findViewById(R.id.discoverBooks);
        discoverGames = findViewById(R.id.discoverGames);

        disMovieRecycler = findViewById(R.id.disMovieRecycler);
        disTvRecycler = findViewById(R.id.disTvRecycler);
        disBooksRecycler = findViewById(R.id.disBooksRecycler);
        disGamesRecycler = findViewById(R.id.disGamesRecycler);
    }

    private void setAPI(String url){

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
                ExploreActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(url=="") {
                            getMovieData(responseData);
                        }if (url == ""){
                            getTvData(responseData);
                        }if(url== ""){
                            getBookData(responseData);
                        }if(url == ""){
                            getGamesData(responseData);
                        }
                    }
                });
            }
        });
    }

    private  void getMovieData(String S){
        String name = "", id ="", image = "",rating = "",year = "" ,overview = "";
        try{
            JSONObject jsonObject =  new JSONObject(S);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for(int i=0;i<jsonArray.length(); i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                id = jsonObject1.getString("id");
                name= jsonObject1.getString("title");
                image = "https://image.tmdb.org/t/p/w500"+jsonObject1.getString("poster_path");
                rating = jsonObject1.getString("vote_average");
                year = jsonObject1.getString("release_date");
                overview = jsonObject1.getString("overview");

                Log.e("testing", "getMovieData: " + id+name+image+rating+year+overview);
                movieList.add( new Movie(id,name,image, rating, year, overview));

            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        PutDataIntoRecyclerView(movieList);

    }

    private  void getTvData(String S){
        String name = "", id ="", image = "",rating = "",year = "" ,overview = "";
        try{
            JSONObject jsonObject =  new JSONObject(S);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for(int i=0;i<jsonArray.length(); i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                id = jsonObject1.getString("id");
                name= jsonObject1.getString("name");
                image = "https://image.tmdb.org/t/p/w500"+jsonObject1.getString("poster_path");
                rating = jsonObject1.getString("vote_average");
                year = jsonObject1.getString("original_language");
                overview = jsonObject1.getString("overview");

                Log.e("testing", "getMovieData: " + id+name+image+rating+year+overview);
                tvList.add( new Movie(id,name,image, rating, year, overview));

            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        PutDataIntoTvRecyclerView(tvList);
    }

    private  void getBookData(String S){
        String name = "", id ="", image = "",rating = "",year = "" ,overview = "";
        try{
            JSONObject jsonObject =  new JSONObject(S);
            JSONArray jsonArray = jsonObject.getJSONObject("results").getJSONArray("books");

            for(int i=0;i<jsonArray.length(); i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                id = jsonObject1.getString("rank");
                name= jsonObject1.getString("title");
                image = jsonObject1.getString("book_image");
                rating = jsonObject1.getString("author");
                year = jsonObject1.getString("publisher");
                overview = jsonObject1.getString("description");

                Log.e("testing", "getMovieData: " + id+name+image+rating+year+overview);
                bookList.add( new Movie(id,name,image, rating, year, overview));


            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        PutDataIntoBookRecyclerView(bookList);
    }

    private  void getGamesData(String S){
        String name = "", id ="", image = "",rating = "",year = "" ,overview = "";
        try{
            JSONObject jsonObject =  new JSONObject(S);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for(int i=0;i<jsonArray.length(); i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                id = jsonObject1.getString("id");
                name= jsonObject1.getString("name");
                image = jsonObject1.getString("background_image");
                rating = jsonObject1.getString("rating");
                year = jsonObject1.getString("released");
                overview = jsonObject1.getString("playtime");

                Log.e("testing", "getMovieData: " + id+name+image+rating+year+overview);
                gameList.add( new Movie(id,name,image, rating, year, overview));

            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        PutDataIntoGameRecyclerView(gameList);
    }

    private void PutDataIntoRecyclerView(List<Movie> movieList){
        genreAdapter movieAdapter = new genreAdapter(this, movieList);
        disMovieRecycler.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        disMovieRecycler.setAdapter(movieAdapter);
    }

    private void PutDataIntoTvRecyclerView(List<Movie> movieList){
        genreAdapter movieAdapter = new genreAdapter(this, movieList);
        disTvRecycler.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        disTvRecycler.setAdapter(movieAdapter);
    }
    private void PutDataIntoBookRecyclerView(List<Movie> movieList){
        genreAdapter movieAdapter = new genreAdapter(this, movieList);
        disBooksRecycler.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        disBooksRecycler.setAdapter(movieAdapter);
    }
    private void PutDataIntoGameRecyclerView(List<Movie> movieList){
        genreAdapter movieAdapter = new genreAdapter(this, movieList);
        disGamesRecycler.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        disGamesRecycler.setAdapter(movieAdapter);
    }
}